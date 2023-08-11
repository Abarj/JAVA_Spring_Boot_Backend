package com.example.block22SpringAdvanced.application.models.responses;

import com.example.block22SpringAdvanced.util.enums.Airlines;
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
public class FlyResponse implements Serializable {

    private Long id;

    private Double originLat;

    private Double originLng;

    private Double destinyLat;

    private Double destinyLng;

    private String originName;

    private String destinyName;

    private BigDecimal price;

    private Airlines airline;
}
