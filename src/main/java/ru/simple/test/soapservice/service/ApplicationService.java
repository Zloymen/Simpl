package ru.simple.test.soapservice.service;

import ru.simpl_group.service.ws.CreateApplicationRequest;
import ru.simpl_group.service.ws.CreateApplicationResponse;
import ru.simpl_group.service.ws.GetApplicationsRequest;
import ru.simpl_group.service.ws.GetApplicationsResponse;

public interface ApplicationService {

    CreateApplicationResponse create(CreateApplicationRequest request);

    GetApplicationsResponse getResponse(GetApplicationsRequest request);
}
