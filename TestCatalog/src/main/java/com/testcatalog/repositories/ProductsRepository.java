package com.testcatalog.repositories;

import com.testcatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>{
    Optional<Product> findByTitle(String title);
}
