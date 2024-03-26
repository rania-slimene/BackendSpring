package com.example.auth.controller;

import DTO.*;
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
    public Object getAllClientsById(@PathVariable Long id) {
        return fineractAPIService.getClienById(id);
    }

    @PostMapping("clients")
    public Object addClients(@RequestBody ClientDtoFineract client) {
        return fineractAPIService.addClient(client);
    }

    /*@PostMapping("staff")
    public Object addStaff( @RequestBody StaffDtoFineract staff) {
        return fineractAPIService.addStaff(staff);
    } */
    @PutMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id, @RequestBody PutClientDtoFineract client) {
        return fineractAPIService.updateClient(id, client);
    }
    @PutMapping("groups/{id}")
    public Object updateClient(@PathVariable Long id, @RequestBody PutGroupDtoFineract group) {
        return fineractAPIService.updateGroup(id, group);
    }

    @DeleteMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id) {
        return fineractAPIService.DeleteClient(id);
    }
    @DeleteMapping("groups/{id}")
    public Object updateGroup(@PathVariable Long id) {
        return fineractAPIService.DeleteGroup(id);
    }

    @GetMapping("groups")
    public Object getAllGroupsFromFineract() {
        return fineractAPIService.getgroupe();
    }

    @PostMapping("groups")
    public Object addGroups(@RequestBody GroupDtoFineract group) {
        return fineractAPIService.addGroup(group);
    }

    @PostMapping("centers")
    public Object addCenter(@RequestBody CenterDtoFineract center){
        return fineractAPIService.addCenter(center);
    }
}