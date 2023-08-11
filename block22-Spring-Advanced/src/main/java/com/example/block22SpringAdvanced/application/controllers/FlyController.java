package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.application.models.responses.FlyResponse;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IFlyService;
import com.example.block22SpringAdvanced.util.annotations.Notify;
import com.example.block22SpringAdvanced.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
@Tag(name = "Fly")
public class FlyController {

    private final IFlyService flyService;

    @Operation(summary = "Get all flights")
    @GetMapping
    @Notify(value = "GET fly")
    public ResponseEntity<Page<FlyResponse>> getAll(@RequestParam Integer page,
                                                    @RequestParam Integer size,
                                                    @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = flyService.readAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Searches flights with the lowest price")
    @GetMapping(path = "less_price")
    public ResponseEntity<Set<FlyResponse>> getLessPrice(@RequestParam BigDecimal price) {
        var response = flyService.readLessPrice(price);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Searches flights with price between min & max")
    @GetMapping(path = "between_price")
    public ResponseEntity<Set<FlyResponse>> getBetweenPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        var response = flyService.readBetweenPrices(min, max);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Searches flights by origin/destiny")
    @GetMapping(path = "origin_destiny")
    public ResponseEntity<Set<FlyResponse>> getByOriginDestiny(@RequestParam String origin, @RequestParam String destiny) {
        var response = flyService.readByOriginDestiny(origin, destiny);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
}
