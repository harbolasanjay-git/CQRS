package com.stech.api.controller;

import com.stech.api.dto.ProductEvent;
import com.stech.api.entity.Product;
import com.stech.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductCommandController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent){
       return productService.createProduct(productEvent);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent){
        return productService.updateProduct(id,productEvent);
    }
}
