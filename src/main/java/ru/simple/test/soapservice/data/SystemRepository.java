package ru.simple.test.soapservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simple.test.soapservice.entity.SystemEntity;

import java.util.Optional;

public interface SystemRepository extends JpaRepository<SystemEntity, Long>  {
    Optional<SystemEntity> findSystemEntityByLoginAndIp(String login, String ip);
}
