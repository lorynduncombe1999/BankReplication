package com.bank.replication.service;

import com.bank.replication.exception.DataNotFoundException;
import com.bank.replication.model.Account;
import com.bank.replication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    //Post  with transaction
    @Transactional
    public ResponseEntity<Object> addAccountWithTransaction(Account account){
        Account newAccount = new Account();
        newAccount.setAccountName(account.getAccountName());
        newAccount.setAccountBalance(account.getAccountBalance());
        newAccount.setTransactionList(account.getTransactionList());
        Account saveAccount = accountRepository.save(newAccount);

        if (accountRepository.findById(saveAccount.getId()).isPresent()){
            return ResponseEntity.accepted().body("Succefully created new Account with a transaction");

        }else {
            return ResponseEntity.unprocessableEntity().body("failed to create role with users");
        }

    }

    //Put
    public ResponseEntity<?> updateAccount( Account account,long id ) throws Exception {
        Account newAccount = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("The account was not found by the following id: " + id));
        if (accountRepository.existsById(id)) {
            newAccount.setAccountName(account.getAccountName());
            newAccount.setAccountBalance(account.getAccountBalance());
          newAccount.setTransactionList(account.getTransactionList());
        }
        Account updatedAccount = accountRepository.save((newAccount));
        return ResponseEntity.ok(updatedAccount);
    }

    //Delete
    public ResponseEntity<?> removeAccount(long id){
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);

            if (accountRepository.findById(id).isPresent()) {
                return ResponseEntity.ok().body("Failed to delete given id");
            } else
                return ResponseEntity.ok().body("Succesfully Delete ID: " + id);

        } else return ResponseEntity.unprocessableEntity().body("No record found");

    }

    /*
    Account API should have
PATCH(where we only update name or type of account)
     */
}
