package it.smartcommunitylabdhlab.rsde.common.bean;

import java.util.Map;

public class Spec {
    private Map<String, String> outputs;
    private String task;
    private Map<String, String> inputs;
    private Map<String, String> parameters;
    private String workflow;

    public Spec() {
    }

    public Spec(String task, String workflow) {
	super();
	this.task = task;
	this.workflow = workflow;
    }

    public String getTask() {
	return task;
    }

    public void setTask(String task) {
	this.task = task;
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

    public Map<String, String> getOutputs() {
	return outputs;
    }

    public void setOutputs(Map<String, String> outputs) {
	this.outputs = outputs;
    }

    public String getWorkflow() {
	return workflow;
    }

    public void setWorkflow(String workflow) {
	this.workflow = workflow;
    }

}