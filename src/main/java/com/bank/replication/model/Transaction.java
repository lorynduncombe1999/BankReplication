package com.bank.replication.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transaction extends AuditModel {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

   @Column
    private long transactionAmount;



    @JsonIgnore
    @ManyToOne (targetEntity=Account.class)
    private Account account;





}
