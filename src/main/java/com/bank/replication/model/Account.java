package com.bank.replication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account extends AuditModel {

    //Requirements for account
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false)
    private long accountBalance;

    @OneToMany(targetEntity = Transaction.class,cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

}
