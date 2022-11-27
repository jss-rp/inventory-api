package com.jss.inventory.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table
public class Trend {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("created_at")
    private LocalDateTime createdAt;
}
