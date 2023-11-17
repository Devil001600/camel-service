package com.devil16.demo.camelservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


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
		//.bindingMode(RestBindingMode.auto)
		;
		
		rest("/camel-servlet")
		.get("/hello")
		.produces(MediaType.TEXT_PLAIN_VALUE)
		.outType(String.class)
		.param()
		.name("name")
		.type(RestParamType.path)
		.endParam()
		.to("direct:helloCamelServlet")
		;
		
		from("direct:helloCamelServlet").setBody(simple("CamelServlet says Hello, ${header.name}"));
		
	}
	
}
