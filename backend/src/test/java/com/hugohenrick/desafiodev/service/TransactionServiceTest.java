package com.hugohenrick.desafiodev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.repository.TransactionRepository;
import com.hugohenrick.desafiodev.services.TransactionService;

public class TransactionServiceTest {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionService transactionService;

	@BeforeEach
	public void beforeEach() {
		transactionRepository = mock(TransactionRepository.class);
		transactionService = new TransactionService(transactionRepository);
	}

	@Test
	public void saveTransactionsTest() {
		when(transactionRepository.saveAll(any())).thenReturn(getTransactions());

		List<Transaction> transactions = transactionService.saveTransactions(getTransactions());

		assertFalse(transactions.isEmpty());
		assertEquals(1, transactions.size());
	}

	private List<Transaction> getTransactions() {
		return List.of(Transaction.builder().build());
	}
}
