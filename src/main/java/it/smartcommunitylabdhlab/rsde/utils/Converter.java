package it.smartcommunitylabdhlab.rsde.utils;

import java.util.UUID;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.smartcommunitylabdhlab.rsde.common.bean.DHRun;
import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationEntity;

@Component
public class Converter {

	ObjectMapper mapper = new ObjectMapper();

	@PostConstruct()
	public void init() {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public ElaborationEntity toEntity(Elaboration e, ElaborationTemplate t) {
		ElaborationEntity persist = new ElaborationEntity("elb-" + UUID.randomUUID().toString(), e.getName(),
				t.getProjectId());
		persist.setCoreId(e.getCoreId());
		persist.setStatus(e.getStatus());
		persist.setWorkflowId(t.getWorkflowId());
		persist.setParameters(e.getParameters());
		persist.setTaskId(t.getTaskId());
		persist.setLocalName(e.getLocalName());
		persist.setName(e.getName());
		persist.setTag(t.getTag());
		persist.setLinks(e.getLinks());
		persist.setCreatedAt(e.getCreatedAt());
		persist.setUpdatedAt(e.getUpdatedAt());
		return persist;
	}

	public Elaboration toElaboration(ElaborationEntity ei) {
		Elaboration e = new Elaboration();
		e.setCoreId(ei.getCoreId());
		e.setId(ei.getId());
		e.setName(ei.getName());
		e.setStatus(ei.getStatus());
		e.setParameters(ei.getParameters());
		e.setLocalName(ei.getLocalName());
		e.setLinks(ei.getLinks());
		e.setTag(ei.getTag());
		e.setCreatedAt(ei.getCreatedAt());
		e.setUpdatedAt(ei.getUpdatedAt());
		return e;
	}

	public String toJsonString(DHRun input) throws JsonProcessingException {
		return mapper.writeValueAsString(input);
	}

	public DHRun toEntity(String res, Class<DHRun> class1) throws JsonProcessingException {
		return mapper.readValue(res, class1);
	}

}
