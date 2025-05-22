package it.smartcommunitylabdhlab.rsde.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationDTO;
import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.manager.ElaborationManager;
import it.smartcommunitylabdhlab.rsde.utils.Converter;

@RestController
@RequestMapping("/elaboration")
public class ElaborationController {
    
    @Autowired
    private ElaborationManager elaManager;

    @Autowired
    private Converter converter;

    @PostMapping(consumes = { "application/json" }, produces = { "application/json" })
    public ElaborationDTO create(@RequestBody Elaboration elaboration) throws NoSuchElaborationException {
	Elaboration res = elaManager.createElaboration(elaboration);
	return converter.toDTO(res);
    }

    @GetMapping(value = "/{id}", produces = { "application/json" })
    public ElaborationDTO read(@PathVariable String id) throws NoSuchElaborationException {
	Elaboration e = elaManager.loadElaborationById(id);
	return e == null ? null : converter.toDTO(e);
    }

    @DeleteMapping(value = "/{id}", produces = { "application/json" })
    public void delete(@PathVariable String id) throws NoSuchElaborationException {
	elaManager.deleteElaboration(id);
    }
    
    @GetMapping(produces = { "application/json" })
    public List<ElaborationDTO> readAll() {
	List<ElaborationDTO> r = new ArrayList<>();
	for (Elaboration e : elaManager.loadElaborations()) {
	    r.add(converter.toDTO(e));
	}
	return r;
    }

    @PostMapping("/start/{id}")
    public String start(@PathVariable String id) throws NoSuchElaborationException {
	return elaManager.invoke(id);
    }

    @PostMapping("/stop")
    public String stop(@RequestBody String id) throws NoSuchElaborationException {
	return elaManager.invoke(id);
    }

}
