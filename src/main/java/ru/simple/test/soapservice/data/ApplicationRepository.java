package ru.simple.test.soapservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simple.test.soapservice.entity.ApplicationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> getAllByCreatedBetween(LocalDateTime from, LocalDateTime to);
}
