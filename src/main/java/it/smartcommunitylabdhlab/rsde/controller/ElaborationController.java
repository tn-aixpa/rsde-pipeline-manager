package it.smartcommunitylabdhlab.rsde.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.manager.ElaborationManager;

@RestController
@RequestMapping("/api/elaborations")
public class ElaborationController {
    
    @Autowired
    private ElaborationManager elaManager;


    @PostMapping(consumes = { "application/json" }, produces = { "application/json" })
    public Elaboration save(@RequestBody Elaboration elaboration) throws NoSuchElaborationException {
        Elaboration res = elaManager.saveElaboration(elaboration);
        return res;
    }

    @GetMapping(value = "/{id}", produces = { "application/json" })
    public Elaboration read(@PathVariable String id) throws NoSuchElaborationException {
        Elaboration e = elaManager.loadElaborationById(id);
        return e;
    }

    @DeleteMapping(value = "/{id}", produces = { "application/json" })
    public void delete(@PathVariable String id) throws NoSuchElaborationException {
	    elaManager.deleteElaboration(id);
    }
    
    @GetMapping(produces = { "application/json" })
    public Page<Elaboration> list(Pageable pageable, @RequestParam(required = false) String tag) {
        return elaManager.loadElaborations(pageable, tag);
    }
    
    @GetMapping(value = "/{id}/download/{name}")
    public ResponseEntity<String> downloadLink(@PathVariable String id, @PathVariable String name) throws NoSuchElaborationException {
        return ResponseEntity.ok(elaManager.getDownloadLink(id, name));
    }
}
