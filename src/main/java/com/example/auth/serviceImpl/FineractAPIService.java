package com.example.auth.serviceImpl;

import DTO.ClientDtoFineract;
import DTO.PutClientDtoFineract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;


@Service
public class FineractAPIService  extends AbstractApiService{
    @Value("${fineract.api.url}")
    private String fineractApiUrl;

    public String getOffices() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(fineractApiUrl + "/offices", HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    public Object getClient() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

    public Object getClienById(Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

//    public Object addStaff(StaffDtoFineract staff) {
//        StaffDtoFineract staff1 = new StaffDtoFineract();
//        staff1.setOfficeId(staff.getOfficeId());
//        staff1.setFirstname(staff.getFirstname());
//        staff1.setLastname(staff.getLastname());
//        staff1.setIsLoanOfficer(staff.getIsLoanOfficer());
//        staff1.setLocale(staff.getLocale());
//        staff1.setJoiningDate(staff.getJoiningDate());
//        staff1.setDateFormat(staff.getDateFormat());
//
//        if (staff.getOfficeId() == null || staff.getFirstname() == null || staff.getLastname() == null || staff.getIsLoanOfficer() == null || staff.getJoiningDate() == null || staff.getLocale() == null || staff.getDateFormat() == null) {
//            return "Erreur lors de l'ajout du client: Certaines données obligatoires sont manquantes";
//        }
//
//        HttpHeaders headers = this.createHeaders();
//        HttpEntity<StaffDtoFineract> entity = new HttpEntity<>(staff1, headers);
//
//
//        try {
//            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/staff", entity, Object.class);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                return "Client ajouté avec succès" + response.getBody();
//            } else {
//                return "Erreur lors de l'ajout du client: " + response.getBody();
//            }
//        } catch (RestClientException e) {
//            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
//        }
//
//    }

    public Object addClient(ClientDtoFineract client) {
        ClientDtoFineract client1 = new ClientDtoFineract();
        client1.setOfficeId(client.getOfficeId());
        client1.setFirstname(client.getFirstname());
        client1.setLastname(client.getLastname());
        client1.setLegalFormId(client.getLegalFormId());
        client1.setLocale(client.getLocale());
        if (client.getOfficeId() == null || client.getFirstname() == null || client.getLastname() == null || client.getLegalFormId() == null) {
            return "Erreur lors de l'ajout du client: Certaines données obligatoires sont manquantes";
        }
        HttpHeaders headers = this.createHeaders();
        HttpEntity<ClientDtoFineract> entity = new HttpEntity<>(client1, headers);


        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/clients", entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Client ajouté avec succès" + response.getBody();
            } else {
                return "Erreur lors de l'ajout du client: " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }

    }

    public Object updateClient(Long id, PutClientDtoFineract client) {
        PutClientDtoFineract client1 = new PutClientDtoFineract();

        client1.setFirstname(client.getFirstname());
        client1.setLastname(client.getLastname());
        client1.setLegalFormId(client.getLegalFormId());
        client1.setLocale(client.getLocale());
        if (client.getFirstname() == null || client.getLastname() == null || client.getLegalFormId() == null) {
            return "Erreur lors de l'ajout du client: Certaines données obligatoires sont manquantes";
        }
        HttpHeaders headers = this.createHeaders();
        HttpEntity<PutClientDtoFineract> entity = new HttpEntity<>(client1, headers);
        try {
            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.PUT, entity, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "Client Updated avec succès" + response.getBody();
            } else {
                return "Erreur lors de modification du client: " + response.getBody();
            }
        } catch (HttpClientErrorException.NotFound e) {
            return "Client non trouvé avec l'ID: " + id;
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }


    }


    public Object DeleteClient(Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<PutClientDtoFineract> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.DELETE, entity, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "Client supprime avec succès" + response.getBody();
            } else {
                return "Erreur lors de suppression du client: " + response.getBody();
            }
        } catch (HttpClientErrorException.NotFound e) {
            return "Client non trouvé avec l'ID: " + id;
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }


    }
}


