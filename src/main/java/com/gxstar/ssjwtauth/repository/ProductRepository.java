package com.gxstar.ssjwtauth.repository;

import com.gxstar.ssjwtauth.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
