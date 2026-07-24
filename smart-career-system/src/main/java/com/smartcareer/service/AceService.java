package com.smartcareer.service;

import com.smartcareer.entity.ContactMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AceService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendEmail(ContactMessage message) {

        String url = "http://localhost:7800/contact/email";

        restTemplate.postForEntity(url, message, String.class);
    }
}
