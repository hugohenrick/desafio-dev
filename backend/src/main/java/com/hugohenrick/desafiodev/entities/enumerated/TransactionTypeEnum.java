package com.hugohenrick.desafiodev.entities.enumerated;

import java.util.Arrays;

public enum TransactionTypeEnum {

	DEBITO(1, "Débito", "+"), 
	BOLETO(2, "Boleto", "-"), 
	FINANCIAMENTO(3, "Financiamento", "-"),
	CREDITO(4, "Crédito", "+"), 
	RECEBIMENTO_EMPRESTIMO(5, "Recebimento Empréstimo", "+"), 
	VENDAS(6, "Vendas", "+"),
	RECEBIMENTO_TED(7, "Recebimento TED", "+"), 
	RECEBIMENTO_DOC(8, "Recebimento DOC", "+"), 
    ALUGUEL(9, "Aluguel", "-"),
	UNEXPECTED_VALUE(99, "Valor não esperado", "");

	private int cod;
	private String signal;
	private String description;

	TransactionTypeEnum(int cod, String description, String signal) {
		this.cod = cod;
		this.description = description;
		this.signal = signal;
	}

	public static TransactionTypeEnum getByCod(String cod) {
		return Arrays.stream(values()).filter(value -> value.getCod() == Integer.parseInt(cod)).findFirst()
				.orElse(TransactionTypeEnum.UNEXPECTED_VALUE);
	}

	public int getCod() {
		return cod;
	}

	public String getSignal() {
		return signal;
	}

	public String getDescription() {
		return description;
	}

}
