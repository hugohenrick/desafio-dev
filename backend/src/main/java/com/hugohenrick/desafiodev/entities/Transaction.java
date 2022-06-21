package com.hugohenrick.desafiodev.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hugohenrick.desafiodev.entities.enumerated.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TRANSACTION_TYPE")
	private TransactionTypeEnum transactionType;

	@Column(name = "TRANSACATION_DATE")
	private String date;

	@Column(name = "TRANSACATION_HOUR")
	private String hour;

	@Column(name = "TRANSACATION_VALUE")
	private BigDecimal value;

	@Column(name = "TRANSACATION_CARD")
	private String card;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;

	@Override
	public String toString() {
		return "Transacao{" + "id=" + id + ", transactionType=" + transactionType + ", date='" + date
				+ '\'' + ", hour='" + hour + '\'' + ", value=" + value + ", card='"
				+ card + '\'' + ", store=" + store + '}';
	}

}
