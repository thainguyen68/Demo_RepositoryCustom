package com.example.demo.repository.impl;

import com.example.demo.Entity.Product;
import com.example.demo.controller.dto.SearchDTO;
import com.example.demo.repository.CustomProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByName(SearchDTO data) {
        StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (data.getName() != null) {
            sql.append(" AND name LIKE :Name");
            params.put("Name", "%" + data.getName() + "%");
        }
        Query countQuery = entityManager.createNativeQuery("select count(1) " + sql);
        params.forEach(countQuery::setParameter);
        Number total = (Number) countQuery.getSingleResult();

        if (total.longValue() > 0) {
            Query query = entityManager.createNativeQuery(
                    sql.toString(),
                    Product.class
            );
            params.forEach(query::setParameter);
            return query.getResultList();
        }
        return null;
    }
}
