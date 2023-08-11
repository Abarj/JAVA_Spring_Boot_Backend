package com.example.block22SpringAdvanced.infrastructure.abstract_services;

import com.example.block22SpringAdvanced.application.models.request.ReservationRequest;
import com.example.block22SpringAdvanced.application.models.responses.ReservationResponse;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID> {

    BigDecimal findPrice(Long hotelId, Currency currency);
}
