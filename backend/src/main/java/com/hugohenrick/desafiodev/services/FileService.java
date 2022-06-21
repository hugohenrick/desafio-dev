package com.hugohenrick.desafiodev.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FileService {

	public Optional<List<String>> readLineFile(InputStream file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            return Optional.of(reader.lines().collect(Collectors.toList()));

        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
