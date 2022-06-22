package com.hugohenrick.desafiodev.service;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hugohenrick.desafiodev.entities.Owner;
import com.hugohenrick.desafiodev.entities.Store;
import com.hugohenrick.desafiodev.repository.StoreRepository;
import com.hugohenrick.desafiodev.services.StoreService;

public class StoreServiceTest {

	private StoreService storeService;

	private StoreRepository storeRepository;

	@BeforeEach
	public void beforeEach() {
		storeRepository = mock(StoreRepository.class);
		storeService = new StoreService(storeRepository);
	}

	@Test
	public void lookExistsngStore() {
		String storeName = "Name of Store";
		when(storeRepository.findByName(anyString())).thenReturn(getStoreFilled());

		Store storeSave = storeService.saveStore(Store.builder().name(storeName).build());

		assertNotNull(storeSave);
		assertEquals(storeName, storeSave.getName());
		assertNotNull(storeSave.getOwner());
	}

	@Test
	public void lookNotExistsngStore() {
		String storeName = "Name of Store";
		when(storeRepository.save(any())).thenReturn(getStoreFilled().get());

		Store storeSave = storeService.saveStore(Store.builder().name(storeName).build());

		assertNotNull(storeSave);
		assertEquals(storeName, storeSave.getName());
		assertNotNull(storeSave.getOwner());
	}

	@Test
	public void lookAllStores() {
		when(storeRepository.findAll()).thenReturn(listStores());

		List<Store> stores = storeService.getStores();
		assertFalse(stores.isEmpty());
		assertEquals(2, stores.size());
	}

	private Optional<Store> getStoreFilled() {
		return Optional.of(Store.builder().name("Name of Store").owner(Owner.builder().build()).build());
	}

	private List<Store> listStores() {

		Store store1 = Store.builder().build();
		Store store2 = Store.builder().build();

		return asList(store1, store2);
	}

}
