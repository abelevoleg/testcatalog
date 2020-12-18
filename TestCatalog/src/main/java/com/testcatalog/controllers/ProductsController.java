package com.testcatalog.controllers;

import com.testcatalog.entities.Product;
import com.testcatalog.exceptions.ProductNotFoundException;
import com.testcatalog.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showAll(Model model) {
        List products = productsService.findAll();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/search")
    public String searchByTitle() {
        return "search_by_title";
    }

    @GetMapping("/title")
    public String showByTitle(@RequestParam String title, Model model) {
        try {
            model.addAttribute("product", productsService.findByTitle(title));
        } catch (ProductNotFoundException exc) {
            return "redirect:/products/";
        }
        return "product";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/del/{id}")
    public String delProduct(@PathVariable Long id) {
        productsService.deleteById(id);
        return "redirect:/products/";
    }
}