package com.hugohenrick.desafiodev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	@Transactional
	public List<Transaction> saveTransactions(List<Transaction> transactionList) {
		return repository.saveAll(transactionList);

	}
}
