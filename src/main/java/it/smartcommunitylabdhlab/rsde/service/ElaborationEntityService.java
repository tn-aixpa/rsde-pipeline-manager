package it.smartcommunitylabdhlab.rsde.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationEntity;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationRepository;

/*
 * Manage persistence for elaboration entities.
 */
@Service
@Transactional
public class ElaborationEntityService {

    @Autowired
    private ElaborationRepository elRepository;

    public ElaborationEntity save(ElaborationEntity obj) {
	return elRepository.save(obj);
    }

    @Transactional(readOnly = true)
    public ElaborationEntity fetch(String id) throws NoSuchElaborationException {
	Optional<ElaborationEntity> e = elRepository.findById(id);
	if (!e.isPresent()) {
	    throw new NoSuchElaborationException("no elaboration found " + id);
	}
	return e.get();
    }

    public List<ElaborationEntity> loadAll() {
	return elRepository.findAll();
    }

    public void delete(String id) {
	elRepository.deleteById(id);	
    }
    
    public ElaborationEntity searchByWorkFlowId(String workflowId) {
	return elRepository.findByworkflowId(workflowId);
    }

}
