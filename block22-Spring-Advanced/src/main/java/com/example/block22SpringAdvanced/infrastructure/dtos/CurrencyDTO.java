package com.example.block22SpringAdvanced.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO implements Serializable {

    @JsonProperty(value = "date")
    private LocalDate exchangeDate;

    private Map<Currency, BigDecimal> rates; // Currency tiene todos los ISO de cada país para poder mapear dinámicamente
}
