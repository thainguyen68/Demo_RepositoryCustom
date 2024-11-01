package com.example.demo.service.impl;

import com.example.demo.Entity.Product;
import com.example.demo.controller.dto.SearchDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   @Override
    public List<Product> getProductsWithName(SearchDTO data) {
        return productRepository.findByName(data);
    }
}
