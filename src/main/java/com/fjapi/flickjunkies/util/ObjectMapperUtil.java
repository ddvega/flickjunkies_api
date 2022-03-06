package com.fjapi.flickjunkies.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.GenericDeclaration;

public class ObjectMapperUtil<T>{

    final Class<T> typeParameterClass;
    ObjectMapper objectMapper;

    public ObjectMapperUtil(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    // private Class<T> type;
//    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
//            false);
//    MovieSearch movieSearch = objectMapper.readValue(payload, MovieSearch.class);


    public T payload2Object(String payload) throws JsonProcessingException {
        return objectMapper.readValue(payload, typeParameterClass);
    }
}
