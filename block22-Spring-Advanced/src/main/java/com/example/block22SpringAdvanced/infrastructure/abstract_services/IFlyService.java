package com.example.block22SpringAdvanced.infrastructure.abstract_services;

import com.example.block22SpringAdvanced.application.models.responses.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogService<FlyResponse> {

    Set<FlyResponse> readByOriginDestiny(String origin, String destiny);
}
