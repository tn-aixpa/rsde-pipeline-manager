package it.smartcommunitylabdhlab.rsde.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import it.smartcommunitylabdhlab.rsde.common.bean.Elaboration;
import it.smartcommunitylabdhlab.rsde.core.configuration.Config;

@Component
public class ConfigurationManager {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
    private ObjectMapper mapper;

    @Value("${core.configUrl}")
    private String configPath;

    private List<Elaboration> templates = new ArrayList<>();

    @PostConstruct()
    private void init() throws FileNotFoundException {
	mapper = new ObjectMapper(new YAMLFactory());
	mapper.findAndRegisterModules();
	parseElaborationonfiguration(new FileInputStream(configPath));
    }

    public void parseElaborationonfiguration(InputStream source) {
	try {
	    Config config = mapper.readValue(source, Config.class);
	    this.templates = config.getElaborations();
	} catch (IOException e) {
	    logger.error(e.getMessage());
	}

    }

    public List<Elaboration> getTemplates() {
	return templates;
    }

}
