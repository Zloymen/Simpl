package ru.simple.test.soapservice;


import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.simpl_group.service.ws.SimpleTestSoapService;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.simple.test.soapservice.data")
public class SimpleTestWorkApplication {

    @Bean
    public Endpoint endpoint(Bus bus, SimpleTestSoapService soapService) {
        EndpointImpl endpoint = new EndpointImpl(bus, soapService);
        endpoint.publish("/ws");
        return endpoint;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleTestWorkApplication.class, args);
    }

    @Bean

    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceXmlLocation("classpath:orm/persistence.xml");

        return em;
    }
}
