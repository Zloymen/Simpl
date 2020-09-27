package ru.simple.test.soapservice.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.simple.test.soapservice.entity.ApplicationEntity;
import ru.simple.test.soapservice.entity.SystemEntity;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FullRepositoryImpl implements FullRepository {

    private final EntityManager entityManager;

    @Override
    public void create(ApplicationEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<ApplicationEntity> getApplicationForPeriod(LocalDateTime from, LocalDateTime to) {
        return entityManager
                .createQuery("select a from ApplicationEntity a where a.created between :from and :to", ApplicationEntity.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public Optional<SystemEntity> getSystemWithLoginAndIp(String login, String ip) {
        return entityManager.createQuery("select s from SystemEntity s where s.login=:login and s.ip=:ip", SystemEntity.class)
                .setParameter("login", login)
                .setParameter("ip", ip)
                .getResultList().stream().findFirst();
    }


}
