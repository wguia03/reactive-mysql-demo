package com.example.demoreactive.services;

import com.example.demoreactive.entities.Product;
import com.example.demoreactive.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .delayElements(java.time.Duration.ofMillis(500));
    }

    @Override
    public Mono<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Flux<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return getProductById(product.getProductId())
                .switchIfEmpty(Mono.just(product).flatMap(productRepository::save));
    }

    @Override
    public Mono<Product> updateProduct(int id, Product product) {
        return getProductById(id)
                .flatMap(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setStock(product.getStock());
                    p.setCategory(product.getCategory());
                    return productRepository.save(p);
                });
    }

    @Override
    public Mono<Void> deleteProduct(int id) {
        return getProductById(id).flatMap(productRepository::delete);
    }
}
