package it.smartcommunitylabdhlab.rsde.manager;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;
import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.service.ElaborationService;

@Component
public class ElaborationManager {

    private final Logger logger = LoggerFactory.getLogger(ElaborationManager.class);

    @Autowired
    private ElaborationService elaborationService;

	@Autowired
	private ConfigurationManager configManager;

    public Elaboration saveElaboration(Elaboration elaboration) throws NoSuchElaborationException {
		ElaborationTemplate template = configManager.getTemplate(elaboration.getName());
		if (template == null) {
			throw new NoSuchElaborationException("no template found for " + elaboration.getName());
		}
		if (!elaborationService.exists(elaboration)) {
		// 1. check for existing elaboration throw error in case elaboration with same name, artifactName.
			// 2. further check the status of DHCORE 	
			// elaborationService.getDHCoreElaboration()
			// if new, launch elaboration on DHCORE.//	Elaboration ??
			Elaboration e = elaborationService.launchRemoteElaboration(elaboration, template);		
			// persist.
			elaboration.setCreatedAt(new Date());
			elaboration.setUpdatedAt(elaboration.getCreatedAt());
			String eId = elaborationService.save(e, template);	
			return elaborationService.getElaborationById(eId);    
		}
		throw new RuntimeException("Elaboration exists");
    }

    public Elaboration loadElaborationById(String id) throws NoSuchElaborationException {
		return elaborationService.getElaborationById(id);
    }

    public void deleteElaboration(String id) throws NoSuchElaborationException {
		if (id != null) {
			elaborationService.deleteById(id);           
		} else {
			throw new NoSuchElaborationException(id);
		}
    }

    public Elaboration[] loadElaborationById() {
		// TODO Auto-generated method stub
		return null;
    }

    public Page<Elaboration> loadElaborations(Pageable pageable, String tag) {
		return elaborationService.loadAll(pageable, tag);
    }

    public String getDownloadLink(String id, String name) throws NoSuchElaborationException {
		return elaborationService.generatePresignedUrl(id, name);
    }

}
