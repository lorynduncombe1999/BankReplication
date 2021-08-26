package com.bank.replication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BankReplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankReplicationApplication.class, args);
	}

}
