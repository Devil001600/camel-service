package com.devil16.demo.camelservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.dataformat.soap.SoapDataFormat;
import org.apache.camel.dataformat.soap.name.TypeNameStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class OjectMapperConfig {

    @Bean(name = "objectMapper")
    @Primary
    ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		return objectMapper;
	}

    @Bean(name = "soapDataFormat")
    @Primary
    SoapDataFormat soapDataFormat() {
		
		SoapDataFormat soapDataFormat = new SoapDataFormat();

		Map<String, String> jaxbNameSapcePrefix = new HashMap<String, String>();
		jaxbNameSapcePrefix.put("http://schemas.xmlsoap.org/soap/envelope/", "soapenv");
		jaxbNameSapcePrefix.put("http://www.w3.org/2003/05/soap-envelope", "soap12");
		jaxbNameSapcePrefix.put("http://www.dataaccess.com/webservicesserver/", "web");

		soapDataFormat.setElementNameStrategy(new TypeNameStrategy());

		soapDataFormat.setContextPath("com.devil16.demo.camelservice.dto");
		soapDataFormat.setVersion("1.2");
		soapDataFormat.setNamespacePrefix(jaxbNameSapcePrefix);
//		soapDataFormat.setSchema("classpath:data-access-number-conversion.xsd");
			
		return soapDataFormat;
	}
	
}
