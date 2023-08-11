package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.application.models.responses.FlyResponse;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IFlyService;
import com.example.block22SpringAdvanced.util.enums.Airlines;
import com.example.block22SpringAdvanced.util.enums.SortType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FlyController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
public class FlyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFlyService flyService;

    @MockBean
    private FlyController flyController;

    /*@Test
    @DisplayName("Should throw an exception when destiny is null")
    void getByOriginDestinyWhenDestinyIsNullThenThrowException() {
        String origin = "New York";
        String destiny = null;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            mockMvc.perform(get("/fly/origin_destiny")
                            .param("origin", origin)
                            .param("destiny", destiny))
                    .andExpect(status().isBadRequest());
        });

        verifyNoInteractions(flyService);
    }*/

    /*@Test
    @DisplayName("Should throw an exception when origin is null")
    void getByOriginDestinyWhenOriginIsNullThenThrowException() {
        String origin = null;
        String destiny = "New York";

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flyController.getByOriginDestiny(origin, destiny);
        });

        verify(flyService, never()).readByOriginDestiny(anyString(), anyString());
    }*/

    /*@Test
    @DisplayName("Should return no content when there are no flights for the given origin and destiny")
    void getByOriginDestinyWhenNoFlightsForGivenOriginAndDestiny() throws Exception {
        String origin = "New York";
        String destiny = "London";
        Set<FlyResponse> emptyResponse = new HashSet<>();
        when(flyService.readByOriginDestiny(origin, destiny)).thenReturn(emptyResponse);

        mockMvc.perform(get("/fly/origin_destiny")
                        .param("origin", origin)
                        .param("destiny", destiny))
                .andExpect(status().isNoContent());

        verify(flyService, times(1)).readByOriginDestiny(origin, destiny);
    }*/

    /*@Test
    @DisplayName("Should return flights when origin and destiny are valid")
    void getByOriginDestinyWhenOriginAndDestinyAreValid() throws Exception {
        String origin = "New York";
        String destiny = "London";
        Set<FlyResponse> expectedResponse = new HashSet<>();
        expectedResponse.add(FlyResponse.builder()
                .id(1L)
                .originLat(40.7128)
                .originLng(-74.0060)
                .destinyLat(51.5074)
                .destinyLng(-0.1278)
                .originName("New York")
                .destinyName("London")
                .price(BigDecimal.valueOf(500))
                .airline(Airlines.blue_sky)
                .build());

        when(flyService.readByOriginDestiny(origin, destiny)).thenReturn(expectedResponse);

        MvcResult mvcResult = mockMvc.perform(get("/fly/origin_destiny")
                        .param("origin", origin)
                        .param("destiny", destiny))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Set<FlyResponse> actualResponse = objectMapper.readValue(contentAsString, new TypeReference<Set<FlyResponse>>() {});

        assertEquals(expectedResponse, actualResponse);

        verify(flyService, times(1)).readByOriginDestiny(origin, destiny);
    }*/

    /*@Test
    @DisplayName("Should return no content when there are no flights with price between min and max")
    void getBetweenPriceWhenNoMatchingFlightsExist() throws Exception {
        BigDecimal min = new BigDecimal("100");
        BigDecimal max = new BigDecimal("200");
        Set<FlyResponse> emptyResponse = new HashSet<>();
        when(flyService.readBetweenPrices(min, max)).thenReturn(emptyResponse);

        mockMvc.perform(get("/fly/between_price")
                        .param("min", min.toString())
                        .param("max", max.toString()))
                .andExpect(status().isNoContent());

        verify(flyService, times(1)).readBetweenPrices(min, max);
    }*/

    /*@Test
    @DisplayName("Should return flights with price between min and max when there are matching flights")
    void getBetweenPriceWhenMatchingFlightsExist() {
        BigDecimal min = new BigDecimal("100");
        BigDecimal max = new BigDecimal("500");
        Set<FlyResponse> expectedResponse = new HashSet<>();
        expectedResponse.add(FlyResponse.builder()
                .id(1L)
                .originLat(40.7128)
                .originLng(-74.0060)
                .destinyLat(34.0522)
                .destinyLng(-118.2437)
                .originName("New York")
                .destinyName("Los Angeles")
                .price(new BigDecimal("250"))
                .airline(Airlines.blue_sky
                )
                .build());
        when(flyService.readBetweenPrices(min, max)).thenReturn(expectedResponse);

        ResponseEntity<Set<FlyResponse>> response = flyController.getBetweenPrice(min, max);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(flyService, times(1)).readBetweenPrices(min, max);
    }*/

    /*@Test
    @DisplayName("Should return no content when no flights are available with price less than the provided price")
    void getLessPriceWhenNoFlightsAreAvailable() {
        BigDecimal price = new BigDecimal("100.00");
        Set<FlyResponse> emptyResponse = new HashSet<>();

        when(flyService.readLessPrice(price)).thenReturn(emptyResponse);

        ResponseEntity<Set<FlyResponse>> response = flyController.getLessPrice(price);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(emptyResponse, response.getBody());

        verify(flyService, times(1)).readLessPrice(price);
    }*/

    /*@Test
    @DisplayName("Should return flights with price less than the provided price")
    void getLessPriceWhenFlightsAreAvailable() {
        BigDecimal price = new BigDecimal("100.00");
        List<FlyResponse> flyResponses = List.of(
                FlyResponse.builder().id(1L).price(new BigDecimal("50.00")).build(),
                FlyResponse.builder().id(2L).price(new BigDecimal("75.00")).build()
        );
        when(flyService.readLessPrice(price)).thenReturn(new HashSet<>(flyResponses));

        ResponseEntity<Set<FlyResponse>> response = flyController.getLessPrice(price);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new HashSet<>(flyResponses), response.getBody());

        verify(flyService, times(1)).readLessPrice(price);
    }*/

    /*@Test
    @DisplayName("Should return no content when the list of flights is empty")
    void getAllWhenListIsEmpty() throws Exception {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.NONE;
        List<FlyResponse> emptyList = List.of();
        Page<FlyResponse> emptyPage = new PageImpl<>(emptyList);

        when(flyService.readAll(anyInt(), anyInt(), any(SortType.class))).thenReturn(emptyPage);

        MvcResult mvcResult = mockMvc.perform(get("/fly")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sortType", sortType.name()))
                .andExpect(status().isOk())
                .andReturn();

        ResponseEntity<Page<FlyResponse>> response = (ResponseEntity<Page<FlyResponse>>) mvcResult.getAsyncResult();

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(CollectionUtils.isEmpty(response.getBody().getContent()));

        verify(flyService, times(1)).readAll(page, size, sortType);
    }*/

    /*@Test
    @DisplayName("Should return all flights sorted by the given sort type")
    void getAllWithSortType() {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.LOWER;
        List<FlyResponse> flyResponses = List.of(
                FlyResponse.builder().id(1L).originLat(12.345).originLng(67.890).destinyLat(98.765).destinyLng(43.210)
                        .originName("Origin1").destinyName("Destiny1").price(BigDecimal.valueOf(100.00)).airline(Airlines.aero_gold).build(),
                FlyResponse.builder().id(2L).originLat(98.765).originLng(43.210).destinyLat(12.345).destinyLng(67.890)
                        .originName("Origin2").destinyName("Destiny2").price(BigDecimal.valueOf(200.00)).airline(Airlines.blue_sky).build()
        );
        Page<FlyResponse> flyResponsePage = new PageImpl<>(flyResponses);

        when(flyService.readAll(anyInt(), anyInt(), any(SortType.class))).thenReturn(flyResponsePage);

        ResponseEntity<Page<FlyResponse>> response = flyController.getAll(page, size, sortType);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flyResponsePage, response.getBody());
        verify(flyService, times(1)).readAll(page, size, sortType);
    }*/

    /*@Test
    @DisplayName("Should return all flights with default sort type when no sort type is provided")
    void getAllWithDefaultSortType() throws Exception {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.NONE;
        List<FlyResponse> flyResponses = List.of(
                FlyResponse.builder().id(1L).originLat(12.345).originLng(67.890).destinyLat(98.765).destinyLng(43.210)
                        .originName("Origin1").destinyName("Destiny1").price(BigDecimal.valueOf(100.00)).airline(Airlines.aero_gold).build(),
                FlyResponse.builder().id(2L).originLat(98.765).originLng(43.210).destinyLat(12.345).destinyLng(67.890)
                        .originName("Origin2").destinyName("Destiny2").price(BigDecimal.valueOf(200.00)).airline(Airlines.blue_sky).build()
        );
        Page<FlyResponse> flyResponsePage = new PageImpl<>(flyResponses);

        when(flyService.readAll(anyInt(), anyInt(), any(SortType.class))).thenReturn(flyResponsePage);

        mockMvc.perform(get("/fly")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].originLat", is(12.345)))
                .andExpect(jsonPath("$.content[0].originLng", is(67.890)))
                .andExpect(jsonPath("$.content[0].destinyLat", is(98.765)))
                .andExpect(jsonPath("$.content[0].destinyLng", is(43.210)))
                .andExpect(jsonPath("$.content[0].originName", is("Origin1")))
                .andExpect(jsonPath("$.content[0].destinyName", is("Destiny1")))
                .andExpect(jsonPath("$.content[0].price", is(100.00)))
                .andExpect(jsonPath("$.content[0].airline", is("aero_gold")))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[1].originLat", is(98.765)))
                .andExpect(jsonPath("$.content[1].originLng", is(43.210)))
                .andExpect(jsonPath("$.content[1].destinyLat", is(12.345)))
                .andExpect(jsonPath("$.content[1].destinyLng", is(67.890)))
                .andExpect(jsonPath("$.content[1].originName", is("Origin2")))
                .andExpect(jsonPath("$.content[1].destinyName", is("Destiny2")))
                .andExpect(jsonPath("$.content[1].price", is(200.00)))
                .andExpect(jsonPath("$.content[1].airline", is("blue_sky")));

        verify(flyService, times(1)).readAll(page, size, sortType);
    }*/

    /*@Test
    @DisplayName("Should return all flights when the list is not empty")
    void getAllWhenListIsNotEmpty() throws Exception {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.NONE;
        List<FlyResponse> flyResponses = List.of(
                FlyResponse.builder().id(1L).originLat(12.345).originLng(67.890).destinyLat(98.765).destinyLng(43.210)
                        .originName("Origin").destinyName("Destiny").price(BigDecimal.valueOf(100.00)).airline(Airlines.aero_gold).build(),
                FlyResponse.builder().id(2L).originLat(98.765).originLng(43.210).destinyLat(12.345).destinyLng(67.890)
                        .originName("Destiny").destinyName("Origin").price(BigDecimal.valueOf(200.00)).airline(Airlines.blue_sky).build()
        );
        Page<FlyResponse> flyResponsePage = new PageImpl<>(flyResponses);

        when(flyService.readAll(anyInt(), anyInt(), any(SortType.class))).thenReturn(flyResponsePage);

        mockMvc.perform(get("/fly")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sortType", sortType.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].originLat", is(12.345)))
                .andExpect(jsonPath("$.content[0].originLng", is(67.890)))
                .andExpect(jsonPath("$.content[0].destinyLat", is(98.765)))
                .andExpect(jsonPath("$.content[0].destinyLng", is(43.210)))
                .andExpect(jsonPath("$.content[0].originName", is("Origin")))
                .andExpect(jsonPath("$.content[0].destinyName", is("Destiny")))
                .andExpect(jsonPath("$.content[0].price", is(100.00)))
                .andExpect(jsonPath("$.content[0].airline", is("aero_gold")))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[1].originLat", is(98.765)))
                .andExpect(jsonPath("$.content[1].originLng", is(43.210)))
                .andExpect(jsonPath("$.content[1].destinyLat", is(12.345)))
                .andExpect(jsonPath("$.content[1].destinyLng", is(67.890)))
                .andExpect(jsonPath("$.content[1].originName", is("Destiny")))
                .andExpect(jsonPath("$.content[1].destinyName", is("Origin")))
                .andExpect(jsonPath("$.content[1].price", is(200.00)))
                .andExpect(jsonPath("$.content[1].airline", is("blue_sky")));

        verify(flyService, times(1)).readAll(page, size, sortType);
    }*/
}
