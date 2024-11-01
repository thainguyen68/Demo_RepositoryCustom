package com.example.demo.repository;

import com.example.demo.Entity.Product;
import com.example.demo.controller.dto.SearchDTO;

import java.util.List;


public interface CustomProductRepository {
    List<Product> findByName(SearchDTO data);
}
