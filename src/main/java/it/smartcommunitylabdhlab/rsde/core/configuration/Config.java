package it.smartcommunitylabdhlab.rsde.core.configuration;

import java.util.List;

import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;

public class Config {

    private List<ElaborationTemplate> elaborations;

    public List<ElaborationTemplate> getElaborations() {
	return elaborations;
    }

    public void setElaborations(List<ElaborationTemplate> elaborations) {
	this.elaborations = elaborations;
    }

}
