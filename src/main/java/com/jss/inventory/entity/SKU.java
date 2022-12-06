package com.jss.inventory.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Data
@Table(name = "sku")
public class SKU {

    @Column("code")
    private String code;

    @Column("product_id")
    private Long productId;

    @CreatedDate
    @Column("created_at")
    private LocalTime createdAt;

    @Column("specifications")
    private Map<String, Object> specifications;

    @Column("quantity")
    private Integer quantity;

    @Column("price")
    private BigDecimal price;
}
