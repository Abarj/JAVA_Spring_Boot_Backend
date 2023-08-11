package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.request.ReservationRequest;
import com.example.block22SpringAdvanced.application.models.responses.ReservationResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.CustomerEntity;
import com.example.block22SpringAdvanced.domain.entities.jpa.HotelEntity;
import com.example.block22SpringAdvanced.domain.entities.jpa.ReservationEntity;
import com.example.block22SpringAdvanced.domain.repositories.jpa.CustomerRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.HotelRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.ReservationRepository;
import com.example.block22SpringAdvanced.infrastructure.dtos.CurrencyDTO;
import com.example.block22SpringAdvanced.infrastructure.helpers.ApiCurrencyConnectorHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.BlackListHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.CustomerHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.EmailHelper;
import com.example.block22SpringAdvanced.util.enums.Tables;
import com.example.block22SpringAdvanced.util.exceptions.ForbiddenCustomerException;
import com.example.block22SpringAdvanced.util.exceptions.IdNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Reservation Service Test")
class ReservationServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerHelper customerHelper;

    @Mock
    private BlackListHelper blackListHelper;

    @Mock
    private ApiCurrencyConnectorHelper currencyConnectorHelper;

    @Mock
    private EmailHelper emailHelper;

    @InjectMocks
    private ReservationService reservationService;


    @Test
    @DisplayName("Should throw an exception when the hotel id is not found")
    void findPriceWhenHotelIdNotFoundThenThrowException() {
        Long hotelId = 1L;
        Currency currency = Currency.getInstance("USD");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            reservationService.findPrice(hotelId, currency);
        });

        verify(hotelRepository, times(1)).findById(hotelId);
    }

    /*@Test
    @DisplayName("Should return the price in dollars when the currency is USD")
    void findPriceWhenCurrencyIsUsd() {
        Long hotelId = 1L;
        Currency currency = Currency.getInstance("USD");
        BigDecimal priceInDollars = BigDecimal.valueOf(100);

        // Mocking the hotel repository
        HotelEntity hotel = HotelEntity.builder()
                .id(hotelId)
                .price(priceInDollars)
                .build();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        // Mocking the currency connector helper
        CurrencyDTO currencyDto = new CurrencyDTO();
        currencyDto.setExchangeDate(LocalDate.now());
        currencyDto.setRates(Map.of(currency, BigDecimal.ONE));
        when(currencyConnectorHelper.getCurrency(currency)).thenReturn(currencyDto);

        // Calling the method under test
        BigDecimal result = reservationService.findPrice(hotelId, currency);

        // Verifying the result
        assertEquals(priceInDollars, result);

        // Verifying the method calls
        verify(hotelRepository, times(1)).findById(hotelId);
        verify(currencyConnectorHelper, times(0)).getCurrency(currency);
    }*/

    @Test
    @DisplayName("Should return the price in the specified currency when the currency is not USD")
    void findPriceWhenCurrencyIsNotUsd() {
        Long hotelId = 1L;
        Currency currency = Currency.getInstance("EUR");

        // Mocking the hotel entity
        HotelEntity hotelEntity = HotelEntity.builder()
                .id(hotelId)
                .name("Hotel ABC")
                .address("123 Main St")
                .rating(4)
                .price(BigDecimal.valueOf(100))
                .build();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotelEntity));

        // Mocking the currency connector helper
        CurrencyDTO currencyDto = new CurrencyDTO();
        currencyDto.setExchangeDate(LocalDate.now());
        currencyDto.setRates(Map.of(Currency.getInstance("EUR"), BigDecimal.valueOf(0.85)));
        when(currencyConnectorHelper.getCurrency(currency)).thenReturn(currencyDto);

        // Calling the method under test
        BigDecimal price = reservationService.findPrice(hotelId, currency);

        // Asserting the result
        BigDecimal expectedPrice = BigDecimal.valueOf(100).add(BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(0.20))).multiply(BigDecimal.valueOf(0.85));
        assertEquals(expectedPrice, price);

        // Verifying the method calls
        verify(hotelRepository, times(1)).findById(hotelId);
        verify(currencyConnectorHelper, times(1)).getCurrency(currency);
    }

    @Test
    @DisplayName("Should throw an exception when the id does not exist")
    void deleteReservationWhenIdDoesNotExistThenThrowException() {
        UUID id = UUID.randomUUID();
        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> reservationService.delete(id));

        verify(reservationRepository, times(1)).findById(id);
        verify(reservationRepository, never()).delete(any(ReservationEntity.class));
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    @DisplayName("Should delete the reservation when the id exists")
    void deleteReservationWhenIdExists() {
        UUID reservationId = UUID.randomUUID();
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .id(reservationId)
                .build();

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservationEntity));

        reservationService.delete(reservationId);

        verify(reservationRepository, times(1)).delete(reservationEntity);
    }

    @Test
    @DisplayName("Should throw an exception when the hotel ID is not found")
    void updateReservationWhenHotelIdNotFoundThenThrowException() {
        UUID reservationId = UUID.randomUUID();
        ReservationRequest request = ReservationRequest.builder()
                .idHotel(123L)
                .totalDays(5)
                .build();

        when(hotelRepository.findById(request.getIdHotel())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            reservationService.update(request, reservationId);
        });

        verify(hotelRepository, times(1)).findById(request.getIdHotel());
        verify(reservationRepository, never()).findById(any(UUID.class));
        verify(reservationRepository, never()).save(any(ReservationEntity.class));
        verify(customerHelper, never()).increase(anyString(), any(Class.class));
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }

    /*@Test
    @DisplayName("Should throw an exception when the reservation ID is not found")
    void updateReservationWhenReservationIdNotFoundThenThrowException() {
        UUID reservationId = UUID.randomUUID();
        ReservationRequest request = new ReservationRequest();
        request.setIdHotel(1L);
        request.setTotalDays(5);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            reservationService.update(request, reservationId);
        });

        verify(reservationRepository, times(1)).findById(reservationId);
        verify(hotelRepository, never()).findById(anyLong());
        verify(reservationRepository, never()).save(any(ReservationEntity.class));
        verify(customerHelper, never()).increase(anyString(), any());
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }*/

    /*@Test
    @DisplayName("Should update the reservation when the hotel and reservation IDs are valid")
    void updateReservationWhenHotelAndReservationIdsAreValid() {        // Create test data
        UUID reservationId = UUID.randomUUID();
        ReservationRequest request = ReservationRequest.builder()
                .idHotel(1L)
                .totalDays(5)
                .build();
        // Create mock data
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .id(reservationId)
                .build();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservationEntity));
        when(hotelRepository.findById(request.getIdHotel())).thenReturn(Optional.of(new HotelEntity()));

        // Call the method under test
        ReservationResponse response = reservationService.update(request, reservationId);

        // Verify the interactions and assertions
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(hotelRepository, times(1)).findById(request.getIdHotel());
        verify(reservationRepository, times(1)).save(any(ReservationEntity.class));

        assertNotNull(response);
        assertEquals(reservationId, response.getId());
        // Add more assertions based on the expected behavior of the method
    }*/
//--------------------------------
    @Test
    @DisplayName("Should throw an exception when the id does not exist")
    void readWhenIdDoesNotExistThenThrowException() {
        UUID id = UUID.randomUUID();

        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            reservationService.read(id);
        });

        verify(reservationRepository, times(1)).findById(id);
    }

    /*@Test
    @DisplayName("Should return the reservation when the id exists")
    void readWhenIdExists() {
        UUID reservationId = UUID.randomUUID();
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .id(reservationId)
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(5))
                .totalDays(5)
                .price(BigDecimal.valueOf(100))
                .build();

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservationEntity));

        ReservationResponse response = reservationService.read(reservationId);

        assertNotNull(response);
        assertEquals(reservationId, response.getId());
        assertEquals(reservationEntity.getDateTimeReservation(), response.getDateTimeReservation());
        assertEquals(reservationEntity.getDateStart(), response.getDateStart());
        assertEquals(reservationEntity.getDateEnd(), response.getDateEnd());
        assertEquals(reservationEntity.getTotalDays(), response.getTotalDays());
        assertEquals(reservationEntity.getPrice(), response.getPrice());
        assertNotNull(response.getHotel());
        verify(reservationRepository, times(1)).findById(reservationId);
    }*/

    @Test
    @DisplayName("Should throw an exception when the customer is in the blacklist")
    void createReservationWhenCustomerIsInBlackListThenThrowException() {
        ReservationRequest request = ReservationRequest.builder()
                .idClient("1234567890")
                .idHotel(1L)
                .totalDays(5)
                .email("test@example.com")
                .build();

        doThrow(new ForbiddenCustomerException()).when(blackListHelper).isInBlackListCustomer(request.getIdClient());

        // Act and Assert
        assertThrows(ForbiddenCustomerException.class, () -> reservationService.create(request));

        // Verify
        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getIdClient());
        verifyNoInteractions(hotelRepository, customerRepository, reservationRepository, customerHelper, currencyConnectorHelper, emailHelper);
    }

    /*@Test
    @DisplayName("Should throw an exception when the customer id is not found")
    void createReservationWhenCustomerIdNotFoundThenThrowException() {
        ReservationRequest request = ReservationRequest.builder()
                .idClient("123456789")
                .idHotel(1L)
                .totalDays(5)
                .email("test@example.com")
                .build();

        when(customerRepository.findById(request.getIdClient())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> reservationService.create(request));

        // Verify
        verify(customerRepository, times(1)).findById(request.getIdClient());
        verify(hotelRepository, never()).findById(anyLong());
        verify(reservationRepository, never()).save(any(ReservationEntity.class));
        verify(customerHelper, never()).increase(anyString(), any(Class.class));
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }*/

    @Test
    @DisplayName("Should throw an exception when the hotel id is not found")
    void createReservationWhenHotelIdNotFoundThenThrowException() {
        ReservationRequest request = ReservationRequest.builder()
                .idClient("1234567890")
                .idHotel(1L)
                .totalDays(5)
                .email("test@example.com")
                .build();

        when(hotelRepository.findById(request.getIdHotel())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> reservationService.create(request));

        // Verify
        verify(hotelRepository, times(1)).findById(request.getIdHotel());
        verify(customerRepository, never()).findById(anyString());
        verify(reservationRepository, never()).save(any(ReservationEntity.class));
        verify(customerHelper, never()).increase(anyString(), any(Class.class));
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }

    /*@Test
    @DisplayName("Should create a reservation when the customer is not in the blacklist")
    void createReservationWhenCustomerIsNotInBlackList() {
        ReservationRequest request = ReservationRequest.builder()
                .idClient("1234567890")
                .idHotel(1L)
                .totalDays(5)
                .email("test@example.com")
                .build();

        HotelEntity hotel = HotelEntity.builder()
                .id(1L)
                .name("Hotel ABC")
                .address("123 Main St")
                .rating(4)
                .price(BigDecimal.valueOf(100))
                .build();

        CustomerEntity customer = CustomerEntity.builder()
                .dni("1234567890")
                .fullName("John Doe")
                .creditCard("1234567890123456")
                .phoneNumber("1234567890")
                .totalFlights(5)
                .totalLodgings(10)
                .totalTours(3)
                .build();

        ReservationEntity reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)))
                .build();

        doNothing().when(blackListHelper).isInBlackListCustomer(request.getIdClient());
        when(hotelRepository.findById(request.getIdHotel())).thenReturn(Optional.of(hotel));
        when(customerRepository.findById(request.getIdClient())).thenReturn(Optional.of(customer));
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationToPersist);

        ReservationResponse response = reservationService.create(request);

        assertNotNull(response);
        assertEquals(reservationToPersist.getId(), response.getId());
        assertEquals(reservationToPersist.getDateTimeReservation(), response.getDateTimeReservation());
        assertEquals(reservationToPersist.getDateStart(), response.getDateStart());
        assertEquals(reservationToPersist.getDateEnd(), response.getDateEnd());
        assertEquals(reservationToPersist.getTotalDays(), response.getTotalDays());
        assertEquals(reservationToPersist.getPrice(), response.getPrice());
        assertNotNull(response.getHotel());
        assertEquals(hotel.getId(), response.getHotel().getId());
        assertEquals(hotel.getName(), response.getHotel().getName());
        assertEquals(hotel.getAddress(), response.getHotel().getAddress());
        assertEquals(hotel.getRating(), response.getHotel().getRating());
        assertEquals(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)), response.getHotel().getPrice());

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getIdClient());
        verify(hotelRepository, times(1)).findById(request.getIdHotel());
        verify(customerRepository, times(1)).findById(request.getIdClient());
        verify(reservationRepository, times(1)).save(any(ReservationEntity.class));
        verify(customerHelper, times(1)).increase(customer.getDni(), ReservationService.class);
        verify(emailHelper, times(1)).sendMail(request.getEmail(), customer.getFullName(), Tables.reservation.name());
    }*/

    /*@Test
    @DisplayName("Should send an email when the email is provided")
    void createReservationWhenEmailIsProvidedThenSendEmail() {
        ReservationRequest request = ReservationRequest.builder()
                .idClient("1234567890")
                .idHotel(1L)
                .totalDays(5)
                .email("test@example.com")
                .build();

        HotelEntity hotel = HotelEntity.builder()
                .id(1L)
                .name("Hotel ABC")
                .address("123 Main St")
                .rating(4)
                .price(BigDecimal.valueOf(100))
                .build();

        CustomerEntity customer = CustomerEntity.builder()
                .dni("1234567890")
                .fullName("John Doe")
                .creditCard("1234567890123456")
                .phoneNumber("1234567890")
                .totalFlights(5)
                .totalLodgings(10)
                .totalTours(3)
                .build();

        ReservationEntity reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)))
                .build();

        ReservationEntity reservationPersisted = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)))
                .build();

        when(hotelRepository.findById(request.getIdHotel())).thenReturn(Optional.of(hotel));
        when(customerRepository.findById(request.getIdClient())).thenReturn(Optional.of(customer));
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationPersisted);

        ReservationResponse response = reservationService.create(request);

        assertNotNull(response);
        assertEquals(reservationPersisted.getId(), response.getId());
        assertEquals(reservationPersisted.getDateTimeReservation(), response.getDateTimeReservation());
        assertEquals(reservationPersisted.getDateStart(), response.getDateStart());
        assertEquals(reservationPersisted.getDateEnd(), response.getDateEnd());
        assertEquals(reservationPersisted.getTotalDays(), response.getTotalDays());
        assertEquals(reservationPersisted.getPrice(), response.getPrice());
        assertNotNull(response.getHotel());
        assertEquals(hotel.getId(), response.getHotel().getId());
        assertEquals(hotel.getName(), response.getHotel().getName());
        assertEquals(hotel.getAddress(), response.getHotel().getAddress());
        assertEquals(hotel.getRating(), response.getHotel().getRating());
        assertEquals(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)), response.getHotel().getPrice());

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getIdClient());
        verify(hotelRepository, times(1)).findById(request.getIdHotel());
        verify(customerRepository, times(1)).findById(request.getIdClient());
        verify(reservationRepository, times(1)).save(any(ReservationEntity.class));
        verify(customerHelper, times(1)).increase(customer.getDni(), ReservationService.class);
        verify(emailHelper, times(1)).sendMail(request.getEmail(), customer.getFullName(), Tables.reservation.name());
    }*/
}


