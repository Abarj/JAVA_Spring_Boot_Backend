package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.responses.HotelResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.HotelEntity;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IHotelService;
import com.example.block22SpringAdvanced.domain.repositories.jpa.HotelRepository;
import com.example.block22SpringAdvanced.util.constants.CacheConstants;
import com.example.block22SpringAdvanced.util.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@Slf4j
@AllArgsConstructor
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Page<HotelResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return hotelRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    @Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
    public Set<HotelResponse> readLessPrice(BigDecimal price) {
        return this.hotelRepository.findByPriceLessThan(price)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
    public Set<HotelResponse> readBetweenPrices(BigDecimal min, BigDecimal max) throws IllegalArgumentException {
        if (min == null || max == null || min.compareTo(max) > 0) {
            throw new IllegalArgumentException();
        }
        return this.hotelRepository.findByPriceIsBetween(min, max)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstants.HOTEL_CACHE_NAME)
    public Set<HotelResponse> readByRating(Integer rating) {
        return this.hotelRepository.findByRatingGreaterThan(rating)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());
    }

    private HotelResponse entityToResponse(HotelEntity entity) {
        var response = new HotelResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
