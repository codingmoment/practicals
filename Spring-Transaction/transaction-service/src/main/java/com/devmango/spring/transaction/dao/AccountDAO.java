package com.devmango.spring.transaction.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.devmango.spring.transaction.domain.Account;
import com.devmango.spring.transaction.domain.Transaction;

@Repository
public class AccountDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void creditAccount(Transaction transaction) {
		jdbcTemplate.update("UPDATE account SET balance = balance + ? WHERE account_id = ?",
				new Object[] { transaction.getAmount(), transaction.getCreditAccount() });
	}

	public void debitAccount(Transaction transaction) {
		jdbcTemplate.update("UPDATE account SET balance = balance - ? WHERE account_id = ?",
				new Object[] { transaction.getAmount(), transaction.getDebitAccount() });
	}
	
	public List<Account> getAllAccounts() {
		return jdbcTemplate.query("SELECT account_id, balance FROM account", new BeanPropertyRowMapper<Account>(Account.class));
	}
}
