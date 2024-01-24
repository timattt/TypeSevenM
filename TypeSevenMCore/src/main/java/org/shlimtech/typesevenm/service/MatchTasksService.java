package org.shlimtech.typesevenm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MatchTasksService {

    private final RestTemplate restTemplate;

    @Value("${type-7.type-7-d-host}")
    private String type7dHost;

    @Value("${type-7.call-type-7-d}")
    private boolean mayCallType7d;

    public void createMatchTask(int userId) {
        if (mayCallType7d) {
            restTemplate.postForLocation(type7dHost + "/task/" + userId, null);
        }
    }

}
