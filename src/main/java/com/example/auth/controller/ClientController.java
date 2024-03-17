package com.example.auth.controller;

import com.example.auth.entities.Staff;
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
    public String getAllClientsFromFineract() {
        return fineractAPIService.getClient();
    }
    @PostMapping("staff")
    public String addStaffFromFineract(Staff staff) {
        return fineractAPIService.addStaff(staff);
    }
}