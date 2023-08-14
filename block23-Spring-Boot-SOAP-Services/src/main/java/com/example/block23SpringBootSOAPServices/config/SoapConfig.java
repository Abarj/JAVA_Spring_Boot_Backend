package com.example.block23SpringBootSOAPServices.config;

import com.example.block23SpringBootSOAPServices.client.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    /*Los servicios web utilizan lenguajes de transferencia de datos como REST(json) o SOAP(xml) para comunicarse con la aplicación
    La aplicación únicamente utiliza Java por lo que para que entre ambos se entiendan es necesario traducir el XML o Json en clase Java
    El proceso de traducir XML a Java se conoce como Unmarshalling
    El proceso de traducir Java a XML se conoce como Marshalling
    La clase que se encarga de realizar esta traducción es Jaxb2Marshaller (XML->Java - Java->XML)
    El proceso de traducir Json a Java se conoce como Deserialización
    El proceso de traducir Java a Json se conoce como Serialización
    La clase que se encarga de realizar esta traducción es JacksonSerialize (Json->Java - Java-Json)
    Jaxb2Marshaller y JacksonSerialize forman parte del Core de Spring*/
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.block23SpringBootSOAPServices.wsdl");
        return marshaller;
    }

    @Bean
    public SoapClient getSoapClient(Jaxb2Marshaller marshaller) {
        SoapClient soapClient = new SoapClient();
        soapClient.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        soapClient.setMarshaller(marshaller);
        soapClient.setUnmarshaller(marshaller);

        return soapClient;
    }
}
