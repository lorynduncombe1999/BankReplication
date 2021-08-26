package com.bank.replication.controller;

import com.bank.replication.model.Account;
import com.bank.replication.model.AuditModel;
import com.bank.replication.model.Transaction;
import com.bank.replication.repository.AccountRepository;
import com.bank.replication.repository.TransactionRepository;
import com.bank.replication.service.AccountService;
import com.bank.replication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.AudioInputStream;
import java.lang.reflect.Member;
import java.util.*;


@RestController
@RequestMapping(value = "/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

   //Get By month
    //need: account,accountid/month(created at)

   /* @GetMapping("/transaction/{dateMade}")
            public List<Transaction> findbyDate(@RequestBody Transaction transaction) {
        transactionRepository.findAll(id);
             return transactionRepository.findAll(Sort.by(String.valueOf(dateMade)));

    }

*/
    //Response Entity
    public List<Transaction> getAllTransactions(Integer pageNo,Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<Transaction> pageResult = transactionRepository.findAll(pageable);

        if(pageResult.hasContent()){
            return pageResult.getContent();
        }
        else
            return new ArrayList<Transaction>();
    }
    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> getAllTransaction(
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "date")String sortBy){
    List<Transaction> transactionList = getAllTransactions(pageNo,pageSize,sortBy);
            return new ResponseEntity<List<Transaction>>(transactionList, HttpStatus.OK);
        }


    //Pageble Object Class
    //Get all transactions
    @GetMapping("/transaction/transactionlist")
    public List<Transaction> getTransactionList(){
        return (List<Transaction>) transactionRepository.findAll();
    }

    //post
    @PostMapping("/account/{id}")
    public ResponseEntity<?> createTransaction(@PathVariable(value = "id") long id, @RequestBody Account account)
            throws Exception {
        return accountService.updateAccount(account,id);
    }
  @DeleteMapping("/transaction/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") long id){
        return transactionService.deleteTransaction(id);
  }
}
    /*@DeleteMapping("/account/{accountId}/transaction/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable(value = "accountId") long accountId,
                                           @PathVariable(value = "transactionId") long transactionId) throws Exception {
        return transactionRepository.findByIdAndAccountId(transactionId, accountId).map(transaction -> {
            transactionRepository.delete(transaction);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception("Transaction can not delete " + transactionId));

    }

*/

