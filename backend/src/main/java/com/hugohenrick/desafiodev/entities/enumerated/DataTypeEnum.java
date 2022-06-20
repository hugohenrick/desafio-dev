package com.hugohenrick.desafiodev.entities.enumerated;

public enum DataTypeEnum {

	TRANSACTION_TYPE(1, 1), 
	DATE(2, 8), 
	VALUE(10, 10), 
	CPF(20, 11), 
	CARD(31, 12), 
	HOUR(43, 6), 
	STORE_OWNER(49, 14),
	STORE_NAME(63, 18);

	private int start;
	private int size;

	DataTypeEnum(int start, int size) {
		this.start = start;
		this.size = size;
	}

	public int getStart() {
		return start;
	}

	public int getSize() {
		return size;
	}

}
