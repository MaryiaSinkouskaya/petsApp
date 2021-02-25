package com.leverx.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogService {

    ResponseEntity<List> findAll(String auth);

}
