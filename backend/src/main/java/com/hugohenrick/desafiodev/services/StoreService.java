package com.hugohenrick.desafiodev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hugohenrick.desafiodev.entities.Store;
import com.hugohenrick.desafiodev.repository.StoreRepository;

@Service
public class StoreService {
	
    @Autowired
    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional(readOnly = true)
    public Store saveStore(Store store) {

        Optional<Store> storeSave = storeRepository.findByName(store.getName());
        return storeSave.orElseGet(() -> storeRepository.save(store));
    }

    public List<Store> getStores() {
    	return storeRepository.findAll(); 
    	
    }

}
