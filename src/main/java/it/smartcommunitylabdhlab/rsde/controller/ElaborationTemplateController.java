package it.smartcommunitylabdhlab.rsde.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;
import it.smartcommunitylabdhlab.rsde.manager.ConfigurationManager;
import it.smartcommunitylabdhlab.rsde.utils.Converter;

@RestController
@RequestMapping("/templates")
public class ElaborationTemplateController {

    @Autowired
    private ConfigurationManager configManager;
    
    @Autowired
    private Converter converter;
    
    @GetMapping(produces = { "application/json" })
    public List<ElaborationTemplate> readAll() {
	List<ElaborationTemplate> r = new ArrayList<>();
	for (Elaboration e : configManager.getTemplates()) {
	    r.add(converter.toTemplate(e));
	}
	return r;
    }

}
