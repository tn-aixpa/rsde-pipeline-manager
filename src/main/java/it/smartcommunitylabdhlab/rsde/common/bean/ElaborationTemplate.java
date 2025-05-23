package it.smartcommunitylabdhlab.rsde.common.bean;

import java.util.Map;

public class ElaborationTemplate {

    private String projectId;
    private String name;
    private String taskId;
    private String workflowId;
    private String tag;
    private Map<String, String> fixedParameters; 

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getFixedParameters() {
        return fixedParameters;
    }
    public void setFixedParameters(Map<String, String> fixedParameters) {
        this.fixedParameters = fixedParameters;
    }

}
