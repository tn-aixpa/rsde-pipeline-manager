package it.smartcommunitylabdhlab.rsde.core.configuration;

import java.util.List;

import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;

public class Config {

    private List<Elaboration> elaborations;

    public List<Elaboration> getElaborations() {
	return elaborations;
    }

    public void setElaborations(List<Elaboration> elaborations) {
	this.elaborations = elaborations;
    }

}
