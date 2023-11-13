package com.gxstar.ssjwtauth.controller;

import com.gxstar.ssjwtauth.model.Product;
import com.gxstar.ssjwtauth.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody final Product product) {
        final Product savedProduct = productService.save(product);
        return ResponseEntity.created(URI.create("/api/v1/products")).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }
}
