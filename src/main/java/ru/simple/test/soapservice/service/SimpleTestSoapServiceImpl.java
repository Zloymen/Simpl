package ru.simple.test.soapservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.simpl_group.service.ws.*;


@Service
@RequiredArgsConstructor
public class SimpleTestSoapServiceImpl implements SimpleTestSoapService {

    private final ApplicationService service;

    @Override
    public CreateApplicationResponse createApplication(CreateApplicationRequest request) {
        return service.create(request);
    }

    @Override
    public GetApplicationsResponse getApplications(GetApplicationsRequest request) {
        return service.getApplications(request);
    }
}
