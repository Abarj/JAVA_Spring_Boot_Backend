package com.example.block22SpringAdvanced.infrastructure.abstract_services;

import com.example.block22SpringAdvanced.application.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse> {

    Set<HotelResponse> readByRating(Integer rating);
}
