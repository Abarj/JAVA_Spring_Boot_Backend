package com.example.block22SpringAdvanced.infrastructure.abstract_services;

import com.example.block22SpringAdvanced.application.models.request.TourRequest;
import com.example.block22SpringAdvanced.application.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long> {

    void deleteTicket(Long tourId, UUID ticketId);
    UUID addTicket(Long tourId, Long flyId);
    void deleteReservation(Long tourId, UUID reservationId);
    UUID addReservation(Long reservationId, Long tourId, Integer totalDays);
}
