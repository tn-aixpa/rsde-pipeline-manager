package it.smartcommunitylabdhlab.rsde.common.bean;

import java.util.Map;

public class ElaborationTemplate {

    private String projectId;
    private String name;
    private String taskId;
    private String workflowId;
    private Map<String, String> parameters = new java.util.HashMap<>();

    public ElaborationTemplate() {
	super();
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

    public String getTaskId() {
	return taskId;
    }

    public void setTaskId(String taskId) {
	this.taskId = taskId;
    }

    public String getWorkflowId() {
	return workflowId;
    }

    public void setWorkflowId(String workflow) {
	this.workflowId = workflow;
    }

    public Map<String, String> getParameters() {
	return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
	this.parameters = parameters;
    }

}
