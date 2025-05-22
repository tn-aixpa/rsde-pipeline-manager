package it.smartcommunitylabdhlab.rsde.common.bean;

import java.util.Date;
import java.util.Map;

public class ElaborationDTO {

    private String id;
    private String name;
    private String coreId;
    private String workflowId;
    private String taskId;
    private String geomtry;
    private Date eventDate;
    private String type;
    private String status;
    private String artifactName;
    private String artifactLink;
    private Map<String, String> parameters;

    public ElaborationDTO() {
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getArtifactName() {
	return artifactName;
    }

    public void setArtifactName(String artifactName) {
	this.artifactName = artifactName;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getArtifactLink() {
	return artifactLink;
    }

    public void setArtifactLink(String artifactLink) {
	this.artifactLink = artifactLink;
    }

    public Date getEventDate() {
	return eventDate;
    }

    public void setEventDate(Date eventDate) {
	this.eventDate = eventDate;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCoreId() {
	return coreId;
    }

    public void setCoreId(String coreId) {
	this.coreId = coreId;
    }

    public String getWorkflowId() {
	return workflowId;
    }

    public void setWorkflowId(String workflowId) {
	this.workflowId = workflowId;
    }

    public String getTaskId() {
	return taskId;
    }

    public void setTaskId(String taskId) {
	this.taskId = taskId;
    }

    public String getGeomtry() {
	return geomtry;
    }

    public void setGeomtry(String geomtry) {
	this.geomtry = geomtry;
    }

    public Map<String, String> getParameters() {
	return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
	this.parameters = parameters;
    }

}
