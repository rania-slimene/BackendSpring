package com.example.auth.serviceImpl;

import DTO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;


@Service
public class FineractAPIService  extends AbstractApiService{
    @Value("${fineract.api.url}")
    private String fineractApiUrl;

    public Object getOffices() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/offices", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

    public Object getClient() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }
    public Object getGLAccounts() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/glaccounts", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

    public Object getClienById (Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

    public Object addStaff(StaffDtoFineract staff) {
        StaffDtoFineract staff1 = new StaffDtoFineract();
        staff1.setOfficeId(staff.getOfficeId());
        staff1.setFirstname(staff.getFirstname());
        staff1.setLastname(staff.getLastname());
        staff1.setIsLoanOfficer(staff.getIsLoanOfficer());
        staff1.setLocale(staff.getLocale());
        staff1.setJoiningDate(staff.getJoiningDate());
        staff1.setDateFormat(staff.getDateFormat());

        if (staff.getOfficeId() == null || staff.getFirstname() == null || staff.getLastname() == null || staff.getIsLoanOfficer() == null || staff.getJoiningDate() == null || staff.getLocale() == null || staff.getDateFormat() == null) {
            return "Erreur lors de l'ajout du client: Certaines données obligatoires sont manquantes";
        }

        HttpHeaders headers = this.createHeaders();
        HttpEntity<StaffDtoFineract> entity = new HttpEntity<>(staff1, headers);


        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/staff", entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Client ajouté avec succès" + response.getBody();
            } else {
                return "Erreur lors de l'ajout du client: " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }

    }

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
    public Object DeleteGroup(Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<PutGroupDtoFineract> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/groups/" + id, HttpMethod.DELETE, entity, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "Group supprime avec succès" + response.getBody();
            } else {
                return "Group lors de suppression du client: " + response.getBody();
            }
        } catch (HttpClientErrorException.NotFound e) {
            return "Group non trouvé avec l'ID: " + id;
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }


    }
    public Object DeleteGLAccount(Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/glaccounts/" + id, HttpMethod.DELETE, entity, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "glaccount supprime avec succès" + response.getBody();
            } else {
                return "Erreur lors de suppression du glaccoun: " + response.getBody();
            }
        } catch (HttpClientErrorException.NotFound e) {
            return "glaccoun non trouvé avec l'ID: " + id;
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }


    }

    public Object addCenter(CenterDtoFineract center) {
        CenterDtoFineract center1 = new CenterDtoFineract();
        center1.setOfficeId(center.getOfficeId());
        center1.setName(center.getName());
        center1.setActive(center.getActive());
        center1.setDateFormat(center.getDateFormat());
        center1.setActivationDate(center.getActivationDate());
        center1.setLocale(center.getLocale());
        center1.setGroupMembers(center.getGroupMembers());
        if (center.getOfficeId() == null || center.getName() == null ) {
            return "Erreur lors de l'ajout du client: Certaines données obligatoires sont manquantes";
        }
        HttpHeaders headers = this.createHeaders();
        HttpEntity<CenterDtoFineract> entity = new HttpEntity<>(center1, headers);

        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/centers", entity, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "center ajouté avec succès" + response.getBody();
            } else {
                return "Erreur lors de l'ajout du center: " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }
    }
    public Object getgroupe() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/groups", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

    public Object addGroup(GroupDtoFineract group) {
        GroupDtoFineract group1 = new GroupDtoFineract();
        group1.setName(group.getName());
        group1.setOfficeId(group.getOfficeId());

        if (group.getOfficeId() == null || group.getName() == null ) {
            return "Erreur lors de l'ajout du group: Certaines données obligatoires sont manquantes";
        }
        HttpHeaders headers = this.createHeaders();
        HttpEntity<GroupDtoFineract> entity = new HttpEntity<>(group1, headers);


        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/groups", entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Groupe ajouté avec succès" + response.getBody();
            } else {
                return "Erreur lors de l'ajout du groupe: " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }

    }
    public Object addGLAccounts(glaccountDtoFineract glaccount) {
        glaccountDtoFineract glaccount1 = new glaccountDtoFineract();
        glaccount1.setName(glaccount.getName());
        glaccount1.setType(glaccount.getType());
        glaccount1.setDescription(glaccount.getDescription());
        glaccount1.setGlCode(glaccount.getGlCode());
        glaccount1.setUsage(glaccount.getUsage());
        glaccount1.setManualEntriesAllowed(glaccount.getManualEntriesAllowed());


        HttpHeaders headers = this.createHeaders();
        HttpEntity<glaccountDtoFineract> entity = new HttpEntity<>(glaccount1, headers);


        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/glaccounts", entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "account ajouté avec succès" + response.getBody();
            } else {
                return "Erreur lors de l'ajout du accounts: " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }

    }


    public Object updateGroup(Long id, PutGroupDtoFineract group) {
        PutGroupDtoFineract group1 = new PutGroupDtoFineract();
        group1.setName(group.getName());

        HttpHeaders headers = this.createHeaders();
        HttpEntity<PutGroupDtoFineract> entity = new HttpEntity<>(group1, headers);


        try {
            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/groups/" + id,HttpMethod.PUT, entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Groupe modifié avec succès" + response.getBody();
            } else {
                return "Erreur lors de la modification du groupe: " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }

    }
    public Object updateGLAcount(Long id, glaccountDtoFineract glaccount) {
        glaccountDtoFineract glaccount1 = new glaccountDtoFineract();
        glaccount1.setName(glaccount.getName());
        glaccount1.setType(glaccount.getType());
        glaccount1.setDescription(glaccount.getDescription());
        glaccount1.setGlCode(glaccount.getGlCode());
        glaccount1.setUsage(glaccount.getUsage());
        glaccount1.setManualEntriesAllowed(glaccount.getManualEntriesAllowed());


        HttpHeaders headers = this.createHeaders();
        HttpEntity<glaccountDtoFineract> entity = new HttpEntity<>(glaccount1, headers);


        try {
            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/glaccounts/" + id,HttpMethod.PUT, entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "account modifié avec succès" + response.getBody();
            } else {
                return "Erreur lors de la modification du : " + response.getBody();
            }
        } catch (RestClientException e) {
            return "Erreur lors de la communication avec l'API Fineract: " + e.getMessage();
        }

    }



};


