package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.application.models.responses.HotelResponse;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IHotelService;
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
@RequestMapping(path = "hotel")
@AllArgsConstructor
@Tag(name = "Hotel")
public class HotelController {

    private final IHotelService hotelService;

    @Operation(summary = "Get all hotels")
    @GetMapping
    @Notify(value = "GET hotel")
    public ResponseEntity<Page<HotelResponse>> getAll(@RequestParam Integer page,
                                                      @RequestParam Integer size,
                                                      @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = hotelService.readAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Searches hotels with the lowest price")
    @GetMapping(path = "less_price")
    public ResponseEntity<Set<HotelResponse>> getLessPrice(@RequestParam BigDecimal price) {
        var response = hotelService.readLessPrice(price);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Searches hotels with price between min & max")
    @GetMapping(path = "between_price")
    public ResponseEntity<Set<HotelResponse>> getBetweenPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        var response = hotelService.readBetweenPrices(min, max);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @Operation(summary = "Searches hotels by rating")
    @GetMapping(path = "rating")
    public ResponseEntity<Set<HotelResponse>> getByRating(@RequestParam Integer rating) {
        if(rating > 4) rating = 4;
        if (rating < 1) rating = 1;
        var response = hotelService.readByRating(rating);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
}
