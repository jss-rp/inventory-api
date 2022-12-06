package com.jss.inventory.controller;

import com.jss.inventory.dao.ProductDAO;
import com.jss.inventory.entity.Product;
import com.jss.inventory.repository.ProductRepository;
import com.jss.inventory.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private ProductRepository productRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Product> create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Mono<ProductDAO> takeOne(@PathVariable("id") Long id) {
        return productService.fetchAllProductData(id);
    }
}
