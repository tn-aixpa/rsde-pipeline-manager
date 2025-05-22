package it.smartcommunitylabdhlab.rsde.persistence;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "elaborations")
public class ElaborationEntity {

    @Id
    @NotNull
    @Column(length = 128, unique = true)
    private String id;
    private String project;
    private String name;
    private String coreId;
    private String workflowId;
    private String taskId;
    @Lob
    @Column(name = "parameters")
    @Convert(converter = MapAttributeConverter.class)
    private Map<String, String> parameters;
    @Lob
    @Column(name = "inputs")
    @Convert(converter = MapAttributeConverter.class)
    private Map<String, String> inputs;
    private String geomtry;
    private Date eventDate;
    private String type;
    private String status;
    private String artifactName;
    private String link;

    public ElaborationEntity() {
    }

    public ElaborationEntity(String id, String name, String project) {
	super();
	this.id = id;
	this.name = name;
	this.project = project;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getProject() {
	return project;
    }

    public void setProject(String project) {
	this.project = project;
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

    public String getTaskId() {
	return taskId;
    }

    public void setTaskId(String taskId) {
	this.taskId = taskId;
    }

    public void setWorkflowId(String workflowId) {
	this.workflowId = workflowId;
    }

    public String getGeomtry() {
	return geomtry;
    }

    public void setGeomtry(String geomtry) {
	this.geomtry = geomtry;
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

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getArtifactName() {
	return artifactName;
    }

    public void setArtifactName(String artifactName) {
	this.artifactName = artifactName;
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

    public Map<String, String> getInputs() {
	return inputs;
    }

    public void setInputs(Map<String, String> inputs) {
	this.inputs = inputs;
    }

}
