package com.devmango.spring.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.spring.transaction.domain.Transaction;
import com.devmango.spring.transaction.service.AccountService;
import com.devmango.spring.transaction.service.CreditService;
import com.devmango.spring.transaction.service.DebitService;

@RestController("/transactions")
public class TransactionController {

	@Autowired
	private DebitService debitService;

	@Autowired
	private CreditService creditService;

	@Autowired
	private AccountService accountService;

	@PostMapping
	public void transfer(@RequestBody Transaction transaction) {
		System.out.println("transfer started....");
		debitService.debitAmount(transaction);
		creditService.creditAccount(transaction);

		accountService.getAllAccounts()
				.forEach(account -> System.out.println(account.getAccountId() + " - " + account.getBalance()));
		System.out.println("transfer completed....");
	}
}
