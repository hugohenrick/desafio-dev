package com.hugohenrick.desafiodev.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	
	@OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Transaction> transactions;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
