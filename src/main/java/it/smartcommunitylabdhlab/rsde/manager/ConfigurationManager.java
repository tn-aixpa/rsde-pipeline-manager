package it.smartcommunitylabdhlab.rsde.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import it.smartcommunitylabdhlab.rsde.common.bean.ElaborationTemplate;
import it.smartcommunitylabdhlab.rsde.core.configuration.Config;

@Component
public class ConfigurationManager {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
    private ObjectMapper mapper;

    @Value("${core.configUrl}")
    private String configPath;

    private Map<String, ElaborationTemplate> templates = new HashMap<>();

    @PostConstruct()
    private void init() throws FileNotFoundException {
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        parseElaborationonfiguration(new FileInputStream(configPath));
    }

    public void parseElaborationonfiguration(InputStream source) {
        try {
            Config config = mapper.readValue(source, Config.class);
            config.getElaborations().forEach(t -> this.templates.put(t.getName(), t));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public ElaborationTemplate getTemplate(String name) {
        return templates.get(name);
    }

    public List<ElaborationTemplate> getTemplates() {
        return new LinkedList<>(templates.values());
    }

}
