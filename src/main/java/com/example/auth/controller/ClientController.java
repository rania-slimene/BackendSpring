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

/* ************************************ offices ***********************************************/
    @GetMapping("offices")
    public Object getAllOfficesFromFineract() {
        return fineractAPIService.getOffices();
    }


    /* ************************************ staff ***********************************************/
    @PostMapping("staff")
    public Object addStaff( @RequestBody StaffDtoFineract staff) {
        return fineractAPIService.addStaff(staff);
    }
    /* ************************************ clients ***********************************************/
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

    @PutMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id , @RequestBody PutClientDtoFineract client) {
        return fineractAPIService.updateClient(id, client);
    }
    @DeleteMapping("clients/{id}")
    public Object updateClient(@PathVariable Long id) {
        return fineractAPIService.DeleteClient(id);
    }
    /* ************************************ GLAccounts ***********************************************/
    @GetMapping("GLAccounts")
    public Object getAllAccountsFromFineract() {
        return fineractAPIService.getGLAccounts();
    }

    @DeleteMapping("glaccounts/{id}")
    public Object deleteGlaccount(@PathVariable Long id) {
        return fineractAPIService.DeleteGLAccount(id);
    }

    @PostMapping("GLAccounts")
    public Object addGLAccounts( @RequestBody glaccountDtoFineract glaccount) {
        return fineractAPIService.addGLAccounts(glaccount);
    }
    @PutMapping("GLAccount/{id}")
    public Object updateGLAccounts(@PathVariable Long id , @RequestBody glaccountDtoFineract glaccount) {
        return fineractAPIService.updateGLAcount(id, glaccount);
    }


    /* ************************************ center ***********************************************/

    @PostMapping("center")
    public Object addCenter( @RequestBody CenterDtoFineract center) {
        return fineractAPIService.addCenter( center );
    }
    @GetMapping("centers")
    public Object getCenters(   ) {
        return fineractAPIService.getCenters( );
    }
    @PutMapping("center/{id}")
    public Object updateCenter(@PathVariable Long id, @RequestBody PutCenterDtoFineract center) {
        return fineractAPIService.updateCenter( center , id);
    }


    /* ************************************ savingsaccounts ***********************************************/
    @PostMapping("savingsaccounts/{id}")
    public Object approveAccount( @PathVariable Long id ,@RequestBody ApproveAccountDtoFineract approveAccount) {
        return fineractAPIService.approveAccount(approveAccount , id );
    }
    @PostMapping("undoapprovalsavingsaccounts/{id}")
    public Object undoapprovalAccount( @PathVariable Long id ) {
        return fineractAPIService.undoapprovalAccount( id );
    }

    @PostMapping("activatesavingsaccounts/{id}")
    public Object undoapprovalAccount( @PathVariable Long id, @RequestBody ActivateAccountDtoFineract activateAcount ) {
        return fineractAPIService.activeAcount( activateAcount,id );
    }
    @PostMapping("savingsaccount")
    public Object addsavingAccount(  @RequestBody SavingsaccountsDtoFineract savingAccount ) {
        return fineractAPIService.addsavingAccount( savingAccount);
    }


    /* ************************************ savingProduct ***********************************************/
    @GetMapping("savingProduct")
    public Object getSavingProducts( ) {
        return fineractAPIService.getSavingProducts();
    }
    @PostMapping("savingProduct")
    public Object addsavingProduct( @RequestBody SavingProductDtoFineract savingProduct ) {
        return fineractAPIService.addsavingProduct( savingProduct);
    }

}

