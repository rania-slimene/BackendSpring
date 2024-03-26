package com.example.auth.serviceImpl;

import DTO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class FineractAPIService  extends AbstractApiService{
    @Value("${fineract.api.url}")
    private String fineractApiUrl;
/* ***************************************** Offices***********************************************************************/
    public Object getOffices() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/offices", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }
    /* ***************************************** Client***********************************************************************/
    public Object getClient() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }

    public Object getClienById (Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.GET, entity, Object.class);
        return response.getBody();
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

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.PUT, entity, Object.class);
        return  response.getBody();

    }


    public Object DeleteClient(Long id) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<PutClientDtoFineract> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + id, HttpMethod.DELETE, entity, Object.class);
        return response.getBody();

    }



    /* ***************************************** Staff***********************************************************************/

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
        ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/staff", entity, Object.class);
        return  response.getBody();


    }

    /* ***************************************** roupe***********************************************************************/
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
    /* ***************************************** GLAccounts***********************************************************************/
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
    public Object getGLAccounts() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/glaccounts", HttpMethod.GET, entity, Object.class);
        return response.getBody();
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



        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/glaccounts/" + id,HttpMethod.PUT, entity, Object.class);

        return "account modifié avec succès" + response.getBody();


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


    /* ****************************************Center* ***********************************************************************/
    public Object getCenters() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/centers", HttpMethod.GET, entity, Object.class);
        return response.getBody();
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


            ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/centers", entity, Object.class);

                return "center ajouté avec succès" + response.getBody();

    }



    public Object updateCenter(PutCenterDtoFineract center, Long id ) {
        CenterDtoFineract center1 = new CenterDtoFineract();
        center1.setName(center.getName());
        center1.setStaffId(center.getStaffId());
        center1.setActive(center.getActive());
        center1.setDateFormat(center.getDateFormat());
        center1.setActivationDate(center.getActivationDate());
        center1.setLocale(center.getLocale());


        HttpHeaders headers = this.createHeaders();
        HttpEntity<CenterDtoFineract> entity = new HttpEntity<>(center1, headers);


            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/centers/" + id, HttpMethod.PUT,entity, Object.class);
           return response.getBody();

    }




    /* ***************************************** Product***********************************************************************/
    public Object addsavingProduct(SavingProductDtoFineract Savingproduct) {
        SavingProductDtoFineract Savingproduct1 = new SavingProductDtoFineract();
        Savingproduct1.setInMultiplesOf(Savingproduct.getInMultiplesOf());
        Savingproduct1.setDigitsAfterDecimal(Savingproduct.getDigitsAfterDecimal());
        Savingproduct1.setName(Savingproduct.getName());
        Savingproduct1.setLocale(Savingproduct.getLocale());
        Savingproduct1.setShortName(Savingproduct.getShortName());
        Savingproduct1.setAccountingRule(Savingproduct.getAccountingRule());
        Savingproduct1.setInterestCompoundingPeriodType(Savingproduct.getInterestCompoundingPeriodType());
        Savingproduct1.setDescription(Savingproduct.getDescription());
        Savingproduct1.setInterestCalculationType(Savingproduct.getInterestCalculationType());
        Savingproduct1.setInterestPostingPeriodType(Savingproduct.getInterestPostingPeriodType());
        Savingproduct1.setInterestCalculationDaysInYearType(Savingproduct.getInterestCalculationDaysInYearType());
        Savingproduct1.setNominalAnnualInterestRate(Savingproduct.getNominalAnnualInterestRate());
        Savingproduct1.setCurrencyCode(Savingproduct.getCurrencyCode());
        Savingproduct1.setInMultiplesOf(Savingproduct.getInMultiplesOf());


        HttpHeaders headers = this.createHeaders();
        HttpEntity<SavingProductDtoFineract> entity = new HttpEntity<>(Savingproduct1, headers);
        ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/savingsproducts  ", entity, Object.class);
        return response.getBody();
    }

    public Object getSavingProducts() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts", HttpMethod.GET, entity, Object.class);
        return response.getBody();
    }


    /* ***************************************** savingAccount ***********************************************************************/
    public Object addsavingAccount(SavingsaccountsDtoFineract SavingAcount) {
        SavingsaccountsDtoFineract SavingAcount1 = new SavingsaccountsDtoFineract();
        SavingAcount1.setSubmittedOnDate(SavingAcount.getSubmittedOnDate());
        SavingAcount1.setExternalId(SavingAcount.getExternalId());
        SavingAcount1.setProductId(SavingAcount.getProductId());
        SavingAcount1.setLocale(SavingAcount.getLocale());
        SavingAcount1.setClientId(SavingAcount.getClientId());
        SavingAcount1.setFieldOfficerId(SavingAcount.getFieldOfficerId());
        SavingAcount1.setInterestCompoundingPeriodType(SavingAcount.getInterestCompoundingPeriodType());
        SavingAcount1.setDateFormat(SavingAcount.getDateFormat());
        SavingAcount1.setInterestCalculationType(SavingAcount.getInterestCalculationType());
        SavingAcount1.setInterestPostingPeriodType(SavingAcount.getInterestPostingPeriodType());
        SavingAcount1.setInterestCalculationDaysInYearType(SavingAcount.getInterestCalculationDaysInYearType());
        SavingAcount1.setNominalAnnualInterestRate(SavingAcount.getNominalAnnualInterestRate());


        HttpHeaders headers = this.createHeaders();
        HttpEntity<SavingsaccountsDtoFineract> entity = new HttpEntity<>(SavingAcount1, headers);

        ResponseEntity<Object> response = restTemplate.postForEntity(fineractApiUrl + "/savingsaccounts", entity, Object.class);

        return response.getBody();
    }

    public Object approveAccount(ApproveAccountDtoFineract ApproveAccount, Long id){
        ApproveAccountDtoFineract approveAccount = new ApproveAccountDtoFineract();
        approveAccount.setApprovedOnDate(ApproveAccount.getApprovedOnDate());
        approveAccount.setDateFormat(ApproveAccount.getDateFormat());
        approveAccount.setLocale(ApproveAccount.getLocale());

        // Définir la valeur du paramètre "command"
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "approve");

        HttpHeaders headers = this.createHeaders();
        // Ajouter les paramètres de requête à l'URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/" + id)
                .queryParams(queryParams);

        HttpEntity<ApproveAccountDtoFineract> entity = new HttpEntity<>(approveAccount, headers);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);
        return response.getBody();

    }

    public Object undoapprovalAccount( Long id){

        // Définir la valeur du paramètre "command"
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "undoapproval");

        HttpHeaders headers = this.createHeaders();
        // Ajouter les paramètres de requête à l'URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/" + id)
                .queryParams(queryParams);

        HttpEntity<ApproveAccountDtoFineract> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);

        return   response.getBody();

    }

    public Object activeAcount(ActivateAccountDtoFineract ActivateAccount, Long id){
        ActivateAccountDtoFineract ActivateAccount1 = new ActivateAccountDtoFineract();
        ActivateAccount1.setActivatedOnDate(ActivateAccount.getActivatedOnDate());
        ActivateAccount1.setLocale(ActivateAccount.getLocale());
        ActivateAccount1.setDateFormat(ActivateAccount.getDateFormat());

        // Définir la valeur du paramètre "command"
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "activate");

        HttpHeaders headers = this.createHeaders();
        // Ajouter les paramètres de requête à l'URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/" + id)
                .queryParams(queryParams);

        HttpEntity<ActivateAccountDtoFineract> entity = new HttpEntity<>(ActivateAccount1, headers);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);
        return  response.getBody();

    }
    /* *****************************************depositMoneytoSavingAccount ***********************************************************************/
    public Object depositMoneytoSavingAccount(DepositMoneyDtoFineract depositMoney, Long id){
        DepositMoneyDtoFineract depositMoney1 = new DepositMoneyDtoFineract();
        depositMoney1.setTransactionDate(depositMoney.getTransactionDate());
        depositMoney1.setPaymentTypeId(depositMoney.getPaymentTypeId());
        depositMoney1.setTransactionAmount(depositMoney.getTransactionAmount());
        depositMoney1.setLocale(depositMoney.getLocale());
        depositMoney1.setDateFormat(depositMoney.getDateFormat());

        // Définir la valeur du paramètre "command"
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "deposit");

        HttpHeaders headers = this.createHeaders();
        // Ajouter les paramètres de requête à l'URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/" + id+"/transactions")
                .queryParams(queryParams);

        HttpEntity<DepositMoneyDtoFineract> entity = new HttpEntity<>(depositMoney1, headers);

            ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);

                return  response.getBody();
            }



}


