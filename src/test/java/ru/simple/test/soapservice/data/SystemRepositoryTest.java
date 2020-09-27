package ru.simple.test.soapservice.data;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.simple.test.soapservice.SimpleTestWorkApplication;
import ru.simple.test.soapservice.entity.SystemEntity;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleTestWorkApplication.class)
@Slf4j
public class SystemRepositoryTest {

    @Autowired
    private SystemRepository repository;

    @Test
    public void findSystemEntityByLoginAndIp() {
        Optional<SystemEntity> systemEntityByLoginAndIp = repository.findSystemEntityByLoginAndIp("1", "1");

        Assert.assertTrue(systemEntityByLoginAndIp.isPresent());
    }
}