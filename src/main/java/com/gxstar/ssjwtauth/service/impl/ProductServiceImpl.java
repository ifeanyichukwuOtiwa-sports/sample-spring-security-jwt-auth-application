package com.gxstar.ssjwtauth.service.impl;

import com.gxstar.ssjwtauth.model.Product;
import com.gxstar.ssjwtauth.repository.ProductRepository;
import com.gxstar.ssjwtauth.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
