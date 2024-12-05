package com.stech.api.service;

import com.stech.api.dto.ProductEvent;
import com.stech.api.entity.Product;
import com.stech.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product productDO = productRepository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct",productDO);
        kafkaTemplate.send("product-event-topic",event);
        return  productDO;
    }

    public Product updateProduct(long id, ProductEvent productEvent){
        Product newProductDO = productEvent.getProduct();
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(newProductDO.getName());
        existingProduct.setDescription(newProductDO.getDescription());
        existingProduct.setPrice(newProductDO.getPrice());
        Product productDO = productRepository.save(existingProduct);
        ProductEvent event = new ProductEvent("UpdateProduct",existingProduct);
        kafkaTemplate.send("product-event-topic",event);
        return  productDO;
    }

}
