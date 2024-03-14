package com.example.auth.serviceImpl;

import com.example.auth.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FineractAPIService {

    @Autowired
    private RestTemplate restTemplate ;

    public void createClient(Client client) {
        String apiUrl = "https://localhost:8443/fineract-provider/api/v1/v1/clients";
        // Créez l'en-tête de la requête avec le type de contenu JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Créez une entité HTTP avec les données du client à envoyer
        HttpEntity<Client> requestEntity = new HttpEntity<>(client, headers);

        // Effectuez la requête POST pour créer le client
        restTemplate.postForObject(apiUrl, requestEntity, Void.class);
    }
}
