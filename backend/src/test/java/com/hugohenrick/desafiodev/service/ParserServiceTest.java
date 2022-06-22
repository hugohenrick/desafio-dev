package com.hugohenrick.desafiodev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.hugohenrick.desafiodev.entities.enumerated.TransactionTypeEnum.FINANCIAMENTO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hugohenrick.desafiodev.entities.Owner;
import com.hugohenrick.desafiodev.entities.Store;
import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.repository.StoreRepository;
import com.hugohenrick.desafiodev.repository.TransactionRepository;
import com.hugohenrick.desafiodev.services.FileService;
import com.hugohenrick.desafiodev.services.ParserService;
import com.hugohenrick.desafiodev.services.StoreService;
import com.hugohenrick.desafiodev.services.TransactionService;

public class ParserServiceTest {

	private FileService fileService;
	private TransactionService transactionService;
	private StoreService storeService;
	private ParserService parserService;

	private StoreRepository storeRepository;
	private TransactionRepository transactionRepository;

	@BeforeEach
	public void beforeEach() {
		transactionRepository = mock(TransactionRepository.class);
		transactionService = new TransactionService(transactionRepository);

		storeRepository = mock(StoreRepository.class);
		storeService = new StoreService(storeRepository);

		fileService = new FileService();

		parserService = new ParserService(fileService, transactionService, storeService);
	}

	@Test
	public void decodeData() {
		when(storeRepository.save(any())).thenReturn(getStoreFilled());
		when(transactionRepository.saveAll(any())).thenReturn(getTransactions());

		List<Transaction> transactions = parserService.parserData(getFileInputStream());

		assertFalse(transactions.isEmpty());
		assertEquals(1, transactions.size());

		Transaction transaction = transactions.get(0);

		assertEquals(FINANCIAMENTO, transaction.getTransactionType());
		assertEquals("20190301", transaction.getDate());
		assertEquals("172712", transaction.getHour());
		assertEquals("6777****1313", transaction.getCard());
		assertEquals(BigDecimal.valueOf(192.0), transaction.getValue());
		assertNotNull(transaction.getStore());
	}

	@Test
	public void returnEmptyListNotReadFile() {
		fileService = mock(FileService.class);
		parserService = new ParserService(fileService, transactionService, storeService);

		when(fileService.readLineFile(any())).thenReturn(Optional.empty());

		List<Transaction> transactions = parserService.parserData(getFileInputStream());

		verify(fileService).readLineFile(any());
		assertTrue(transactions.isEmpty());
	}

	private Store getStoreFilled() {
		return Store.builder().id(1L).name("PEREIRAMERCADO DA AVENIDA").owner(Owner.builder().build()).build();
	}

	private List<Transaction> getTransactions() {
		Transaction transaction = Transaction.builder().id((long) 1).transactionType(FINANCIAMENTO).date("20190301")
				.hour("172712").value(BigDecimal.valueOf(192.0)).card("6777****1313").store(getStoreFilled()).build();

		return List.of(transaction);
	}

	private InputStream getFileInputStream() {
		String initialString = "3201903010000019200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA";
		return new ByteArrayInputStream(initialString.getBytes());
	}
}
