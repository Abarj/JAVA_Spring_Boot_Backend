package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.responses.FlyResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.FlyEntity;
import com.example.block22SpringAdvanced.domain.repositories.jpa.FlyRepository;
import com.example.block22SpringAdvanced.util.enums.Airlines;
import com.example.block22SpringAdvanced.util.enums.SortType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("FlyService")
class FlyServiceTest {

    @MockBean
    private FlyRepository flyRepository;

    @Autowired
    private FlyService flyService;

    /*@Test
    @DisplayName("Should throw an exception when the destiny is null")
    void readByOriginDestinyWhenDestinyIsNullThenThrowException() {
        String origin = "New York";
        String destiny = null;

        assertThrows(IllegalArgumentException.class, () -> {
            flyService.readByOriginDestiny(origin, destiny);
        });
    }*/

    @Test
    @DisplayName("Should throw an exception when the origin is null")
    void readByOriginDestinyWhenOriginIsNullThenThrowException() {
        String origin = null;
        String destiny = "New York";

        assertThrows(IllegalArgumentException.class, () -> {
            flyService.readByOriginDestiny(origin, destiny);
        });
    }

    /*@Test
    @DisplayName("Should return a set of flights when the origin and destiny are valid")
    void readByOriginDestinyWhenOriginAndDestinyAreValid() {
        String origin = "New York";
        String destiny = "Los Angeles";    // Create a FlyEntity object with the given origin and destiny
        FlyEntity flyEntity = FlyEntity.builder()
                .originName(origin)
                .destinyName(destiny)
                .build();

        // Create a FlyResponse object with the same origin and destiny
        FlyResponse flyResponse = FlyResponse.builder()
                .originName(origin)
                .destinyName(destiny)
                .build();

        // Create a list of FlyEntity objects containing the flyEntity object
        List<FlyEntity> flyEntities = List.of(flyEntity);

        // Create a Page object with the flyEntities list
        Page<FlyEntity> flyEntityPage = new PageImpl<>(flyEntities);

        // Mock the flyRepository's selectOriginDestiny method to return the flyEntityPage
        when(flyRepository.selectOriginDestiny(origin, destiny)).thenReturn(flyEntityPage.toSet());

        Set<FlyResponse> result = flyService.readByOriginDestiny(origin, destiny);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(flyResponse));

        // Verify that the flyRepository's selectOriginDestiny method was called with the correct parameters
        verify(flyRepository, times(1)).selectOriginDestiny(origin, destiny);
    }*/

    /*@Test
    @DisplayName("Should return an empty set when there are no flights with price less than the provided price")
    void readLessPriceWhenNoFlightsAreCheaper() {
        BigDecimal price = new BigDecimal("1000.00");

        when(flyRepository.selectLessPrice(price)).thenReturn(List.of());

        Set<FlyResponse> result = flyService.readLessPrice(price);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(flyRepository, times(1)).selectLessPrice(price);
    }*/

    /*@Test
    @DisplayName("Should return a set of flights with price less than the provided price")
    void readLessPriceWhenPriceIsProvided() {
        BigDecimal price = new BigDecimal("1000.00");
        FlyEntity flyEntity1 = FlyEntity.builder()
                .id(1L)
                .originLat(40.7128)
                .originLng(-74.0060)
                .destinyLat(34.0522)
                .destinyLng(-118.2437)
                .originName("New York")
                .destinyName("Los Angeles")
                .price(new BigDecimal("500.00"))
                .airline(Airlines.blue_sky)
                .build();

        FlyEntity flyEntity2 = FlyEntity.builder()
                .id(2L)
                .originLat(51.5074)
                .originLng(-0.1278)
                .destinyLat(48.8566)
                .destinyLng(2.3522)
                .originName("London")
                .destinyName("Paris")
                .price(new BigDecimal("700.00"))
                .airline(Airlines.aero_gold)
                .build();

        Set<FlyEntity> flyEntities = new HashSet<>(List.of(flyEntity1, flyEntity2));
        when(flyRepository.selectLessPrice(price)).thenReturn(flyEntities);

        Set<FlyResponse> expectedResponse = Set.of(
                FlyResponse.builder()
                        .id(1L)
                        .originLat(40.7128)
                        .originLng(-74.0060)
                        .destinyLat(34.0522)
                        .destinyLng(-118.2437)
                        .originName("New York")
                        .destinyName("Los Angeles")
                        .price(new BigDecimal("500.00"))
                        .airline(Airlines.blue_sky)
                        .build(),
                FlyResponse.builder()
                        .id(2L)
                        .originLat(51.5074)
                        .originLng(-0.1278)
                        .destinyLat(48.8566)
                        .destinyLng(2.3522)
                        .originName("London")
                        .destinyName("Paris")
                        .price(new BigDecimal("700.00"))
                        .airline(Airlines.aero_gold)
                        .build()
        );

        Set<FlyResponse> actualResponse = flyService.readLessPrice(price);

        assertEquals(expectedResponse, actualResponse);
        verify(flyRepository, times(1)).selectLessPrice(price);
    }*/

    /*@Test
    @DisplayName("Should throw an exception when the minimum price is greater than the maximum price")
    void readBetweenPricesWhenMinPriceIsGreaterThanMaxPrice() {
        BigDecimal minPrice = new BigDecimal("100");
        BigDecimal maxPrice = new BigDecimal("50");

        assertThrows(IllegalArgumentException.class, () -> {
            flyService.readBetweenPrices(minPrice, maxPrice);
        });
    }

    @Test
    @DisplayName("Should return flights with prices between the given range")
    void readBetweenPricesWithValidRange() {
        BigDecimal minPrice = new BigDecimal("100");
        BigDecimal maxPrice = new BigDecimal("500");    // Create a list of FlyEntity objects to be returned by the mock repository
        List<FlyEntity> flyEntities = List.of(
                FlyEntity.builder()
                        .id(1L)
                        .originLat(40.7128)
                        .originLng(-74.0060)
                        .destinyLat(34.0522)
                        .destinyLng(-118.2437)
                        .originName("New York")
                        .destinyName("Los Angeles")
                        .price(new BigDecimal("250"))
                        .airline(Airlines.blue_sky)
                        .build(),
                FlyEntity.builder()
                        .id(2L)
                        .originLat(51.5074)
                        .originLng(-0.1278)
                        .destinyLat(48.8566)
                        .destinyLng(2.3522)
                        .originName("London")
                        .destinyName("Paris")
                        .price(new BigDecimal("300"))
                        .airline(Airlines.aero_gold)
                        .build(),
                FlyEntity.builder()
                        .id(3L)
                        .originLat(37.7749)
                        .originLng(-122.4194)
                        .destinyLat(32.7157)
                        .destinyLng(-117.1611)
                        .originName("San Francisco")
                        .destinyName("San Diego")
                        .price(new BigDecimal("400"))
                        .airline(Airlines.blue_sky)
                        .build()
        );

        // Create a Page object containing the FlyEntity list
        Page<FlyEntity> flyEntityPage = new PageImpl<>(flyEntities);

        // Mock the behavior of the flyRepository
        when(flyRepository.selectBetweenPrice(minPrice, maxPrice)).thenReturn(flyEntityPage.toSet());

        Set<FlyResponse> flyResponses = flyService.readBetweenPrices(minPrice, maxPrice);

        assertNotNull(flyResponses);
        assertEquals(3, flyResponses.size());
        assertTrue(flyResponses.stream().anyMatch(f -> f.getId() == 1L));
        assertTrue(flyResponses.stream().anyMatch(f -> f.getId() == 2L));
        assertTrue(flyResponses.stream().anyMatch(f -> f.getId() == 3L));
    }*/

    // ---------------------- readAll ---------------------- //
    @Test
    @DisplayName("Should return a page of flies without any sorting")
    void readAllWithNoSorting() {    // Create a list of FlyEntity objects for testing
        List<FlyEntity> flyEntities = List.of(
                FlyEntity.builder()
                        .id(1L)
                        .originLat(40.7128)
                        .originLng(-74.0060)
                        .destinyLat(34.0522)
                        .destinyLng(-118.2437)
                        .originName("New York")
                        .destinyName("Los Angeles")
                        .price(BigDecimal.valueOf(500))
                        .airline(Airlines.aero_gold)
                        .build(),
                FlyEntity.builder()
                        .id(2L)
                        .originLat(51.5074)
                        .originLng(-0.1278)
                        .destinyLat(48.8566)
                        .destinyLng(2.3522)
                        .originName("London")
                        .destinyName("Paris")
                        .price(BigDecimal.valueOf(400))
                        .airline(Airlines.blue_sky)
                        .build()
        );

        // Create a PageRequest object for the readAll method
        PageRequest pageRequest = PageRequest.of(0, 3);

        // Mock the flyRepository to return a PageImpl of FlyEntity objects
        when(flyRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(flyEntities, pageRequest, flyEntities.size()));

        // Call the readAll method and assert the result
        Page<FlyResponse> result = flyService.readAll(0, 3, SortType.NONE);
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());

        // Verify that the flyRepository's findAll method was called with the correct arguments
        verify(flyRepository, times(1)).findAll(pageRequest);
    }

    @Test
    @DisplayName("Should return a page of flies sorted in ascending order")
    void readAllWithAscendingSorting() {    // Mock data
        List<FlyEntity> flyEntities = List.of(
                FlyEntity.builder()
                        .id(1L)
                        .originLat(40.7128)
                        .originLng(-74.0060)
                        .destinyLat(34.0522)
                        .destinyLng(-118.2437)
                        .originName("New York")
                        .destinyName("Los Angeles")
                        .price(BigDecimal.valueOf(500))
                        .airline(Airlines.aero_gold)
                        .build(),
                FlyEntity.builder()
                        .id(2L)
                        .originLat(34.0522)
                        .originLng(-118.2437)
                        .destinyLat(40.7128)
                        .destinyLng(-74.0060)
                        .originName("Los Angeles")
                        .destinyName("New York")
                        .price(BigDecimal.valueOf(700))
                        .airline(Airlines.blue_sky)
                        .build()
        );

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("price").ascending());
        Page<FlyEntity> flyEntityPage = new PageImpl<>(flyEntities, pageRequest, flyEntities.size());

        // Mock repository method
        when(flyRepository.findAll(pageRequest)).thenReturn(flyEntityPage);

        // Call the method under test
        Page<FlyResponse> result = flyService.readAll(0, 10, SortType.LOWER);

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals("New York", result.getContent().get(0).getOriginName());
        assertEquals("Los Angeles", result.getContent().get(0).getDestinyName());
        assertEquals(BigDecimal.valueOf(500), result.getContent().get(0).getPrice());
        assertEquals(Airlines.aero_gold, result.getContent().get(0).getAirline());

        assertEquals(2L, result.getContent().get(1).getId());
        assertEquals("Los Angeles", result.getContent().get(1).getOriginName());
        assertEquals("New York", result.getContent().get(1).getDestinyName());
        assertEquals(BigDecimal.valueOf(700), result.getContent().get(1).getPrice());
        assertEquals(Airlines.blue_sky, result.getContent().get(1).getAirline());

        // Verify repository method was called
        verify(flyRepository, times(1)).findAll(pageRequest);
    }

    @Test
    @DisplayName("Should return a page of flies sorted in descending order")
    void readAllWithDescendingSorting() {    // Mocking the input parameters
        Integer page = 0;
        Integer size = 10;
        SortType sortType = SortType.UPPER;

        // Mocking the FlyEntity objects
        FlyEntity fly1 = FlyEntity.builder()
                .id(1L)
                .originLat(40.7128)
                .originLng(-74.0060)
                .destinyLat(34.0522)
                .destinyLng(-118.2437)
                .originName("New York")
                .destinyName("Los Angeles")
                .price(BigDecimal.valueOf(500))
                .airline(Airlines.aero_gold)
                .build();

        FlyEntity fly2 = FlyEntity.builder()
                .id(2L)
                .originLat(34.0522)
                .originLng(-118.2437)
                .destinyLat(40.7128)
                .destinyLng(-74.0060)
                .originName("Los Angeles")
                .destinyName("New York")
                .price(BigDecimal.valueOf(700))
                .airline(Airlines.blue_sky)
                .build();

        List<FlyEntity> flyEntities = List.of(fly1, fly2);
        Page<FlyEntity> flyEntityPage = new PageImpl<>(flyEntities);

        // Mocking the flyRepository behavior
        when(flyRepository.findAll(any(PageRequest.class))).thenReturn(flyEntityPage);

        // Calling the method under test
        Page<FlyResponse> result = flyService.readAll(page, size, sortType);

        // Verifying the behavior
        verify(flyRepository, times(1)).findAll(any(PageRequest.class));

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(fly1.getId(), result.getContent().get(0).getId());
        assertEquals(fly2.getId(), result.getContent().get(1).getId());
        assertEquals(fly1.getOriginLat(), result.getContent().get(0).getOriginLat());
        assertEquals(fly2.getOriginLat(), result.getContent().get(1).getOriginLat());
        assertEquals(fly1.getOriginLng(), result.getContent().get(0).getOriginLng());
        assertEquals(fly2.getOriginLng(), result.getContent().get(1).getOriginLng());
        assertEquals(fly1.getDestinyLat(), result.getContent().get(0).getDestinyLat());
        assertEquals(fly2.getDestinyLat(), result.getContent().get(1).getDestinyLat());
        assertEquals(fly1.getDestinyLng(), result.getContent().get(0).getDestinyLng());
        assertEquals(fly2.getDestinyLng(), result.getContent().get(1).getDestinyLng());
        assertEquals(fly1.getOriginName(), result.getContent().get(0).getOriginName());
        assertEquals(fly2.getOriginName(), result.getContent().get(1).getOriginName());
        assertEquals(fly1.getDestinyName(), result.getContent().get(0).getDestinyName());
        assertEquals(fly2.getDestinyName(), result.getContent().get(1).getDestinyName());
        assertEquals(fly1.getPrice(), result.getContent().get(0).getPrice());
        assertEquals(fly2.getPrice(), result.getContent().get(1).getPrice());
        assertEquals(fly1.getAirline(), result.getContent().get(0).getAirline());
        assertEquals(fly2.getAirline(), result.getContent().get(1).getAirline());
    }
}