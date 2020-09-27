package ru.simple.test.soapservice.service;

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

        try {
            GregorianCalendar gcal = GregorianCalendar.from(entity.getCreated().atZone(ZoneId.systemDefault()));
            XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            response.setCreated(xcal);
            response.setId(entity.getId());

            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
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

        return applicationResult;
    }

}
