package com.home.dataprocessing.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.AbstractBeanField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonToMapConverter extends AbstractBeanField<Map<String, String>, String> {

    private static final Logger logger = LoggerFactory.getLogger(JsonToMapConverter.class);


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected Map<String, Object> convert(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        value = value.trim();
        if (!value.startsWith("{") || !value.endsWith("}")) {
            logger.info("Skipping invalid JSON: " + value);
            return null;
        }

        value = value.replace("\"\"", "\"");

        try {
            return objectMapper.readValue(value, Map.class);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert JSON to Map: " + value, e);
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error while converting JSON to Map: " + value, e);
            return null;
        }
    }
}
