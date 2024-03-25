package com.example.auth.controller;

import DTO.ClientDtoFineract;
import DTO.PutClientDtoFineract;
import DTO.StaffDtoFineract;
import com.example.auth.serviceImpl.FineractAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/")
public class ClientController {

    @Autowired
    private FineractAPIService fineractAPIService;


    @GetMapping("offices")
    public String getAllOfficesFromFineract() {
        return fineractAPIService.getOffices();
    }
    @GetMapping("clients")
    public Object getAllClientsFromFineract() {
        return fineractAPIService.getClient();
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
    @PutMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id , @RequestBody PutClientDtoFineract client) {
        return fineractAPIService.updateClient(id, client);
    }
    @DeleteMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id) {
        return fineractAPIService.DeleteClient(id);
    }
}