package com.gxstar.ssjwtauth.service.api;

import com.gxstar.ssjwtauth.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
}
