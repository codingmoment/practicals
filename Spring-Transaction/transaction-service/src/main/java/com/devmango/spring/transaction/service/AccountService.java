package com.devmango.spring.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmango.spring.transaction.dao.AccountDAO;
import com.devmango.spring.transaction.domain.Account;

@Service
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;

	public List<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}
	
}
