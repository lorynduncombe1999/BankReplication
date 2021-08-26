package com.bank.replication.controller;

import com.bank.replication.model.Account;
import com.bank.replication.repository.AccountRepository;
import com.bank.replication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
@Autowired
private AccountService accountService;

@Autowired
private AccountRepository accountRepository;
//Get Accounts
    @GetMapping("/accountlist")
   public List<Account> getAccountList(){
        return accountRepository.findAll();
    }
//Get By ID
    @GetMapping("/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable(value = "id")long id) throws Exception{
      Account account = accountRepository.findById(id).orElseThrow(()->new Exception("Given Id Not Found"));
      return ResponseEntity.ok(account);
    }

    //post
    @PostMapping("/create")
    ResponseEntity<Object> createAccount(@RequestBody Account account)throws Exception{
                return accountService.addAccountWithTransaction(account);
    }

    //put
    @PutMapping("/update/{id}")
    ResponseEntity<?> putAccount(@RequestBody Account account, @PathVariable(value = "id")long id) throws Exception {
        return accountService.updateAccount(account,id);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable(value = "id") long id){
        return accountService.removeAccount(id);
    }
}

