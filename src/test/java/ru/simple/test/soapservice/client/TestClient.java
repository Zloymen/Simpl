package ru.simple.test.soapservice.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import ru.simpl_group.service.ws.System;
import ru.simpl_group.service.ws.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.UUID;

@Slf4j
public class TestClient {

    private SimpleTestSoapService soapService;

    private final ObjectFactory objectFactory = new ObjectFactory();

    @Before
    public void init(){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(SimpleTestSoapService.class);
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/soap-api/ws");
        soapService = (SimpleTestSoapService) jaxWsProxyFactoryBean.create();
    }

    @Test
    public void testGetApplications(){
        GetApplicationsRequest request = objectFactory.createGetApplicationsRequest();

        request.setCreateFrom(toGregorianCalendar(LocalDate.now().atStartOfDay()));
        request.setCreateTo(toGregorianCalendar(LocalDate.now().atTime(23, 59)));

        GetApplicationsResponse response = soapService.getApplications(request);

        log.info("{}", response.getApplicationResult().size());
    }

    @Test
    public void testCreateApplication(){
        CreateApplicationRequest request = objectFactory.createCreateApplicationRequest();

        Application application = objectFactory.createApplication();
        System system = objectFactory.createSystem();

        application.setFio("X X X");
        application.setNumber(UUID.randomUUID().toString());
        application.setSystem(system);

        system.setIp("1.1.1.1");
        system.setLogin("login");
        system.setWrite(true);

        request.setApplication(application);

        try {
            CreateApplicationResponse response = soapService.createApplication(request);
            log.info("id: {}, {}", response.getId(), response.getCreated());
        }catch (CreateApplicationFault ex){
            log.error("error:", ex);
            FaultDetail faultInfo = ex.getFaultInfo();
            log.info("{} {}", faultInfo.getCode(), faultInfo.getDetail());
        }
    }

    @SneakyThrows
    private XMLGregorianCalendar toGregorianCalendar(LocalDateTime dateTime){
        GregorianCalendar gcal = GregorianCalendar.from(dateTime.atZone(ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    }
}
