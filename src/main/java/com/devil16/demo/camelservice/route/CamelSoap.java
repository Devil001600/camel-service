package com.devil16.demo.camelservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.soap.SoapDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.devil16.demo.camelservice.dto.NumberToWords;
import com.devil16.demo.camelservice.dto.NumberToWordsResponse;

@Component
public class CamelSoap  extends RouteBuilder {
	
	@Autowired
	@Qualifier ("soap11DataFormat")
	SoapDataFormat soap11DataFormat;
	
	@Autowired
	@Qualifier ("soap12DataFormat")
	SoapDataFormat soap12DataFormat;
	
	@Override
	public void configure() throws Exception {
		
		rest("/camel-soap")
		.post("/marshall-unmarshall-soap-xml")
		.consumes(MediaType.APPLICATION_XML_VALUE)
		.produces(MediaType.APPLICATION_XML_VALUE)
		.to("direct:marshall-unmarshall-soap-xml")
		;
		
		from("direct:marshall-unmarshall-soap-xml")
		.choice()
			.when(simple("${header.soap} == '1.2'"))
				.to("direct:marshall-unmarshall-soap12-xml")
			.otherwise()
				.to("direct:marshall-unmarshall-soap11-xml")
		.endChoice()
		;
		
		from("direct:marshall-unmarshall-soap11-xml")
		.log("before unmarshall : ${body}")
		.unmarshal(soap11DataFormat)
		.log("post unmarshall : ${body}")
		.process(exchange -> {
			NumberToWordsResponse numberToWordsResponse = new NumberToWordsResponse();
			numberToWordsResponse.setNumberToWordsResult(exchange.getMessage().getBody(NumberToWords.class).getUbiNum().toString());
			exchange.getMessage().setBody(numberToWordsResponse);
		})
		.marshal(soap11DataFormat)
		.end()
		;
		
		from("direct:marshall-unmarshall-soap12-xml")
		.log("before unmarshall : ${body}")
		.unmarshal(soap12DataFormat)
		.log("post unmarshall : ${body}")
		.process(exchange -> {
			NumberToWordsResponse numberToWordsResponse = new NumberToWordsResponse();
			numberToWordsResponse.setNumberToWordsResult(exchange.getMessage().getBody(NumberToWords.class).getUbiNum().toString());
			exchange.getMessage().setBody(numberToWordsResponse);
		})
		.marshal(soap12DataFormat)
		.end()
		;
		
	}
	
}
