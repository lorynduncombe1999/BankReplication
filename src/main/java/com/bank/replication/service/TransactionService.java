package com.bank.replication.service;

import com.bank.replication.exception.DataNotFoundException;
import com.bank.replication.model.Account;
import com.bank.replication.model.AuditModel;
import com.bank.replication.model.Transaction;
import com.bank.replication.repository.AccountRepository;
import com.bank.replication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    //GetByDate


//Delete
public ResponseEntity<?> deleteTransaction(long id) {

    //if the given id excess delete id
    if (transactionRepository.findById(id).isPresent()) {
        transactionRepository.deleteById(id);

        if (transactionRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Failed to delete given id");
        } else
            return ResponseEntity.ok().body("Succesfully Delete ID: " + id);

    } else return ResponseEntity.unprocessableEntity().body("No record found");
}


}

/*
Transaction API should have
GET/account/{acc_ID}/transaction By date (month, week or day)
Delete
All the HTTP method should have swagger with all the possible exceptions
Both should have it’s own custom exceptions
Spring Security where only two Role (OWNER(all), VIWER(only do GET))
 */