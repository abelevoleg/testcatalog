package com.testcatalog.services;

import com.testcatalog.entities.Product;
import com.testcatalog.exceptions.ProductNotFoundException;
import com.testcatalog.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found product with id = " + id));
    }

    public Product findByTitle(String title) {
        return productsRepository.findByTitle(title).orElseThrow(() -> new ProductNotFoundException("Can't found product with title = " + title));
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }
}
