package com.jss.inventory.controller;

import com.jss.inventory.configuration.SecurityConfiguration;
import com.jss.inventory.dto.ProductDTO;
import com.jss.inventory.entity.Product;
import com.jss.inventory.repository.ProductRepository;
import com.jss.inventory.repository.SKURepository;
import com.jss.inventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;


@Import(SecurityConfiguration.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    SKURepository skuRepository;

    @MockBean
    ProductService productService;


    WebTestClient client;

    @Autowired
    void setup(final ApplicationContext context) {
        this.client = WebTestClient
                .bindToApplicationContext(context)
                .apply(springSecurity())
                .configureClient()
                .defaultHeaders(headers -> headers.setBasicAuth("username", "password"))
                .build();
    }

    @Test
    @WithMockUser
    void successfulProductCreation() {
        final Product product = new Product();
        product.setId(1L);
        product.setTrendId(1L);
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
    @WithMockUser
    void successfulProductGetting() {
        final Product product = new Product();
        product.setId(1L);
        product.setName("Omnitrix");
        product.setDescription("Dispositivo usado como relógio, porém é capaz de transformar o usuário em monstros.");
        product.setCreatedAt(LocalTime.from(LocalDateTime.now()));

        final ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("Omnitrix");
        dto.setDescription("Dispositivo usado como relógio, porém é capaz de transformar o usuário em monstros.");
        dto.setCreatedAt(product.getCreatedAt());
        dto.setTrendId(1L);

        Mockito.when(productService.fetchAllProductData(1L)).thenReturn(Mono.just(dto));
        Mockito.when(skuRepository.findAllByProductId(1L)).thenReturn(Flux.empty());

        client.get().uri("/product/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.name").isEqualTo("Omnitrix")
                .jsonPath("$.description").isNotEmpty()
                .jsonPath("$.createdAt").isNotEmpty();
    }
}
