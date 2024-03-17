package com.example.auth.serviceImpl;

import com.example.auth.entities.Staff;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

@Service
public class FineractAPIService {
    @Value("${fineract.api.url}")
    private String fineractApiUrl;

    @Value("${fineract.api.username}")
    private String fineractUsername;

    @Value("${fineract.api.password}")
    private String fineractPassword;

    public String getOffices() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = fineractUsername + ":" + fineractPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Fineract-Platform-TenantId", "default");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(fineractApiUrl + "/offices", HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
    public String getClient() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = fineractUsername + ":" + fineractPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Fineract-Platform-TenantId", "default");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(fineractApiUrl + "/clients", HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
    public String addStaff(Staff staff) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = fineractUsername + ":" + fineractPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Fineract-Platform-TenantId", "default");
        HttpEntity<Staff> entity = new HttpEntity<>(staff, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(fineractApiUrl + "/staff", entity, String.class);

        return response.getBody();
    }

}
