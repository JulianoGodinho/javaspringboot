package com.godinho.appproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.godinho.appproduct.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
