package dvdrental.sifat.config;

import dvdrental.sifat.soap.RentService;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class RentalServiceConfig {
    @Autowired
    private SpringBus springBus;

    @Autowired
    private RentService rentService;

    @Bean
    public Endpoint filmEndPoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus, rentService);
        endpoint.publish("/rent");
        return endpoint;

    }
}
