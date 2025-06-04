package it.smartcommunitylabdhlab.rsde.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationEntity;
import it.smartcommunitylabdhlab.rsde.persistence.ElaborationRepository;
import jakarta.annotation.PostConstruct;

/*
 * Manage persistence for elaboration entities.
 */
@Service
@Transactional
public class ElaborationEntityService {

    @Autowired
    private ElaborationRepository elRepository;

    @PostConstruct
    public void init() {
        if (elRepository.findById("1").isPresent()) {
            ElaborationEntity entity = new ElaborationEntity();
            entity.setId("1");
            entity.setName("forest");
            entity.setLocalName("deforestation-2018-2019");
            entity.setProject("deforestation");
            entity.setCoreId("8c76c182ced845a19d3adc15c3618ed4");
            entity.setCreatedAt(new Date());
            entity.setUpdatedAt(new Date());
            entity.setStatus("COMPLETED");
            entity.setWorkflowId("kfp://deforestation/pipeline_deforestation:679e3b35eb604fbea622427e0a5cfb42");
            entity.setTaskId("kfp+pipeline://deforestation/72b25d056799453da82cbfb24a0b89ef");
            entity.setTag("forest");
            Map<String, String> map = new HashMap<>();
            map.put("startYear", "2018");
            map.put("endYear", "2019");
            map.put("input1", "bosco");
            map.put("geometry", "POLYGON((11.031183173429909 46.088357949131584, 11.031923564323543 46.08843607921081, 11.031708958267417 46.08796357656629, 11.031183173429909 46.088357949131584))");
            map.put("outputName", "pipeline_forest_output21_19");
            map.put("input2", "data_s2_deforestation");

            entity.setParameters(map);
            
            elRepository.save(entity);            
        }
    }

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

    public ElaborationEntity findById(String id) {
        return elRepository.findById(id).orElse(null);
    }

    public void saveEntity(ElaborationEntity e) {
	    elRepository.save(e);
    }

}
