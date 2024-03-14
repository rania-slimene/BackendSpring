package com.example.auth.controller;

import com.example.auth.entities.Client;
import com.example.auth.serviceImpl.FineractAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/")
public class ClientController {

    @Autowired
    private FineractAPIService fineractAPIService;

    @PostMapping("/clients")
    public void createClient(@RequestBody Client client) {
        fineractAPIService.createClient(client);
    }
}