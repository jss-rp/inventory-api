package com.jss.inventory.controller;

import com.jss.inventory.entity.Product;
import com.jss.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    private WebTestClient client;

    @Test
    void productCreation() {
        final Product product = new Product();
        product.setId(1L);
        product.setName("Omnitrix");
        product.setDescription("Dispositivo usado como relógio, porém é capaz de transformar o usuário em monstros.");

        Mockito.when(productRepository.save(product)).thenReturn(Mono.just(product));

        client.post()
                .uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(product))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void takeOne() {
        final Product product = new Product();
        product.setId(1L);
        product.setName("Omnitrix");
        product.setDescription("Dispositivo usado como relógio, porém é capaz de transformar o usuário em monstros.");
        product.setCreatedAt(LocalDateTime.now());

        Mockito.when(productRepository.findById(1L)).thenReturn(Mono.just(product));

        client.get().uri("/product/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.name").isEqualTo("Omnitrix")
                .jsonPath("$.description").isNotEmpty()
                .jsonPath("$.createdAt").isNotEmpty();

        Mockito.verify(productRepository, Mockito.times(1)).findById(1L);
    }
}
