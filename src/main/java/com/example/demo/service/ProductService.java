package com.example.demo.service;

import com.example.demo.Entity.Product;
import com.example.demo.controller.dto.SearchDTO;

import java.util.List;

public interface ProductService {
    List<Product> getProductsWithName(SearchDTO data);
}
