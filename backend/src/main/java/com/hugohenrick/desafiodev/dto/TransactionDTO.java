package com.hugohenrick.desafiodev.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.entities.enumerated.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private TransactionTypeEnum transactionType;

	private String date;

	private BigDecimal value;
	
	private String description;
	
	private String signal;

	
	public TransactionDTO(Transaction entity) {
		this.date = entity.getDate();
		this.value = entity.getValue();
		this.transactionType = entity.getTransactionType();
	}

}
