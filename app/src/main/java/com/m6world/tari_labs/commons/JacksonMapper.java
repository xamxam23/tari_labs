package com.m6world.tari_labs.commons;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonMapper {
    private final ObjectMapper mapper;

    public JacksonMapper() {
        mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public ObjectMapper getObjectMapper() {
        return mapper;
    }

    public <T> T toObject(String json, Class<T> aClass) throws IOException {
        return getObjectMapper().readValue(json, aClass);
    }

    public  String toJson(Object object) throws IOException {
        return getObjectMapper().writeValueAsString(object);
    }
}