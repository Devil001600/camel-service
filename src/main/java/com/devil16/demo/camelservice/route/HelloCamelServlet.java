package com.devil16.demo.camelservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HelloCamelServletRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		rest("/camel-servlet").
		get("/helloCamelServlet").
		produces(MediaType.TEXT_PLAIN_VALUE).
		outType(String.class).
		param().
		name("name").
		type(RestParamType.path).
		endParam().
		to("direct:helloCamelServlet");
		
		from("direct:helloCamelServlet").setBody(simple("CamelServlet says Hello, ${header.name}"));
		
	}
	
}
