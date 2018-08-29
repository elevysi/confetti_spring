package com.elevysi.site.blog.config.ws.soap.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.elevysi.site.blog.ws.soap.consumer.client.ArticleClient;

//@Configuration
public class SoapClientConfig {

//	@Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        // this is the package name specified in the <generatePackage> specified in pom.xml
//        marshaller.setContextPath("com.elevysi.site.ws.shop.soap.consumer");
//        return marshaller;
//    }
// 
//    @Bean
//    public SOAPConnector soapConnector(Jaxb2Marshaller marshaller) {
//        SOAPConnector client = new SOAPConnector();
//        client.setDefaultUri("http://localhost:8000/ws/articleDetailsWsdl.wsdl");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
//    
//    @Bean
//    public ArticleClient articleClient(Jaxb2Marshaller marshaller){
//    	ArticleClient client = new ArticleClient();
//    	client.setDefaultUri("http://localhost:8000/ws/articleDetailsWsdl.wsdl");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    	
//    }
}
//http://concretepage.com/soap/getStudentRequest
//http://localhost:8080/spring4soap-1/soapws/students.wsdl
//http://localhost:8080/spring4soap-1/soapws/getStudentResponse