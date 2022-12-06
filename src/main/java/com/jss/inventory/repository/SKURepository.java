package com.jss.inventory.repository;

import com.jss.inventory.entity.SKU;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SKURepository extends ReactiveCrudRepository<SKU, String> {

    Flux<SKU> findAllByProductId(Long productId);
}
