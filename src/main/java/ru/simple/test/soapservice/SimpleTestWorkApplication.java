package ru.simple.test.soapservice;


import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.simpl_group.service.ws.SimpleTestSoapService;
import ru.simple.test.soapservice.filter.LoggingFilter;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.simple.test.soapservice.data")
public class SimpleTestWorkApplication {

    @Bean
    public DispatcherServletPath dispatcherServletPathProvider() {
        return () -> "";
    }

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
        return new FilterRegistrationBean<>(new LoggingFilter(), dispatcherServlet());
    }

    @Bean
    public ServletRegistrationBean<CXFServlet> dispatcherServlet() {

        return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
    }

    @Bean
    public List<Feature> features(){

        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);

        return Arrays.asList(loggingFeature);
    }

    @Bean
    public Endpoint endpoint(Bus bus, SimpleTestSoapService soapService) {
        EndpointImpl endpoint = new EndpointImpl(bus, soapService);
        endpoint.setFeatures(features());
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
