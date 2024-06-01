package com.example.demoreactive.services;

import com.example.demoreactive.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> getAllProducts();

    Flux<Product> getProductsByCategory(String category);

    Mono<Product> getProductById(int id);

    Mono<Product> saveProduct(Product product);

    Mono<Product> updateProduct(int id, Product product);

    Mono<Void> deleteProduct(int id);
}
