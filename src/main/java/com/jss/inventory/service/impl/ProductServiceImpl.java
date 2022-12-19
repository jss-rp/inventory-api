package com.jss.inventory.service.impl;

import com.jss.inventory.dto.ProductDTO;
import com.jss.inventory.repository.ProductRepository;
import com.jss.inventory.repository.SKURepository;
import com.jss.inventory.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private SKURepository skuRepository;

    @Override
    public Mono<ProductDTO> fetchAllProductData(Long productId) {
        return productRepository
                .findById(productId)
                .flatMap(product -> {
                    final ProductDTO dao = ProductDTO.builder()
                            .id(product.getId())
                            .trendId(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .createdAt(product.getCreatedAt())
                            .skus(new ArrayList<>())
                            .build();

                    return skuRepository.findAllByProductId(productId)
                            .doOnNext(sku -> dao.getSkus().add(sku.getCode()))
                            .then(Mono.just(dao));
                });
    }
}
