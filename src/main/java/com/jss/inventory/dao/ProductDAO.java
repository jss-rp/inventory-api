package com.jss.inventory.dao;

import com.jss.inventory.entity.SKU;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductDAO {

    private Long id;

    private String name;

    private String description;

    private Long trendId;

    private LocalTime createdAt;

    private List<String> skus;

}
