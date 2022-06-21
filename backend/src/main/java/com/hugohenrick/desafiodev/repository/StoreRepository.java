package com.hugohenrick.desafiodev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugohenrick.desafiodev.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	Optional<Store> findByName(String name);

//	@Override
//    @Query("SELECT s FROM Store s JOIN FETCH s.owner LEFT JOIN FETCH s.transactions")
//    List<Store> findAll();
}

