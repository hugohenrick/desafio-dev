package com.hugohenrick.desafiodev.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hugohenrick.desafiodev.dto.StoreDTO;
import com.hugohenrick.desafiodev.services.StoreService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class StoreResource {
	
	@Autowired
	private StoreService service;
    
	@ApiOperation(value = "Retorna a lista de todas as Lojas")
	@RequestMapping(value = "/stores", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<StoreDTO>> getStoreTransactions() {    	
        List<StoreDTO> stores = service.getStores()
                .stream()
                .map(StoreDTO::of)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(stores);
    }

}
