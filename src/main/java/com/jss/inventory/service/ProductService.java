package com.jss.inventory.service;

import com.jss.inventory.dao.ProductDAO;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDAO> fetchAllProductData(Long productId);
}
