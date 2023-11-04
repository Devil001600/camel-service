package com.devil16.demo.camelservice.route;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.devil16.demo.camelservice.dto.Employee;
import com.devil16.demo.camelservice.dto.Employees;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeServiceIntegrationRoute extends RouteBuilder {
	
	JacksonDataFormat employeesDataFormat = new JacksonDataFormat(JsonMapper.builder()
		    .addModule(new JavaTimeModule())
		    .build(), Employees.class);
	
	
	@Override
	public void configure() throws Exception {

		rest("/camel-servlet").
		get("/get-employees").
		bindingMode(RestBindingMode.json).
		produces(MediaType.APPLICATION_JSON_VALUE).
		outType(Employees.class).
		to("direct:getAllEmployees");
		
		from("direct:getAllEmployees").
		log("Received message: ${body}").
		removeHeaders("*").
		setHeader(Exchange.HTTP_METHOD, simple("GET")).
		to("http://localhost:8081/EmployeeDetails/v1/getEmployees").
		log("Received message from service: ${body}").
		unmarshal(employeesDataFormat).
		log("Received message from demarshalling: ${body}").transform(simple("${body}")).
		setBody(simple("${body}"));
		
	}

}