package com.jss.inventory.service;

import com.jss.inventory.dto.ProductDTO;
import reactor.core.publisher.Mono;


public interface ProductService {

    Mono<ProductDTO> fetchAllProductData(Long productId);
}
