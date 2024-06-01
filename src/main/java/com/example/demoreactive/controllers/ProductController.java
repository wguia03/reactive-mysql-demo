package com.example.demoreactive.controllers;

import com.example.demoreactive.services.ProductService;
import com.example.demoreactive.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Flux<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<Mono<Product>> getProductById(@RequestParam int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/byCat")
    public ResponseEntity<Flux<Product>> getProductsByCategory(@RequestParam String category) {
        return new ResponseEntity<>(productService.getProductsByCategory(category), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mono<Product>> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @PutMapping
    public Mono<ResponseEntity<Product>> updateProduct(@RequestParam int id, @RequestBody Product product) {
        return productService.updateProduct(id, product)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
    }

    @DeleteMapping
    public ResponseEntity<Mono<Void>> deleteProduct(@RequestParam int id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
