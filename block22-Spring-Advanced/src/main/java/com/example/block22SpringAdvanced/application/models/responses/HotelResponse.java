package com.example.block22SpringAdvanced.application.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelResponse implements Serializable {

    private Long id;

    private String name;

    private String address;

    private Integer rating;

    private BigDecimal price;
}
