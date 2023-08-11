package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.request.TourFlyRequest;
import com.example.block22SpringAdvanced.application.models.request.TourHotelRequest;
import com.example.block22SpringAdvanced.application.models.request.TourRequest;
import com.example.block22SpringAdvanced.application.models.responses.TourResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.*;
import com.example.block22SpringAdvanced.domain.repositories.jpa.CustomerRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.FlyRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.HotelRepository;
import com.example.block22SpringAdvanced.domain.repositories.jpa.TourRepository;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.ITourService;
import com.example.block22SpringAdvanced.infrastructure.helpers.BlackListHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.CustomerHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.EmailHelper;
import com.example.block22SpringAdvanced.infrastructure.helpers.TourHelper;
import com.example.block22SpringAdvanced.util.exceptions.ForbiddenCustomerException;
import com.example.block22SpringAdvanced.util.exceptions.IdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tour service test")
class TourServiceTest {

    @Mock
    private TourRepository tourRepository;

    @Mock
    private FlyRepository flyRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TourHelper tourHelper;

    @Mock
    private CustomerHelper customerHelper;

    @Mock
    private BlackListHelper blackListHelper;

    @Mock
    private EmailHelper emailHelper;

    private ITourService tourService;

    @BeforeEach
    void setUp() {
        tourService = new TourService(tourRepository, flyRepository, hotelRepository, customerRepository, tourHelper, customerHelper, blackListHelper, emailHelper);
    }


    @Test
    @DisplayName("Should throw an exception when the tourId is not found")
    void deleteReservationWhenTourIdNotFoundThenThrowException() {
        Long tourId = 1L;
        UUID reservationId = UUID.randomUUID();

        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.deleteReservation(tourId, reservationId));

        verify(tourRepository, times(1)).findById(tourId);
        verifyNoMoreInteractions(tourRepository);
        verifyNoInteractions(flyRepository, hotelRepository, customerRepository, tourHelper, customerHelper, blackListHelper, emailHelper);
    }

    /*@Test
    @DisplayName("Should throw an exception when the reservationId is not found in the tour")
    void deleteReservationWhenReservationIdNotFoundInTourThenThrowException() {
        Long tourId = 1L;
        UUID reservationId = UUID.randomUUID();

        TourEntity tourEntity = new TourEntity();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));

        assertThrows(IdNotFoundException.class, () -> tourService.deleteReservation(tourId, reservationId));

        verify(tourRepository, times(1)).findById(tourId);
        verify(tourRepository, never()).save(any(TourEntity.class));
    }*/

    /*@Test
    @DisplayName("Should delete the reservation when the tourId and reservationId are valid")
    void deleteReservationWhenTourIdAndReservationIdAreValid() {
        Long tourId = 1L;
        UUID reservationId = UUID.randomUUID();

        TourEntity tourEntity = new TourEntity();
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(reservationId);
        tourEntity.addReservation(reservationEntity);

        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));

        tourService.deleteReservation(tourId, reservationId);

        verify(tourRepository, times(1)).findById(tourId);
        verify(tourRepository, times(1)).save(tourEntity);
        assertTrue(tourEntity.getReservations().isEmpty());
    }*/

    @Test
    @DisplayName("Should throw an exception when the tour ID is not found")
    void addReservationWhenTourIdNotFoundThenThrowException() {
        Long tourId = 1L;
        Long hotelId = 1L;
        Integer totalDays = 5;

        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.addReservation(tourId, hotelId, totalDays));

        verify(tourRepository, times(1)).findById(tourId);
        verifyNoMoreInteractions(tourRepository, flyRepository, hotelRepository, customerRepository, tourHelper, customerHelper, blackListHelper, emailHelper);
    }

    /*@Test
    @DisplayName("Should return the reservation ID when the reservation is successfully added")
    void addReservationReturnsReservationIdWhenSuccessful() {
        Long tourId = 1L;
        Long hotelId = 1L;
        Integer totalDays = 5;
        UUID reservationId = UUID.randomUUID();

        TourEntity tourEntity = new TourEntity();

        CustomerEntity customerEntity = new CustomerEntity();
    }*/

    @Test
    @DisplayName("Should throw an exception when the hotel ID is not found")
    void addReservationWhenHotelIdNotFoundThenThrowException() {
        Long tourId = 1L;
        Long hotelId = 2L;
        Integer totalDays = 5;

        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.addReservation(tourId, hotelId, totalDays));

        verify(tourRepository, times(1)).findById(tourId);
        verify(hotelRepository, never()).findById(hotelId);
        verify(tourHelper, never()).createReservation(any(), any(), anyInt());
        verify(tourRepository, never()).save(any());
    }

    /*@Test
    @DisplayName("Should add a reservation to the tour when the tour and hotel IDs are valid")
    void addReservationWhenTourAndHotelIdsAreValid() {
        Long tourId = 1L;
        Long hotelId = 1L;
        Integer totalDays = 5;

        Long tourId = 1L;
        Long hotelId = 1L;
        Integer totalDays = 5;

        TourEntity tourEntity = TourEntity.builder().build();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));
    }*/

    @Test
    @DisplayName("Should throw an exception when the tour ID is not found")
    void deleteTicketWhenTourIdNotFoundThenThrowException() {
        Long tourId = 1L;
        UUID ticketId = UUID.randomUUID();

        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.deleteTicket(tourId, ticketId));

        verify(tourRepository, times(1)).findById(tourId);
        verifyNoMoreInteractions(tourRepository);
        verifyNoInteractions(flyRepository);
        verifyNoInteractions(hotelRepository);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(tourHelper);
        verifyNoInteractions(customerHelper);
        verifyNoInteractions(blackListHelper);
        verifyNoInteractions(emailHelper);
    }

   /* @Test
    @DisplayName("Should throw an exception when the ticket ID is not found in the tour")
    void deleteTicketWhenTicketIdNotFoundInTourThenThrowException() {
        UUID ticketId = UUID.randomUUID();
        Long tourId = 1L;
        TourEntity tourEntity = new TourEntity();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> tourService.deleteTicket(tourId, ticketId));

        // Verify
        verify(tourRepository, times(1)).findById(tourId);
        verify(tourRepository, never()).save(any(TourEntity.class));
    }*/

    /*@Test
    @DisplayName("Should delete the ticket from the tour when the tour and ticket IDs are valid")
    void deleteTicketWhenTourAndTicketIdsAreValid() {
        Long tourId = 1L;
        UUID ticketId = UUID.randomUUID();

        // Mock the tour entity
        TourEntity tourEntity = new TourEntity();
        tourEntity.setId(tourId);

        // Mock the ticket entity
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketId);

        // Set the ticket entity in the tour entity
        tourEntity.setTickets(Set.of(ticketEntity));

        // Mock the tour repository to return the tour entity
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));

        // Call the deleteTicket method
        tourService.deleteTicket(tourId, ticketId);

        // Verify that the removeTicket method was called on the tour entity
        verify(tourEntity, times(1)).removeTicket(ticketId);

        // Verify that the tour repository's save method was called
        verify(tourRepository, times(1)).save(tourEntity);
    }*/

    /*@Test
    @DisplayName("Should throw an exception when the flight ID is not found")
    void addTicketWhenFlyIdNotFoundThenThrowException() {
        Long tourId = 1L;
        Long flyId = 2L;

        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());
        when(flyRepository.findById(flyId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.addTicket(tourId, flyId));

        verify(tourRepository, times(1)).findById(tourId);
        verify(flyRepository, times(1)).findById(flyId);
        verifyNoMoreInteractions(tourRepository, flyRepository, hotelRepository, customerRepository, tourHelper, customerHelper, blackListHelper, emailHelper);
    }*/

    /*@Test
    @DisplayName("Should add a ticket to the tour when the tour and flight IDs are valid")
    void addTicketWhenTourIdAndFlyIdAreValid() {
        Long tourId = 1L;
        Long flyId = 1L;
        Long tourId = 1L;
        Long flyId = 1L;
        TourEntity tourEntity = new TourEntity();
        FlyEntity flyEntity = new FlyEntity();
        flyEntity.setId(flyId);
    }*/

    @Test
    @DisplayName("Should throw an exception when the tour ID is not found")
    void addTicketWhenTourIdNotFoundThenThrowException() {
        Long tourId = 1L;
        Long flyId = 1L;

        // Create a CustomerEntity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setDni("123456789");

        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.addTicket(tourId, flyId));

        verify(tourRepository, times(1)).findById(tourId);
        verify(flyRepository, never()).findById(flyId);
        verify(tourRepository, never()).save(any(TourEntity.class));
        verify(tourHelper, never()).createTicket(any(FlyEntity.class), eq(customerEntity));
    }

    @Test
    @DisplayName("Should throw an exception when the id is not found")
    void deleteTourWhenIdIsNotFoundThenThrowException() {
        Long tourId = 1L;
        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.delete(tourId));

        verify(tourRepository, times(1)).findById(tourId);
        verifyNoMoreInteractions(tourRepository);
    }

    @Test
    @DisplayName("Should delete the tour when the id is valid")
    void deleteTourWhenIdIsValid() {
        Long tourId = 1L;
        TourEntity tourEntity = new TourEntity();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));

        tourService.delete(tourId);

        verify(tourRepository, times(1)).findById(tourId);
        verify(tourRepository, times(1)).delete(tourEntity);
    }

    @Test
    @DisplayName("Should throw an exception when the id does not exist")
    void readWhenIdDoesNotExistThenThrowException() {
        Long id = 1L;
        when(tourRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.read(id));

        verify(tourRepository, times(1)).findById(id);
    }

    /*@Test
    @DisplayName("Should return the tour when the id exists")
    void readWhenIdExists() {
        Long tourId = 1L;
        // Mocking the tour entity
        TourEntity tourEntity = TourEntity.builder()
                .id(tourId)
                .build();

        // Mocking the tour response
        TourResponse expectedResponse = TourResponse.builder()
                .id(tourId)
                .build();

        // Mocking the tour repository to return the tour entity
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tourEntity));

        // Calling the read method of the tour service
        TourResponse actualResponse = tourService.read(tourId);

        // Verifying that the tour repository's findById method was called with the correct tourId
        verify(tourRepository, times(1)).findById(tourId);

        // Verifying that the actual response is equal to the expected response
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }*/

    @Test
    @DisplayName("Should throw an exception when customer is in black list")
    void createTourWhenCustomerIsInBlackListThenThrowException() {
        TourRequest request = TourRequest.builder()
                .customerId("123456789")
                .build();

        doThrow(new ForbiddenCustomerException()).when(blackListHelper).isInBlackListCustomer(request.getCustomerId());

        assertThrows(ForbiddenCustomerException.class, () -> tourService.create(request));

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getCustomerId());
        verifyNoMoreInteractions(blackListHelper);
        verifyNoInteractions(tourRepository, flyRepository, hotelRepository, customerRepository, tourHelper, customerHelper, emailHelper);
    }

    /*@Test
    @DisplayName("Should throw an exception when customer id is not found")
    void createTourWhenCustomerIdNotFoundThenThrowException() {
        TourRequest request = TourRequest.builder()
                .customerId("12345678")
                .build();

        when(customerRepository.findById(request.getCustomerId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> tourService.create(request));

        // Verify
        verify(blackListHelper, never()).isInBlackListCustomer(anyString());
        verify(flyRepository, never()).findById(anyLong());
        verify(hotelRepository, never()).findById(anyLong());
        verify(tourRepository, never()).save(any());
        verify(customerHelper, never()).increase(anyString(), any());
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }*/

    /*@Test
    @DisplayName("Should throw an exception when hotel id is not found")
    void createTourWhenHotelIdNotFoundThenThrowException() {
        TourRequest request = TourRequest.builder()
                .customerId("123456789")
                .email("test@example.com")
                .flights(Set.of(TourFlyRequest.builder().id(1L).build()))
                .hotels(Set.of(TourHotelRequest.builder().id(2L).totalDays(3).build()))
                .build();

        when(customerRepository.findById(request.getCustomerId())).thenReturn(Optional.of(new CustomerEntity()));
        when(flyRepository.findById(anyLong())).thenReturn(Optional.of(new FlyEntity()));
        when(hotelRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.create(request));

        verify(tourRepository, never()).save(any());
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }*/

    @Test
    @DisplayName("Should throw an exception when flight id is not found")
    void createTourWhenFlightIdNotFoundThenThrowException() {
        TourRequest request = TourRequest.builder()
                .customerId("123456789")
                .email("test@example.com")
                .flights(Set.of(TourFlyRequest.builder().id(1L).build()))
                .hotels(Set.of(TourHotelRequest.builder().id(1L).totalDays(3).build()))
                .build();

        when(customerRepository.findById(request.getCustomerId())).thenReturn(Optional.of(new CustomerEntity()));
        when(flyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> tourService.create(request));

        verify(tourRepository, never()).save(any());
        verify(emailHelper, never()).sendMail(anyString(), anyString(), anyString());
    }

    /*@Test
    @DisplayName("Should create a tour with valid request and customer is not in black list")
    void createTourWithValidRequestAndCustomerNotInBlackList() {
        TourRequest request = TourRequest.builder()
                .customerId("123456789")
                .email("test@example.com")
                .build();

        CustomerEntity customer = CustomerEntity.builder()
                .dni("123456789")
                .fullName("John Doe")
                .build();

        doReturn(false).when(blackListHelper).isInBlackListCustomer(request.getCustomerId());
        when(customerRepository.findById(request.getCustomerId())).thenReturn(Optional.of(customer));
        when(tourRepository.save(any(TourEntity.class))).thenReturn(new TourEntity());

        TourResponse response = tourService.create(request);

        assertNotNull(response);
        assertEquals(0, response.getReservationIds().size());
        assertEquals(0, response.getTicketsIds().size());
        assertNotNull(response.getId());

        verify(blackListHelper, times(1)).isInBlackListCustomer(request.getCustomerId());
        verify(customerRepository, times(1)).findById(request.getCustomerId());
        verify(tourRepository, times(1)).save(any(TourEntity.class));
    }*/
}
