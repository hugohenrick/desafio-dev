package com.hugohenrick.desafiodev.services;

import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.CARD;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.CPF;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.DATE;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.HOUR;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.STORE_NAME;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.STORE_OWNER;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.TRANSACTION_TYPE;
import static com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum.VALUE;
import static java.lang.Double.parseDouble;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hugohenrick.desafiodev.entities.Owner;
import com.hugohenrick.desafiodev.entities.Store;
import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.entities.enumerated.DataTypeEnum;
import com.hugohenrick.desafiodev.entities.enumerated.TransactionTypeEnum;

@Service
public class ParserService {

	@Autowired
	private FileService fileService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private StoreService storeService;

	public ParserService(FileService fileService, TransactionService transactionService, StoreService storeService) {
		this.fileService = fileService;
		this.transactionService = transactionService;
		this.storeService = storeService;
	}

	@Transactional
	public List<Transaction> parserData(InputStream file) {

		Optional<List<String>> stringTransacoes = fileService.readLineFile(file);
		if (stringTransacoes.isPresent()) {
			List<String> transacoesList = stringTransacoes.get();
			List<Transaction> transacoes = transacoesList.stream().map(this::stringToTransacao)
					.collect(Collectors.toList());
			return transactionService.saveTransactions(transacoes);
		}
		return Collections.emptyList();
	}

	private Transaction stringToTransacao(String transaction) {
		return Transaction.builder().transactionType(getTransactionType(transaction))
				.date(getTranscationDate(transaction)).hour(getTransactionHour(transaction))
				.value(getTransactionValue(transaction)).card(getTransactionCard(transaction))
				.store(getSotre(transaction)).build();
	}

	private TransactionTypeEnum getTransactionType(String line) {
		return TransactionTypeEnum.getByCod(getData(line, TRANSACTION_TYPE));
	}

	private String getTranscationDate(String transaction) {
		return getData(transaction, DATE);
	}

	private String getTransactionHour(String transaction) {
		return getData(transaction, HOUR);
	}

	private BigDecimal getTransactionValue(String transaction) {
		BigDecimal value = BigDecimal.valueOf(parseDouble(getData(transaction, VALUE)));
		return value.divide(BigDecimal.valueOf(100));
	}

	private String getTransactionCard(String transacao) {
		return getData(transacao, CARD);
	}

	private Store getSotre(String transaction) {
		Store store = Store.builder().name(getData(transaction, STORE_NAME).trim()).owner(getStoreOwner(transaction))
				.build();
		return storeService.saveStore(store);
	}

	private Owner getStoreOwner(String transaction) {
		return Owner.builder().name(getData(transaction, STORE_OWNER).trim()).cpf(getData(transaction, CPF)).build();
	}

	private String getData(String line, DataTypeEnum dataType) {
		int beginIndex = dataType.getStart() - 1;
		int endIndex = beginIndex + dataType.getSize();
		return line.substring(beginIndex, endIndex);
	}
}
