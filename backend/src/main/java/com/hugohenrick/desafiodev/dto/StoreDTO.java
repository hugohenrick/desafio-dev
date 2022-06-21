package com.hugohenrick.desafiodev.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hugohenrick.desafiodev.entities.Store;
import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.entities.enumerated.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
public class StoreDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	
	public StoreDTO(Store entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

    private BigDecimal balance;
    private List<TransactionDTO> transactions;

    public static StoreDTO of(Store store) {

        BigDecimal sum = BigDecimal.ZERO;
        List<TransactionDTO> transactionsResponse = new ArrayList<>();

        List<Transaction> transactions = store.getTransactions();

        for (Transaction tr: transactions) {

        	TransactionTypeEnum transactionType = tr.getTransactionType();

            if ("+".equals(transactionType.getSignal())) {
                sum = sum.add(tr.getValue());
            } else {
                sum = sum.subtract(tr.getValue());
            }

            transactionsResponse.add(TransactionDTO.builder()
                                        .date(tr.getDate())
                                        .description(transactionType.getDescription())
                                        .signal(transactionType.getSignal())
                                        .value(tr.getValue())
                                        .build()
            );
        }
        return StoreDTO.builder()
        		.id(store.getId())
                .name(store.getName())
                .balance(sum)
                .transactions(transactionsResponse)
                .build();
    }


}
