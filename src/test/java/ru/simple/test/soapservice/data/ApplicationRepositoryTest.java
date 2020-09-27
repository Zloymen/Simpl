package ru.simple.test.soapservice.data;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.simple.test.soapservice.SimpleTestWorkApplication;
import ru.simple.test.soapservice.entity.ApplicationEntity;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleTestWorkApplication.class)
@Slf4j
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void getAllByCreatedBetween(){

        LocalDate localDate = LocalDate.now();

        List<ApplicationEntity> allByCreatedBetween = applicationRepository.getAllByCreatedBetween(LocalDate.of(2020, 9, 27).atStartOfDay(), localDate.atStartOfDay());

        Assert.assertFalse(allByCreatedBetween.isEmpty());
    }
}