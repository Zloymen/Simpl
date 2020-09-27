package ru.simple.test.soapservice.data;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.simple.test.soapservice.SimpleTestWorkApplication;
import ru.simple.test.soapservice.entity.ApplicationEntity;
import ru.simple.test.soapservice.entity.SystemEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleTestWorkApplication.class)
@Slf4j
public class FullRepositoryImplTest {

    @Autowired
    private FullRepository repository;

    @Test
    @Transactional
    @Rollback(false)
    public void testCreate() {
        ApplicationEntity entity = new ApplicationEntity();

        entity.setFio("A A A");
        entity.setNumber("123");
        entity.setWrite(true);

        SystemEntity system = new SystemEntity();
        system.setIp("1");
        system.setLogin("1");

        entity.setSystem(system);

        try {
            repository.create(entity);
        }catch(JpaSystemException e){
            log.info("{}", e);
            //e.getClass()
        }
    }

    public void testGetApplicationForPeriod() {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testGetSystemWithLoginAndIp() {
        Optional<SystemEntity> systemWithLoginAndIp = repository.getSystemWithLoginAndIp("1", "1");

        Assert.assertFalse(systemWithLoginAndIp.isPresent());
    }
}