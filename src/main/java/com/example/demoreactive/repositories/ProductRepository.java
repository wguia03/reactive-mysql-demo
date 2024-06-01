package com.example.demoreactive.repositories;

import com.example.demoreactive.entities.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

    Flux<Product> findByCategory(String category);
}
