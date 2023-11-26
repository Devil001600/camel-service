package com.devil16.demo.camelservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.devil16.demo.camelservice.dto.Employee;


/**
 * CamelServlet class - 
 * 
 * demos how a camel-service can be exposed as a REST service;
 * uses camel's <a href="https://camel.apache.org/manual/rest-dsl.html">REST DSL</a> implemented with <a href="https://camel.apache.org/components//servlet-component.html">camel-servlet</a>
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2023-10-31
 * 
 * @see com.devil16.demo.employee.service.EmployeeService
 */

@Component
public class CamelServlet extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		.component("servlet")
		;
		
		rest()
		.get("/camel-servlet/hello")
			.produces(MediaType.TEXT_PLAIN_VALUE)
			.outType(String.class)
			.param()
			.name("name")
			.type(RestParamType.path)
			.endParam()
			.to("direct:hello-camel-servlet")
		.post("/camel-soap/marshall-unmarshall-soap-xml")
			.consumes(MediaType.APPLICATION_XML_VALUE)
			.produces(MediaType.APPLICATION_XML_VALUE)
			.to("direct:marshall-unmarshall-soap-xml")
		.get("/camel-http/employees")
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.bindingMode(RestBindingMode.json)
			.outType(Employee.class)
			.to("direct:getAllEmployees")
		;
		
		from("direct:hello-camel-servlet").setBody(simple("CamelServlet says Hello, ${header.name}"));
		
	}
	
}
