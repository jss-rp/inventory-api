package com.jss.inventory.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Table(name = "product")
public class Product {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("trend_id")
    private Long trendId;

    @CreatedDate
    @Column("created_at")
    private LocalTime createdAt;
}
