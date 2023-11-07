package com.devil16.demo.camelservice.route;

import java.io.DataOutput;
import java.io.InputStream;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.devil16.demo.camelservice.dto.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeServiceIntegrationRoute extends RouteBuilder {
	
	@Autowired
	private ObjectMapper objectMapper;
	private JacksonDataFormat employeesDataFormat;
	
	@PostConstruct
	public void setUp() {
		log.info("autowired : {}", this.objectMapper);
		this.employeesDataFormat = new ListJacksonDataFormat(objectMapper, Employee.class);
		employeesDataFormat.addModule(new JavaTimeModule());
	}
	
	@Override
	public void configure() throws Exception {
		
		restConfiguration().
		component("servlet").
		bindingMode(RestBindingMode.auto)
//		.
//		dataFormatProperty("moduleclassnames", "com.fasterxml.jackson.datatype.jsr310.javatimemodule").
//      dataFormatProperty("disablefeatures", "write_dates_as_timestamps")
        ;
		
		rest("/camel-servlet").
		get("/get-employees").
		produces(MediaType.APPLICATION_JSON_VALUE).
		to("direct:getAllEmployees");
		
		from("direct:getAllEmployees").
		log("Received message: ${body}").
		removeHeaders("*").
		setHeader(Exchange.HTTP_METHOD, simple("GET")).
		to("http://localhost:8081/EmployeeDetails/v1/getEmployees").
		log("Received message from service: ${body}").
		unmarshal(employeesDataFormat).
//		unmarshal().json(JsonLibrary.Jackson, Employee[].class).
//		process(new Processor() {
//
//			@Override
//			public void process(Exchange exchange) throws Exception {
//				ObjectMapper customObjectMapper = new ObjectMapper();
//				customObjectMapper.registerModule(new JavaTimeModule());
//				
//				Employee[] employees = customObjectMapper.readValue(exchange.getMessage().getMandatoryBody(InputStream.class), Employee[].class);
//				log.info("de-serialized employees");
//				for (Employee employee : employees) {
//					log.info("{}", employee);
//				}
//				
//				exchange.getMessage().setBody(
//						customObjectMapper.
//						//writerWithDefaultPrettyPrinter().
//						writeValue((JsonGenerator)customObjectMapper.writer(),
//								employees
//								)
//						);
//			}
//			
//		}).
		log("Received message post unmarshal: ${body}").
		end();
		
	}

}