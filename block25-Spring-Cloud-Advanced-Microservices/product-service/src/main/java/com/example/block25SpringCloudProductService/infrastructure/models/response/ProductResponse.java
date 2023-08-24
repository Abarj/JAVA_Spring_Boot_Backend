package com.example.block25SpringCloudProductService.infrastructure.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}
