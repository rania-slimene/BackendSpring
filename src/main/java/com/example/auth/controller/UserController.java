package com.example.auth.controller;

import DTO.DepositMoneyDtoFineract;
import DTO.StaffDtoFineract;
import com.example.auth.entities.User;
import com.example.auth.serviceImpl.FineractAPIService;
import com.example.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savingsaccounts/")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private FineractAPIService fineractAPIService;
    //    @Autowired
//    UserService userService;


//    @PostMapping("Create")
//    public User SignUp (@RequestBody User user){
//        return userService.SignUp(user);
//
//    }

//    @PostMapping("login")
//    public User SignIn (@RequestBody User user){
//        return  userService.SignIn(user);
//    }


    @PostMapping("{id}")
    public Object depositMoney(@PathVariable Long id , @RequestBody DepositMoneyDtoFineract depositMoney) {
        return fineractAPIService.depositMoneytoSavingAccount( depositMoney,id);
    }
}
