package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.request.ReservationRequest;
import com.example.block22SpringAdvanced.application.models.responses.HotelResponse;
import com.example.block22SpringAdvanced.application.models.responses.ReservationResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.ReservationEntity;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IReservationService;
import com.example.block22SpringAdvanced.infrastructure.helpers.ApiCurrencyConnectorHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.BlackListHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.CustomerHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.EmailHelper;
import com.example.block22SpringAdvanced.domain.repositories.jpa.CustomerRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.HotelRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.ReservationRepository;
import com.example.block22SpringAdvanced.util.enums.Tables;
import com.example.block22SpringAdvanced.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final HotelRepository hotelRepository;

    private final CustomerRepository customerRepository;

    private final ReservationRepository reservationRepository;

    private final CustomerHelper customerHelper;

    private final BlackListHelper blackListHelper;

    private final ApiCurrencyConnectorHelper currencyConnectorHelper;

    private final EmailHelper emailHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient()); // Comprobar que no esté en la BlackList

        var hotel = hotelRepository.findById(request.getIdHotel())
                .orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var customer = customerRepository.findById(request.getIdClient())
                .orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));
        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();

        var reservationPersisted = reservationRepository.save(reservationToPersist);
        customerHelper.increase(customer.getDni(), ReservationService.class);
        log.info("Reservation saved with id: {}", reservationPersisted.getId());
        if (Objects.nonNull(request.getEmail())) emailHelper.sendMail(request.getEmail(), customer.getFullName(), Tables.reservation.name());
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel = hotelRepository.findById(request.getIdHotel())
                .orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var reservationToUpdate = reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));

        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation created with id {}", reservationUpdated.getId());
        return this.entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
        this.reservationRepository.delete(reservationToDelete);
    }

    @Override
    public BigDecimal findPrice(Long hotelId, Currency currency) {
        var hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var priceInDollars = hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));

        if (currency.equals(Currency.getInstance("USD"))) return priceInDollars;
        var currencyDto = currencyConnectorHelper.getCurrency(currency);
        log.info("API Currency in {}, response: {}", currencyDto.getExchangeDate().toString(), currencyDto.getRates());
        return priceInDollars.multiply(currencyDto.getRates().get(currency));
    }

    private ReservationResponse entityToResponse(ReservationEntity entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }
    
    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20); // IVA
}
