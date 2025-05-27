package it.smartcommunitylabdhlab.rsde.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.smartcommunitylabdhlab.rsde.common.bean.DHRun;
import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;
import it.smartcommunitylabdhlab.rsde.common.bean.Spec;
import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationEntity;
import it.smartcommunitylabdhlab.rsde.utils.Converter;

@Service
public class ElaborationService {

    private final Logger logger = LoggerFactory.getLogger(ElaborationService.class);

	@Value("${core.token}")
    private String dhToken;

    @Value("${core.apiEndopint}")
    private String dhUrl;


    @Autowired
    private ElaborationEntityService elEntityService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Converter converter;

    String postfix = "/api/v1/-/";

    public String save(Elaboration elaboration, ElaborationTemplate template) {
		logger.debug("save elaboration {}", elaboration.getName());
		ElaborationEntity e = elEntityService.save(converter.toEntity(elaboration, template));
		return e.getId();
    }

    public Elaboration getElaborationById(String eId) throws NoSuchElaborationException {
		logger.debug("get elaboration {}", eId);
		return (converter.toElaboration(elEntityService.fetch(eId)));
    }
    
    public Elaboration getElaborationByWorkFlowId(String workFlowId) throws NoSuchElaborationException {
		logger.debug("get elaboration by workflow {}", workFlowId);
		return (converter.toElaboration(elEntityService.searchByWorkFlowId(workFlowId)));
	}

    public Page<Elaboration> loadAll(Pageable pageable, String tag) {
		if (tag != null) {
			Page<ElaborationEntity> page = elEntityService.findByTag(pageable, tag);
			return page.map(e -> converter.toElaboration(e));
		}
		Page<ElaborationEntity> page = elEntityService.loadAll(pageable);
		return page.map(e -> converter.toElaboration(e));
    }

    public void deleteById(String id) {
		if (id != null) {
			elEntityService.delete(id);
		}
    }

    public Elaboration launchRemoteElaboration(Elaboration el, ElaborationTemplate template) {
		// el.setCoreId(UUID.randomUUID().toString());
		// el.setStatus("RUNNING");
		// return el;
		return doRemote(el, template);
	}


	public boolean exists(Elaboration el) {
		return el.getId() != null && elEntityService.findById(el.getId()) != null;
	}

	@Scheduled(fixedDelay = 10000)
	public void syncState() {
		logger.debug("syncState");
		elEntityService.searchByStatus("RUNNING").forEach(e -> {
			try {
				String url = dhUrl + postfix + "/" + e.getProject() + "/runs/" + e.getCoreId();
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setBearerAuth(dhToken);
				HttpEntity<String> entity = new HttpEntity<>(headers);
				String res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
				DHRun run = converter.toEntity(res, DHRun.class);
				if (!run.getStatus().state.equals(e.getStatus())) {
					e.setUpdatedAt(new Date());
				}
				e.setStatus(run.getStatus().state);
				elEntityService.saveEntity(e);
			} catch (Exception e1) {
				logger.error("error in syncState", e1);
			}
		});
	}

    private Elaboration doRemote(Elaboration el, ElaborationTemplate t) {
		String url = dhUrl + postfix + t.getProjectId() + "/runs";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(dhToken);
		DHRun input = new DHRun();
		input.setProject(t.getProjectId());
		Spec spec = new Spec(t.getTaskId(), t.getWorkflowId());
		Map<String, String> params = new HashMap<>();
		if (el.getParameters()!=null && !el.getParameters().isEmpty()) {
			params.putAll(el.getParameters());
		}
		if (t.getFixedParameters() != null && !t.getFixedParameters().isEmpty()) {
			params.putAll(t.getFixedParameters());
		}
		if (params != null && !params.isEmpty()) {
			spec.setParameters(params);
		}
		input.setSpec(spec);
		try {
			HttpEntity<String> entity = new HttpEntity<>(converter.toJsonString(input), headers);
			String res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			DHRun run = converter.toEntity(res, DHRun.class);
			el.setCoreId(run.getId());
			el.setStatus("RUNNING");
			return el;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

    }

    @SuppressWarnings({ "unchecked" })
	public String generatePresignedUrl(String id, String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(dhToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ElaborationEntity e = elEntityService.findById(id);
		String url = dhUrl + postfix + e.getProject() + "/artifacts?name=" + name;
		Map<String, Object> res = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, Object>>() {}).getBody();

		if (res != null) {
			Collection<Map<String, Object>> content = (Collection<Map<String, Object>>) res.getOrDefault("content", Collections.emptyList());
			if (content.size() > 0) {
				String artifactId = (String) content.iterator().next().get("id");
				url = dhUrl + postfix + e.getProject() + "/artifacts/" + artifactId + "/files/download";
				return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
			}
		}
		throw new RuntimeException("Artifact not found");
    }

}
