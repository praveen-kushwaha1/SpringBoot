package com.praveen.repository;

import com.praveen.entity.Product;
import com.praveen.entity.ProductX;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductX, Long> {
}
