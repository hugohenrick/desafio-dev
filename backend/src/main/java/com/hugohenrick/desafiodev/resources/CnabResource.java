package com.hugohenrick.desafiodev.resources;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hugohenrick.desafiodev.entities.Transaction;
import com.hugohenrick.desafiodev.response.UploadResponse;
import com.hugohenrick.desafiodev.services.ParserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class CnabResource {
	
	@Autowired
	private ParserService parserService;

	@ApiOperation(value = "Realiza o Upload e processamento do arquivo CNAB")
    @RequestMapping(value = "/upload", method =  RequestMethod.POST, produces="application/json", consumes="multipart/form-data")
    public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<Long> transactionsSave = parserService.parserData(file.getInputStream())
                .stream()
                .map(Transaction::getId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UploadResponse(transactionsSave));
    }

}
