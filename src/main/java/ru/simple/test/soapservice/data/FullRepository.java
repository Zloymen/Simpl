package ru.simple.test.soapservice.data;

import ru.simple.test.soapservice.entity.ApplicationEntity;
import ru.simple.test.soapservice.entity.SystemEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FullRepository {

    void create(ApplicationEntity entity);

    List<ApplicationEntity> getApplicationForPeriod(LocalDateTime from, LocalDateTime to);

    Optional<SystemEntity> getSystemWithLoginAndIp(String login, String ip);
}
