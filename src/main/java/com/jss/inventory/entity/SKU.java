package com.jss.inventory.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Table(name = "sku")
public class SKU {

    @Column
    private String code;

    private Long productId;

    @CreatedDate
    private LocalDateTime createdAt;

    private Map<String, Object> specifications;
}
