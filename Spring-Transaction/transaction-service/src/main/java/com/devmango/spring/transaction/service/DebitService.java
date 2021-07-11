package com.devmango.spring.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmango.spring.transaction.dao.AccountDAO;
import com.devmango.spring.transaction.domain.Transaction;

@Service
public class DebitService {

	@Autowired
	private AccountDAO accountDAO;

	@Transactional
	public void debitAmount(Transaction transaction) {
		accountDAO.debitAccount(transaction);
	}
}
