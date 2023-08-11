package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.request.TicketRequest;
import com.example.block22SpringAdvanced.application.models.responses.FlyResponse;
import com.example.block22SpringAdvanced.application.models.responses.TicketResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.CustomerEntity;
import com.example.block22SpringAdvanced.domain.entities.jpa.FlyEntity;
import com.example.block22SpringAdvanced.domain.entities.jpa.TicketEntity;
import com.example.block22SpringAdvanced.domain.repositories.jpa.CustomerRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.FlyRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.TicketRepository;
import com.example.block22SpringAdvanced.infrastructure.dtos.CurrencyDTO;
import com.example.block22SpringAdvanced.infrastructure.helpers.ApiCurrencyConnectorHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.BlackListHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.CustomerHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.EmailHelper;
import com.example.block22SpringAdvanced.util.enums.Airlines;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TicketService test")
class TicketServiceTest {

    @Mock
    private FlyRepository flyRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private CustomerHelper customerHelper;

    @Mock
    private BlackListHelper blackListHelper;

    @Mock
    private ApiCurrencyConnectorHelper currencyConnectorHelper;

    @Mock
    private EmailHelper emailHelper;

    @InjectMocks
    private TicketService ticketService;


    @Test
    @DisplayName("Should throw an exception when the flyId does not exist")
    void findPriceWhenFlyIdDoesNotExistThenThrowException() {
        Long flyId = 1L;
        Currency currency = Currency.getInstance("USD");

        when(flyRepository.findById(flyId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.findPrice(flyId, currency));

        verify(flyRepository, times(1)).findById(flyId);
    }

    /*@Test
    @DisplayName("Should return the price in the specified currency when the currency is not USD")
    void findPriceWhenCurrencyIsNotUsd() {
        Long flyId = 1L;
        Currency currency = Currency.getInstance("EUR");
        FlyEntity flyEntity = FlyEntity.builder()
                .id(flyId)
                .price(BigDecimal.valueOf(100))
                .airline(Airlines.blue_sky
                )
                .build();

        when(flyRepository.findById(flyId)).thenReturn(Optional.of(flyEntity));
    }*/

    /*@Test
    @DisplayName("Should return the price in USD when the currency is USD")
    void findPriceWhenCurrencyIsUsd() {
        Long flyId = 1L;
        Currency currency = Currency.getInstance("USD");
        FlyEntity flyEntity = new FlyEntity();
        flyEntity.setPrice(BigDecimal.valueOf(100));

        when(flyRepository.findById(flyId)).thenReturn(Optional.of(flyEntity));
        when(currencyConnectorHelper.getCurrency(currency)).thenReturn(new CurrencyDTO(LocalDate.now(), Map.of(currency, BigDecimal.valueOf(1.0))));

        BigDecimal expectedPrice = flyEntity.getPrice().add(flyEntity.getPrice().multiply(TicketService.charges_price_percentage));

        BigDecimal actualPrice = ticketService.findPrice(flyId, currency);

        assertEquals(expectedPrice, actualPrice);
        verify(flyRepository, times(1)).findById(flyId);
        verify(currencyConnectorHelper, times(1)).getCurrency(currency);
    }*/

    /*
    @Test
    @DisplayName("Should delete the ticket when the id exists")
    void deleteTicketWhenIdExists() {
        UUID ticketId = UUID.randomUUID();
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketId);

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticketEntity));

        ticketService.delete(ticketId);

        verify(ticketRepository, times(1)).delete(ticketEntity);
    }

    @Test
    @DisplayName("Should throw an exception when the id does not exist")
    void deleteTicketWhenIdDoesNotExistThenThrowException() {
        UUID id = UUID.randomUUID();
        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.delete(id));

        verify(ticketRepository, times(1)).findById(id);
        verify(ticketRepository, never()).delete(any(TicketEntity.class));
        verifyNoMoreInteractions(ticketRepository);
    }

    @Test
    @DisplayName("Should throw an exception when the ticket ID is not found")
    void updateTicketWhenTicketIdNotFoundThenThrowException() {
        UUID ticketId = UUID.randomUUID();
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(1L)
                .email("test@example.com")
                .build();

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.update(request, ticketId));

        verify(ticketRepository, times(1)).findById(ticketId);
        verifyNoMoreInteractions(ticketRepository);
    }

    @Test
    @DisplayName("Should throw an exception when the fly ID is not found")
    void updateTicketWhenFlyIdNotFoundThenThrowException() {
        UUID ticketId = UUID.randomUUID();
        UUID flyId = UUID.randomUUID();
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(flyId)
                .email("test@example.com")
                .build();

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
        when(flyRepository.findById(flyId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.update(request, ticketId));

        verify(ticketRepository, times(1)).findById(ticketId);
        verify(flyRepository, times(1)).findById(flyId);
        verifyNoMoreInteractions(ticketRepository, flyRepository, customerRepository, customerHelper, blackListHelper, currencyConnectorHelper, emailHelper);
    }

    @Test
    @DisplayName("Should update the ticket when the ticket and fly IDs are valid")
    void updateTicketWhenTicketAndFlyIdsAreValid() {
        UUID ticketId = UUID.randomUUID();
        UUID flyId = UUID.randomUUID();
        TicketRequest request = TicketRequest.builder()
                .idClient("1234567890")
                .idFly(flyId)
                .email("test@example.com")
                .build();
        TicketEntity ticketEntity = TicketEntity.builder()
                .id(ticketId)
                .build();
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticketEntity));
        when(flyRepository.findById(flyId)).thenReturn(Optional.of(new FlyEntity()));

        TicketResponse response = ticketService.update(request, ticketId);

        assertNotNull(response);
        assertEquals(ticketId, response.getId());
        assertNotNull(response.getFly());
        verify(ticketRepository, times(1)).save(any(TicketEntity.class));
        verify(flyRepository, times(1)).findById(flyId);
    }

    @Test
    @DisplayName("Should throw an IdNotFoundException when the id does not exist in the database")
    void readWhenIdDoesNotExistThenThrowException() {
        UUID id = UUID.randomUUID();

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.read(id));

        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the ticket when the id exists in the database")
    void readWhenIdExists() {
        UUID ticketId = UUID.randomUUID();
        TicketEntity ticketEntity = TicketEntity.builder()
                .id(ticketId)
                .departureDate(LocalDateTime.now())
                .arrivalDate(LocalDateTime.now().plusHours(2))
                .purchaseDate(LocalDate.now())
                .price(BigDecimal.valueOf(100))
                .build();

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticketEntity));

        TicketResponse expectedResponse = TicketResponse.builder()
                .id(ticketId)
                .departureDate(ticketEntity.getDepartureDate())
                .arrivalDate(ticketEntity.getArrivalDate())
                .purchaseDate(ticketEntity.getPurchaseDate())
                .price(ticketEntity.getPrice())
                .build();

        TicketResponse actualResponse = ticketService.read(ticketId);

        assertEquals(expectedResponse, actualResponse);
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    @DisplayName("Should throw an exception when the customer is in the blacklist")
    void createTicketWhenCustomerIsInBlackListThenThrowException() {
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(1L)
                .email("test@example.com")
                .build();

        when(blackListHelper.isInBlackListCustomer(request.getIdClient())).thenReturn(true);

        assertThrows(ForbiddenCustomerException.class, () -> ticketService.create(request));

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getIdClient());
        verifyNoInteractions(flyRepository, customerRepository, ticketRepository, customerHelper, currencyConnectorHelper, emailHelper);
    }

    @Test
    @DisplayName("Should throw an exception when the fly id is not found")
    void createTicketWhenFlyIdNotFoundThenThrowException() {
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(1L)
                .email("test@example.com")
                .build();

        when(flyRepository.findById(request.getIdFly())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> ticketService.create(request));

        // Verify
        verify(flyRepository, times(1)).findById(request.getIdFly());
        verify(customerRepository, never()).findById(any());
        verify(ticketRepository, never()).save(any());
        verify(customerHelper, never()).increase(any(), any());
        verify(emailHelper, never()).sendMail(any(), any(), any());
    }

    @Test
    @DisplayName("Should throw an exception when the customer id is not found")
    void createTicketWhenCustomerIdNotFoundThenThrowException() {
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(1L)
                .email("test@example.com")
                .build();

        when(customerRepository.findById(request.getIdClient())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> ticketService.create(request));

        // Verify
        verify(customerRepository, times(1)).findById(request.getIdClient());
        verify(flyRepository, never()).findById(any());
        verify(ticketRepository, never()).save(any());
        verify(customerHelper, never()).increase(anyString(), any());
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should create a ticket successfully when the customer is not in the blacklist")
    void createTicketWhenCustomerIsNotInBlackList() {
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(1L)
                .email("test@example.com")
                .build();

        FlyEntity flyEntity = FlyEntity.builder()
                .id(1L)
                .originLat(40.7128)
                .originLng(-74.0060)
                .destinyLat(51.5074)
                .destinyLng(-0.1278)
                .originName("New York")
                .destinyName("London")
                .price(BigDecimal.valueOf(1000))
                .airline(Airlines.aero_gold)
                .build();

        CustomerEntity customerEntity = CustomerEntity.builder()
                .dni("123456789")
                .fullName("John Doe")
                .creditCard("1234567890123456")
                .phoneNumber("1234567890")
                .totalFlights(5)
                .totalLodgings(2)
                .totalTours(3)
                .build();

        TicketEntity ticketEntity = TicketEntity.builder()
                .id(UUID.randomUUID())
                .departureDate(LocalDateTime.now())
                .arrivalDate(LocalDateTime.now().plusHours(5))
                .purchaseDate(LocalDate.now())
                .price(BigDecimal.valueOf(1250))
                .fly(flyEntity)
                .customer(customerEntity)
                .build();

        when(blackListHelper.isInBlackListCustomer(request.getIdClient())).thenReturn(false);
        when(flyRepository.findById(request.getIdFly())).thenReturn(Optional.of(flyEntity));
        when(customerRepository.findById(request.getIdClient())).thenReturn(Optional.of(customerEntity));
        when(ticketRepository.save(any(TicketEntity.class))).thenReturn(ticketEntity);

        TicketResponse response = ticketService.create(request);

        assertNotNull(response);
        assertEquals(ticketEntity.getId(), response.getId());
        assertEquals(ticketEntity.getDepartureDate(), response.getDepartureDate());
        assertEquals(ticketEntity.getArrivalDate(), response.getArrivalDate());
        assertEquals(ticketEntity.getPurchaseDate(), response.getPurchaseDate());
        assertEquals(ticketEntity.getPrice(), response.getPrice());

        FlyResponse flyResponse = response.getFly();
        assertNotNull(flyResponse);
        assertEquals(flyEntity.getId(), flyResponse.getId());
        assertEquals(flyEntity.getOriginLat(), flyResponse.getOriginLat());
        assertEquals(flyEntity.getOriginLng(), flyResponse.getOriginLng());
        assertEquals(flyEntity.getDestinyLat(), flyResponse.getDestinyLat());
        assertEquals(flyEntity.getDestinyLng(), flyResponse.getDestinyLng());
        assertEquals(flyEntity.getOriginName(), flyResponse.getOriginName());
        assertEquals(flyEntity.getDestinyName(), flyResponse.getDestinyName());
        assertEquals(flyEntity.getPrice(), flyResponse.getPrice());
        assertEquals(flyEntity.getAirline(), flyResponse.getAirline());

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getIdClient());
        verify(flyRepository, times(1)).findById(request.getIdFly());
        verify(customerRepository, times(1)).findById(request.getIdClient());
        verify(ticketRepository, times(1)).save(any(TicketEntity.class));
    }

    @Test
    @DisplayName("Should send an email when the email is provided in the request")
    void createTicketWhenEmailIsProvidedThenSendEmail() {
        TicketRequest request = TicketRequest.builder()
                .idClient("123456789")
                .idFly(1L)
                .email("test@example.com")
                .build();

        FlyEntity flyEntity = FlyEntity.builder()
                .id(1L)
                .originLat(40.7128)
                .originLng(-74.0060)
                .destinyLat(51.5074)
                .destinyLng(-0.1278)
                .originName("New York")
                .destinyName("London")
                .price(BigDecimal.valueOf(1000))
                .airline(Airlines.aero_gold)
                .build();

        CustomerEntity customerEntity = CustomerEntity.builder()
                .dni("123456789")
                .fullName("John Doe")
                .creditCard("1234567890123456")
                .phoneNumber("1234567890")
                .totalFlights(5)
                .totalLodgings(2)
                .totalTours(3)
                .build();

        TicketEntity ticketEntity = TicketEntity.builder()
                .id(UUID.randomUUID())
                .departureDate(LocalDateTime.now())
                .arrivalDate(LocalDateTime.now().plusHours(5))
                .purchaseDate(LocalDate.now())
                .price(BigDecimal.valueOf(1250))
                .fly(flyEntity)
                .customer(customerEntity)
                .build();

        when(flyRepository.findById(request.getIdFly())).thenReturn(Optional.of(flyEntity));
        when(customerRepository.findById(request.getIdClient())).thenReturn(Optional.of(customerEntity));
        when(ticketRepository.save(any(TicketEntity.class))).thenReturn(ticketEntity);

        TicketResponse response = ticketService.create(request);

        assertNotNull(response);
        assertEquals(ticketEntity.getId(), response.getId());
        assertEquals(ticketEntity.getDepartureDate(), response.getDepartureDate());
        assertEquals(ticketEntity.getArrivalDate(), response.getArrivalDate());
        assertEquals(ticketEntity.getPurchaseDate(), response.getPurchaseDate());
        assertEquals(ticketEntity.getPrice(), response.getPrice());

        FlyResponse flyResponse = response.getFly();
        assertNotNull(flyResponse);
        assertEquals(flyEntity.getId(), flyResponse.getId());
        assertEquals(flyEntity.getOriginLat(), flyResponse.getOriginLat());
        assertEquals(flyEntity.getOriginLng(), flyResponse.getOriginLng());
        assertEquals(flyEntity.getDestinyLat(), flyResponse.getDestinyLat());
        assertEquals(flyEntity.getDestinyLng(), flyResponse.getDestinyLng());
        assertEquals(flyEntity.getOriginName(), flyResponse.getOriginName());
        assertEquals(flyEntity.getDestinyName(), flyResponse.getDestinyName());
        assertEquals(flyEntity.getPrice(), flyResponse.getPrice());
        assertEquals(flyEntity.getAirline(), flyResponse.getAirline());

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getIdClient());
        verify(flyRepository, times(1)).findById(request.getIdFly());
        verify(customerRepository, times(1)).findById(request.getIdClient());
        verify(ticketRepository, times(1)).save(any(TicketEntity.class));
        verify(customerHelper, times(1)).increase(customerEntity.getDni(), TicketService.class);
        verify(emailHelper, times(1)).sendMail(request.getEmail(), customerEntity.getFullName(), Tables.ticket.name());
    }*/
}
