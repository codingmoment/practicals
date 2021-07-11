package com.devmango.spring.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmango.spring.transaction.dao.AccountDAO;
import com.devmango.spring.transaction.domain.Transaction;
import com.devmango.spring.transaction.exception.TransactionException;

@Service
public class CreditService {

	@Autowired
	private AccountDAO accountDAO;

	@Transactional
	public void creditAccount(Transaction transaction) {
		
		if(transaction.getAmount().longValue() != 0) throw new TransactionException();
		
		accountDAO.creditAccount(transaction);
	}
}
