package com.stech.api.controller;

import com.stech.api.entity.Product;
import com.stech.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> fetchAllProducts(){
        return  productService.getProducts();
    }
}
