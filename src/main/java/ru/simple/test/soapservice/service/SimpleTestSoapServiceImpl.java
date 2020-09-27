package ru.simple.test.soapservice.service;

import org.springframework.stereotype.Service;
import ru.simpl_group.service.ws.*;


@Service
public class SimpleTestSoapServiceImpl implements SimpleTestSoapService {
    @Override
    public CreateApplicationResponse createApplication(CreateApplicationRequest request) {
        return null;
    }

    @Override
    public GetApplicationsResponse getApplications(GetApplicationsRequest request) {
        return null;
    }
}
