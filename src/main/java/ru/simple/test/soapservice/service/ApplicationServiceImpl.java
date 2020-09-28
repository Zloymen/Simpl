package ru.simple.test.soapservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.simpl_group.service.ws.*;
import ru.simple.test.soapservice.data.ApplicationRepository;
import ru.simple.test.soapservice.data.SystemRepository;
import ru.simple.test.soapservice.entity.ApplicationEntity;
import ru.simple.test.soapservice.entity.SystemEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationServiceImpl implements ApplicationService{

    private final ApplicationRepository applicationRepository;

    private final ConverterService converterService;

    private final SystemRepository systemRepository;

    @SneakyThrows
    @Override
    public CreateApplicationResponse create(CreateApplicationRequest request) {

        ApplicationEntity entity = converterService.toEntity(request.getApplication());

        SystemEntity system = entity.getSystem();

        Optional<SystemEntity> optionalSystemEntity = systemRepository.findSystemEntityByLoginAndIp(system.getLogin(), system.getIp());

        if(optionalSystemEntity.isPresent()) {
            FaultDetail detail = new FaultDetail();
            detail.setCode("APPLICATION_IS_EXIST");
            detail.setDetail("Заявка от таких логин и ip присутвует");

            throw new CreateApplicationFault("Заявка от таких логин и ip присутвует", detail);
        }

        return converterService.toCreateApplicationResponse(applicationRepository.save(entity));
    }

    @Override
    public GetApplicationsResponse getApplications(GetApplicationsRequest request) {
        LocalDateTime from = converterService.toLocalDateTime(request.getCreateFrom());
        LocalDateTime to = converterService.toLocalDateTime(request.getCreateTo());

        return converterService.toApplicationList(applicationRepository.getAllByCreatedBetween(from, to));
    }
}
