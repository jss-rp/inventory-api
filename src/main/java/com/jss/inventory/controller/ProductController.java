package com.jss.inventory.controller;

import com.jss.inventory.dto.ProductDTO;
import com.jss.inventory.entity.Product;
import com.jss.inventory.entity.SKU;
import com.jss.inventory.repository.ProductRepository;
import com.jss.inventory.repository.SKURepository;
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

    private SKURepository skuRepository;

    private ProductRepository productRepository;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Product> create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Mono<ProductDTO> takeOne(@PathVariable("id") Long id) {
        return productService.fetchAllProductData(id);
    }

    @GetMapping("/{id}/sku/{code}")
    public Mono<SKU> getSku(@PathVariable("id") Long id, @PathVariable("code") String code) {
        return skuRepository.findFirstByCodeAndProductId(code, id);
    }
}
