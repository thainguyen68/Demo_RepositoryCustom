package com.example.demo.controller;

import com.example.demo.Entity.Product;
import com.example.demo.controller.dto.SearchDTO;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // {" ⏩ %7B
    // "  ⏩ %22
    // :  ⏩ %3A
    // ,  ⏩ %2C
    // }  ⏩ %7D
    // --------------------------------
    // {
    //  "name": "bia 333"
    // }
    // ------------⏬🔁🔁⏬--------------------
    // %7B%22name%22%3A%22Bia%22%7D
    // --------------------------------
    // http://localhost:8686/products/search?searchData=%7B%22name%22%3A%22Bia 333%22%7D

    @GetMapping("/search")
    public List<Product> getProducts(
            @RequestParam(required = false) String searchData
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        SearchDTO data = new SearchDTO();
        try {
            data = objectMapper.readValue(searchData, SearchDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productService.getProductsWithName(data);
    }


    @GetMapping("/search-by-body")
    public List<Product> getProducts(
            @RequestBody SearchDTO searchData
    ) {
        return productService.getProductsWithName(searchData);
    }
}
