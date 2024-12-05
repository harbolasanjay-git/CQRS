package com.stech.api.dto;

import com.stech.api.entity.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private String eventType;
    private Product product;
}
