package com.devil16.demo.camelservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.soap.SoapDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.devil16.demo.camelservice.dto.NumberToWords;
import com.devil16.demo.camelservice.dto.NumberToWordsResponse;

@Component
public class CamelSoap  extends RouteBuilder {
	
	@Autowired
	SoapDataFormat soapDataFormat;
	
	@Override
	public void configure() throws Exception {
		
		rest("/camel-servlet")
		.post("/marshall-unmarshall-xml")
		.consumes(MediaType.APPLICATION_XML_VALUE)
		.produces(MediaType.APPLICATION_XML_VALUE)
		.to("direct:unmarshallXml")
		;
		
		from("direct:unmarshallXml")
		.log("before unmarshall : ${body}")
		.unmarshal(soapDataFormat)
		.log("post unmarshall : ${body}")
		.process(exchange -> {
			NumberToWordsResponse numberToWordsResponse = new NumberToWordsResponse();
			numberToWordsResponse.setNumberToWordsResult(exchange.getMessage().getBody(NumberToWords.class).getUbiNum().toString());
			exchange.getMessage().setBody(numberToWordsResponse);
		})
		.marshal(soapDataFormat)
		.end()
		;
		
	}
	
}
