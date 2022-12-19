package com.jss.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Long trendId;

    private LocalTime createdAt;

    private List<String> skus;

}
