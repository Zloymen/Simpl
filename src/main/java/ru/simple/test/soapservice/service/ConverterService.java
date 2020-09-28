package ru.simple.test.soapservice.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.simpl_group.service.ws.System;
import ru.simpl_group.service.ws.*;
import ru.simple.test.soapservice.entity.ApplicationEntity;
import ru.simple.test.soapservice.entity.SystemEntity;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterService {

    public ApplicationEntity toEntity(Application application){

        ApplicationEntity entity = new ApplicationEntity();

        entity.setWrite(application.getSystem().isWrite());
        entity.setNumber(application.getNumber());
        entity.setComment(application.getSystem().getComment());
        entity.setFio(application.getFio());
        entity.setSystem(toEntity(application.getSystem()));

        return entity;
    }

    public SystemEntity toEntity(System system){
        SystemEntity systemEntity = new SystemEntity();

        systemEntity.setLogin(system.getLogin());
        systemEntity.setIp(system.getIp());

        return systemEntity;
    }

    public CreateApplicationResponse toCreateApplicationResponse(ApplicationEntity entity) {

        CreateApplicationResponse response = new CreateApplicationResponse();

        response.setCreated(toGregorianCalendar(entity.getCreated()));
        response.setId(entity.getId());

        return response;

    }

    public LocalDateTime toLocalDateTime(XMLGregorianCalendar dateTimeCalendar){
        return dateTimeCalendar.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }

    public GetApplicationsResponse toApplicationList(List<ApplicationEntity> applicationEntities){

        GetApplicationsResponse response = new GetApplicationsResponse();

        response.getApplicationResult().addAll(applicationEntities.stream().map(this::toApplication).collect(Collectors.toList()));

        return response;
    }

    public ApplicationResult toApplication(ApplicationEntity entity){

        SystemEntity systemEntity = entity.getSystem();

        ApplicationResult applicationResult = new ApplicationResult();

        applicationResult.setComment(entity.getComment());
        applicationResult.setNum(entity.getNumber());
        applicationResult.setWrite(entity.getWrite());
        applicationResult.setIp(systemEntity.getIp());
        applicationResult.setLogin(systemEntity.getLogin());
        applicationResult.setCreated(toGregorianCalendar(entity.getCreated()));

        return applicationResult;
    }

    @SneakyThrows
    public XMLGregorianCalendar toGregorianCalendar(LocalDateTime dateTime){
        GregorianCalendar gcal = GregorianCalendar.from(dateTime.atZone(ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    }

}
