package it.smartcommunitylabdhlab.rsde.common.bean;

import java.util.Date;
import java.util.Map;

public class Elaboration {

    private String id;
    private String projectId;
    private String name;
    private String coreId;
    private String workflowId;
    private String taskId;
    private String geomtry;
    private Date eventDate;
    private String type;
    private String status;
    private String artifactName;
    private String link;
    private Map<String, String> parameters;

    public Elaboration() {
    }

    public Elaboration(String id, String projectId, String name, String workflow, String taskId, String task,
	    String geomtry, String artifactName, Date eventDate, String type) {
	super();
	this.id = id;
	this.name = name;
	this.projectId = projectId;
	this.workflowId = workflow;
	this.taskId = taskId;
	this.workflowId = task;
	this.geomtry = geomtry;
	this.artifactName = artifactName;
	this.eventDate = eventDate;
	this.type = type;
    }

    public String getProjectId() {
	return projectId;
    }

    public void setProjectId(String projectId) {
	this.projectId = projectId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
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

    public String getLink() {
	return link;
    }

    public void setLink(String link) {
	this.link = link;
    }

    public Map<String, String> getParameters() {
	return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
	this.parameters = parameters;
    }

}
