package com.example.block22SpringAdvanced.infrastructure.abstract_services;

import com.example.block22SpringAdvanced.util.enums.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Set;

public interface CatalogService<R> {

    Page<R> readAll(Integer page, Integer size, SortType sortType);
    Set<R> readLessPrice(BigDecimal price);
    Set<R> readBetweenPrices(BigDecimal min, BigDecimal max);
    String FIELD_BY_SORT = "price";
}
