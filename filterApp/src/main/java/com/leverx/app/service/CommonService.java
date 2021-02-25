package com.leverx.app.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    List findAll(String auth);
}
