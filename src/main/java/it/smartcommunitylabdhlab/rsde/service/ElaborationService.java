package it.smartcommunitylabdhlab.rsde.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.smartcommunitylabdhlab.rsde.common.bean.DHRun;
import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.Spec;
import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.exception.SystemException;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationEntity;
import it.smartcommunitylabdhlab.rsde.utils.Converter;

@Service
public class ElaborationService {

    private final Logger logger = LoggerFactory.getLogger(ElaborationService.class);

    @Autowired
    private ElaborationEntityService elEntityService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Converter converter;

    String postfix = "/api/v1/-/";

    public String save(Elaboration elaboration) {
	logger.debug("save elaboration {}", elaboration.getName());
	ElaborationEntity e = elEntityService.save(converter.toEntity(elaboration));
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

    public List<Elaboration> loadAll() {
	List<Elaboration> e = new ArrayList<>();
	for (ElaborationEntity ee : elEntityService.loadAll()) {
	    e.add(converter.toElaboration(ee));
	}
	return e;
    }

    public Elaboration launchDHCoreElaboration(Elaboration e) {
	return null;
    }

    public void deleteById(String id) {
	if (id != null) {
	    elEntityService.delete(id);
	}
    }

    public Elaboration launchRemoteElaboration(Elaboration el, String url, String token) {
	url = url + postfix + el.getProjectId() + "/runs";
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.setBearerAuth(token);
	DHRun input = new DHRun();
	input.setProject(el.getProjectId());
	Spec spec = new Spec(el.getTaskId(), el.getWorkflowId());
	if (!el.getParameters().isEmpty())
	    spec.setParameters(el.getParameters());
	input.setSpec(spec);
	try {
	    HttpEntity<String> entity = new HttpEntity<>(converter.toJsonString(input), headers);
	    String res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	    DHRun run = converter.toEntity(res, DHRun.class);
	    el.setCoreId(run.getId());
	    el.setStatus(run.getStatus().state);
	    return el;
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage());
	}

    }

    public boolean exists(Elaboration elaboration) {
	boolean exists = false;
	if (elEntityService.searchByWorkFlowId(elaboration.getWorkflowId()) !=  null )
	    exists = true;   
	
	return exists;
    }

}
