package it.smartcommunitylabdhlab.rsde.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.exception.NoSuchElaborationException;
import it.smartcommunitylabdhlab.rsde.exception.SystemException;
import it.smartcommunitylabdhlab.rsde.service.ElaborationService;
import it.smartcommunitylabdhlab.rsde.utils.Converter;

@Component
public class ElaborationManager {

    private final Logger logger = LoggerFactory.getLogger(ElaborationManager.class);

    @Value("${core.token}")
    private String dhToken;

    @Value("${core.apiEndopint}")
    private String dhUrl;

    @Autowired
    private ElaborationService elaborationService;

    @Autowired
    private Converter converter;

    public String invoke(String id) throws NoSuchElaborationException {
	Elaboration saved = elaborationService.getElaborationById(id);
	if (saved != null) {
	    Elaboration e = elaborationService.launchRemoteElaboration(saved, dhUrl, dhToken);
	    return (e.getId() + " invoked");
	} 
	throw new NoSuchElaborationException(id);
    }

    public Elaboration createElaboration(Elaboration elaboration) throws NoSuchElaborationException {
	if (!elaborationService.exists(elaboration)) {
	 // 1. check for existing elaboration throw error in case elaboration with same name, artifactName.
		// 2. further check the status of DHCORE 	
		// elaborationService.getDHCoreElaboration()
		// if new, launch elaboration on DHCORE.//	Elaboration ??
		Elaboration e = elaborationService.launchRemoteElaboration(elaboration, dhUrl, dhToken);		
		// persist.
		String eId = elaborationService.save(e);	
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

    public List<Elaboration> loadElaborations() {
	return elaborationService.loadAll();
    }

}
