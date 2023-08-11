package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.request.TourRequest;
import com.example.block22SpringAdvanced.application.models.responses.TourResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.*;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.ITourService;
import com.example.block22SpringAdvanced.infrastructure.helpers.BlackListHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.CustomerHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.EmailHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.TourHelper;
import com.example.block22SpringAdvanced.domain.repositories.jpa.CustomerRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.FlyRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.HotelRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.TourRepository;
import com.example.block22SpringAdvanced.util.enums.Tables;
import com.example.block22SpringAdvanced.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;

    private final FlyRepository flyRepository;

    private final HotelRepository hotelRepository;

    private final CustomerRepository customerRepository;

    private final TourHelper tourHelper;

    private final CustomerHelper customerHelper;

    private final BlackListHelper blackListHelper;

    private final EmailHelper emailHelper;

    @Override
    public TourResponse create(TourRequest request) {
        blackListHelper.isInBlackListCustomer(request.getCustomerId());

        var customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));
        var flights = new HashSet<FlyEntity>();
        request.getFlights().forEach(fly -> flights.add(flyRepository.findById(fly.getId())
                .orElseThrow(() -> new IdNotFoundException(Tables.fly.name()))));
        var hotels = new HashMap<HotelEntity, Integer>();
        request.getHotels().forEach(hotel -> hotels.put(hotelRepository.findById(hotel.getId())
                .orElseThrow(), hotel.getTotalDays()));
        var tourToPersist = TourEntity.builder()
                .tickets(tourHelper.createTickets(flights, customer))
                .reservations(tourHelper.createReservations(hotels, customer))
                .customer(customer)
                .build();
        var tourPersisted = tourRepository.save(tourToPersist);
        log.info("Tour saved with id: {}", tourPersisted.getId());
        customerHelper.increase(customer.getDni(), TourService.class);
        if (Objects.nonNull(request.getEmail())) emailHelper.sendMail(request.getEmail(), customer.getFullName(), Tables.tour.name());
        return TourResponse.builder()
                .reservationIds(tourPersisted.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketsIds(tourPersisted.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourPersisted.getId())
                .build();
    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDb = tourRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        return TourResponse.builder()
                .reservationIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketsIds(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
        var tourToDelete = tourRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        tourRepository.delete(tourToDelete);
    }

    @Override
    public UUID addTicket(Long tourId, Long flyId) {
        var tourUpdate = tourRepository.findById(tourId)
                .orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        var fly = flyRepository.findById(flyId)
                .orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
        var ticket = tourHelper.createTicket(fly, tourUpdate.getCustomer());

        tourUpdate.addTicket(ticket);
        tourRepository.save(tourUpdate);
        return ticket.getId();
    }

    @Override
    public void deleteTicket(Long tourId, UUID ticketId) {
        var tourUpdate = tourRepository.findById(tourId)
                .orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        tourUpdate.removeTicket(ticketId);
        tourRepository.save(tourUpdate);
    }

    @Override
    public UUID addReservation(Long tourId, Long hotelId, Integer totalDays) {
        var tourUpdate = this.tourRepository.findById(tourId)
                .orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        var hotel = this.hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var reservation = this.tourHelper.createReservation(hotel, tourUpdate.getCustomer(), totalDays);

        tourUpdate.addReservation(reservation);
        this.tourRepository.save(tourUpdate);
        return reservation.getId();
    }

    @Override
    public void deleteReservation(Long tourId, UUID reservationId) {
        var tourUpdate = tourRepository.findById(tourId)
                .orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        tourUpdate.removeReservation(reservationId);
        tourRepository.save(tourUpdate);
    }
}
