package com.bank.replication.repository;

import com.bank.replication.model.AuditModel;
import com.bank.replication.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long> {
    Optional<Transaction> findByIdAndAccountId(Long id, Long postId);
    //Page<Transaction> sortByDate(Transaction transaction, Pageable pageable);



}
