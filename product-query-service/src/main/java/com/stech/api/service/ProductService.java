package com.stech.api.service;

import com.stech.api.dto.ProductEvent;
import com.stech.api.entity.Product;
import com.stech.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvent(ProductEvent productEvent){
    Product product = productEvent.getProduct();
    if(productEvent.getEventType().equals("CreateProduct")){
        productRepository.save(product);
    }
    if(productEvent.getEventType().equals("UpdateProduct")){
        Product existingProduct = productRepository.findById(productEvent.getProduct().getId()).get();
        Product newProduct = productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setPrice(newProduct.getPrice());
        productRepository.save(existingProduct);
    }
    }
}
