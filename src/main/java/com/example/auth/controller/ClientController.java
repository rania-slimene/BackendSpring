package com.example.auth.controller;

import DTO.ClientDtoFineract;
import DTO.PutClientDtoFineract;
import DTO.StaffDtoFineract;
import DTO.glaccountDtoFineract;
import com.example.auth.serviceImpl.FineractAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/")
public class ClientController {

    @Autowired
    private FineractAPIService fineractAPIService;


    @GetMapping("offices")
    public Object getAllOfficesFromFineract() {
        return fineractAPIService.getOffices();
    }
    @GetMapping("clients")
    public Object getAllClientsFromFineract() {
        return fineractAPIService.getClient();
    }
    @GetMapping("GLAccounts")
    public Object getAllAccountsFromFineract() {
        return fineractAPIService.getGLAccounts();
    }

    @GetMapping("clients/{id}")
    public Object getAllClientsById(@PathVariable Long id ) {
        return fineractAPIService.getClienById(id);
    }

    @PostMapping("clients")
    public Object addClients(@RequestBody ClientDtoFineract client) {
        return fineractAPIService.addClient(client);
    }

    @PostMapping("staff")
    public Object addStaff( @RequestBody StaffDtoFineract staff) {
        return fineractAPIService.addStaff(staff);
    }
    @PostMapping("GLAccounts")
    public Object addGLAccounts( @RequestBody glaccountDtoFineract glaccount) {
        return fineractAPIService.addGLAccounts(glaccount);
    }
    @PutMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id , @RequestBody PutClientDtoFineract client) {
        return fineractAPIService.updateClient(id, client);
    }
    @PutMapping("GLAccount/{id}")
    public Object updateGLAccounts(@PathVariable Long id , @RequestBody glaccountDtoFineract glaccount) {
        return fineractAPIService.updateGLAcount(id, glaccount);
    }
    @DeleteMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id) {
        return fineractAPIService.DeleteClient(id);
    }

    @DeleteMapping("glaccounts/{id}")
    public Object deleteGlaccount(@PathVariable Long id) {
        return fineractAPIService.DeleteGLAccount(id);
    }
}