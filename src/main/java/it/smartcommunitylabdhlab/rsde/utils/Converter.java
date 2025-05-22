package it.smartcommunitylabdhlab.rsde.utils;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.smartcommunitylabdhlab.rsde.common.bean.DHRun;
import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationDTO;
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

    public ElaborationDTO toDTO(Elaboration e) {
	ElaborationDTO eDTO = new ElaborationDTO();
	eDTO.setArtifactLink(e.getLink());
	eDTO.setArtifactName(e.getArtifactName());
	eDTO.setEventDate(e.getEventDate());
	eDTO.setStatus(e.getStatus());
	eDTO.setType(e.getType());
	eDTO.setId(e.getId());
	eDTO.setName(e.getName());
	eDTO.setWorkflowId(e.getWorkflowId());
	eDTO.setCoreId(e.getCoreId());
	eDTO.setGeomtry(e.getGeomtry());
	eDTO.setTaskId(e.getTaskId());
	eDTO.setParameters(e.getParameters());
	return eDTO;
    }

    public ElaborationEntity toEntity(Elaboration e) {
	ElaborationEntity persist = new ElaborationEntity("elb-" + UUID.randomUUID().toString(), e.getName(),
		e.getProjectId());
	persist.setArtifactName(e.getArtifactName());
	persist.setCoreId(e.getCoreId());
	persist.setEventDate(e.getEventDate());
	persist.setGeomtry(e.getGeomtry());
	persist.setLink(e.getLink());
	persist.setStatus(e.getStatus());
	persist.setType(e.getType());
	persist.setWorkflowId(e.getWorkflowId());
	persist.setParameters(e.getParameters());
	persist.setTaskId(e.getTaskId());
	return persist;
    }

    public Elaboration toElaboration(ElaborationEntity ei) {
	Elaboration e = new Elaboration();
	e.setProjectId(ei.getProject());
	e.setArtifactName(ei.getArtifactName());
	e.setCoreId(ei.getCoreId());
	e.setEventDate(ei.getEventDate());
	e.setGeomtry(ei.getGeomtry());
	e.setId(ei.getId());
	e.setLink(ei.getLink());
	e.setName(ei.getName());
	e.setStatus(ei.getStatus());
	e.setType(ei.getType());
	e.setWorkflowId(ei.getWorkflowId());
	e.setTaskId(ei.getTaskId());
	e.setParameters(ei.getParameters());
	return e;
    }

    public ElaborationTemplate toTemplate(Elaboration e) {
	ElaborationTemplate t = new ElaborationTemplate();
	t.setName(e.getName());
	t.setProjectId(e.getProjectId());
	t.setWorkflowId(e.getWorkflowId());
	t.setTaskId(e.getTaskId());
	t.setParameters(e.getParameters());
	return t;
    }

    public String toJsonString(DHRun input) throws JsonProcessingException {
	return mapper.writeValueAsString(input);
    }

    public DHRun toEntity(String res, Class<DHRun> class1) throws JsonProcessingException {
	return mapper.readValue(res, class1);
    }

}
