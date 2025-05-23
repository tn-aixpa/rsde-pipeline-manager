package it.smartcommunitylabdhlab.rsde.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("mapConverter")
@Converter(autoApply = false)
public class MapAttributeConverter implements AttributeConverter<Map<String, String>, byte[]> {

    private final Logger logger = LoggerFactory.getLogger(MapAttributeConverter.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
    };

    @Override
    public byte[] convertToDatabaseColumn(Map<String, String> map) {
	byte[] value = null;
	if (map != null) {
	    try {
		value = mapper.writeValueAsBytes(map);
	    } catch (JsonProcessingException e) {
		logger.error("error converting map: {}", e.getMessage());
	    }
	}

	return value;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(byte[] source) {
	Map<String, String> value = null;

	if (source != null) {
	    try {
		value = mapper.readValue(source, typeRef);
	    } catch (IOException e) {
		logger.error("error reading map from bytes: {}", e.getMessage());
	    }
	}
	return value;
    }
}