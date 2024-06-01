package com.example.demoreactive.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("products")
public class Product {

    @Id
    private int productId;
    private String name;
    private double price;
    private int stock;
    private String category;
}
