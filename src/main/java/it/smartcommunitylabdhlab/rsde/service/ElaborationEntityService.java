package it.smartcommunitylabdhlab.rsde.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ElaborationEntity> loadAll(Pageable pageable) {
    	return elRepository.findAll(pageable);
    }
    public Page<ElaborationEntity> findByTag(Pageable pageable, String tag) {
    	return elRepository.findByTag(tag, pageable);
    }

    public void delete(String id) {
	    elRepository.deleteById(id);	
    }
    
    public ElaborationEntity searchByWorkFlowId(String workflowId) {
	    return elRepository.findByWorkflowId(workflowId);
    }

    public List<ElaborationEntity> searchByStatus(String status) {
	    return elRepository.findByStatus(status);
    }

    public Object findById(String id) {
        return elRepository.findById(id).orElse(null);
    }

    public void saveEntity(ElaborationEntity e) {
	    elRepository.save(e);
    }

}
