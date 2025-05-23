package it.smartcommunitylabdhlab.rsde.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;
import it.smartcommunitylabdhlab.rsde.manager.ConfigurationManager;

@RestController
@RequestMapping("/api/templates")
public class ElaborationTemplateController {

    @Autowired
    private ConfigurationManager configManager;
   
    @GetMapping(produces = { "application/json" })
    public List<ElaborationTemplate> readAll() {
        return configManager.getTemplates();
    }

}
