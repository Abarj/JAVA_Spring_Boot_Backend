package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.responses.FlyResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.FlyEntity;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IFlyService;
import com.example.block22SpringAdvanced.domain.repositories.jpa.FlyRepository;
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
public class FlyService implements IFlyService {

    private final FlyRepository flyRepository;

    @Override
    public Page<FlyResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return flyRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    @Cacheable(value = CacheConstants.FLY_CACHE_NAME)
    public Set<FlyResponse> readLessPrice(BigDecimal price) {
        return this.flyRepository.selectLessPrice(price)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstants.FLY_CACHE_NAME)
    public Set<FlyResponse> readBetweenPrices(BigDecimal min, BigDecimal max) throws IllegalArgumentException {
        if (min == null || max == null) {
            throw new IllegalArgumentException();
        }
        return this.flyRepository.selectBetweenPrice(min, max)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstants.FLY_CACHE_NAME)
    public Set<FlyResponse> readByOriginDestiny(String origin, String destiny) throws IllegalArgumentException {
        if (origin == null || destiny == null ) {
            throw new IllegalArgumentException();
        }
        return this.flyRepository.selectOriginDestiny(origin, destiny)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());
    }

    private FlyResponse entityToResponse(FlyEntity entity) {
        var response = new FlyResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
