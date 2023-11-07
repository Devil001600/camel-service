package com.devil16.demo.camelservice.config;

import java.util.HashMap;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class OjectMapperConfig {
	@Bean(name = "objectMapper")
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		return objectMapper;
	}
	
	@Bean(name = "json-jackson")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public JacksonDataFormat jacksonDataFormat(@Qualifier ("objectMapper") ObjectMapper objectMapper) {
		JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(objectMapper, HashMap.class);
	    jacksonDataFormat
        .getObjectMapper()
        .findAndRegisterModules()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    jacksonDataFormat.addModule(new JavaTimeModule());
	    return jacksonDataFormat;
	}
}
