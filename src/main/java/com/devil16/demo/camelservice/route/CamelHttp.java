package com.devil16.demo.camelservice.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.devil16.demo.camelservice.dto.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeDetails extends RouteBuilder {
	
	@Autowired
	private ObjectMapper objectMapper;
	private JacksonDataFormat employeesDataFormat;
	
	@PostConstruct
	public void setUp() {
		log.info("autowired : {}", this.objectMapper);
		this.employeesDataFormat = new ListJacksonDataFormat(objectMapper, Employee.class);
	}
	
	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.auto)
        ;
		
		rest("/camel-servlet")
		.get("/get-employees")
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.outType(Employee[].class)
		.to("direct:getAllEmployees")
		;
		
		from("direct:getAllEmployees")
		.log("Received message: ${body}")
		.removeHeaders("*")
		.setHeader(Exchange.HTTP_METHOD, simple("GET"))
		.to("http://localhost:8081/EmployeeDetails/v1/getEmployees")
		.log("Received message from service: ${body}")
		//.unmarshal(employeesDataFormat)
		.log("Received message post unmarshal: ${body}")
		.end()
		;
		
	}

}