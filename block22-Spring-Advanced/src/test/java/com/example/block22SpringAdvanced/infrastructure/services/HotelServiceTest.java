
package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.application.models.responses.HotelResponse;
import com.example.block22SpringAdvanced.domain.entities.jpa.HotelEntity;
import com.example.block22SpringAdvanced.domain.repositories.jpa.HotelRepository;
import com.example.block22SpringAdvanced.util.enums.SortType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("HotelService")
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;


    /*@Test
    @DisplayName("Should return hotels with rating greater than the provided rating")
    void readByRatingWhenRatingIsProvided() {
        Integer rating = 4;
        HotelEntity hotelEntity1 = HotelEntity.builder().id(1L).name("Hotel A").address("Address A").rating(5).price(BigDecimal.valueOf(100)).build();
        HotelEntity hotelEntity2 = HotelEntity.builder().id(2L).name("Hotel B").address("Address B").rating(4).price(BigDecimal.valueOf(200)).build();
        HotelEntity hotelEntity3 = HotelEntity.builder().id(3L).name("Hotel C").address("Address C").rating(3).price(BigDecimal.valueOf(300)).build();

        Set<HotelEntity> hotelEntities = new HashSet<>(List.of(hotelEntity1, hotelEntity2, hotelEntity3));
        when(hotelRepository.findByRatingGreaterThan(rating)).thenReturn(hotelEntities);

        Set<HotelResponse> result = hotelService.readByRating(rating);

        assertEquals(2, result.size());
        verify(hotelRepository, times(1)).findByRatingGreaterThan(rating);
    }*/

    @Test
    @DisplayName("Should throw an exception when the minimum price is greater than the maximum price")
    void readBetweenPricesWhenMinPriceIsGreaterThanMaxPrice() {
        BigDecimal minPrice = new BigDecimal("100");
        BigDecimal maxPrice = new BigDecimal("50");

        assertThrows(IllegalArgumentException.class, () -> {
            hotelService.readBetweenPrices(minPrice, maxPrice);
        });
    }

    @Test
    @DisplayName("Should return hotels with price less than the provided price")
    void readLessPriceWhenPriceIsProvided() {
        BigDecimal price = new BigDecimal("100");

        List<HotelEntity> hotelEntities = List.of(
                HotelEntity.builder().id(1L).name("Hotel A").address("Address A").rating(4).price(new BigDecimal("80")).build(),
                HotelEntity.builder().id(2L).name("Hotel B").address("Address B").rating(3).price(new BigDecimal("90")).build(),
                HotelEntity.builder().id(3L).name("Hotel C").address("Address C").rating(5).price(new BigDecimal("70")).build()
        );

        Set<HotelEntity> hotelEntitiesSet = new HashSet<>(hotelEntities);
        when(hotelRepository.findByPriceLessThan(price)).thenReturn(hotelEntitiesSet);

        Set<HotelResponse> expectedHotels = Set.of(
                HotelResponse.builder().id(1L).name("Hotel A").address("Address A").rating(4).price(new BigDecimal("80")).build(),
                HotelResponse.builder().id(2L).name("Hotel B").address("Address B").rating(3).price(new BigDecimal("90")).build(),
                HotelResponse.builder().id(3L).name("Hotel C").address("Address C").rating(5).price(new BigDecimal("70")).build()
        );

        Set<HotelResponse> actualHotels = hotelService.readLessPrice(price);

        assertEquals(expectedHotels, actualHotels);
        verify(hotelRepository, times(1)).findByPriceLessThan(price);
    }

    @Test
    @DisplayName("Should return a page of hotels sorted in ascending order when sort type is LOWER")
    void readAllWhenSortTypeIsLower() {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.LOWER;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("price").ascending()); // Change "FIELD_BY_SORT" to "price"
        List<HotelEntity> hotelEntities = List.of(
                new HotelEntity(1L, "Hotel A", "Address A", 4, BigDecimal.valueOf(100), null),
                new HotelEntity(2L, "Hotel B", "Address B", 3, BigDecimal.valueOf(200), null),
                new HotelEntity(3L, "Hotel C", "Address C", 5, BigDecimal.valueOf(150), null)
        );
        PageImpl<HotelEntity> hotelPage = new PageImpl<>(hotelEntities, pageRequest, hotelEntities.size());

        when(hotelRepository.findAll(pageRequest)).thenReturn(hotelPage);

        Page<HotelResponse> result = hotelService.readAll(page, size, sortType);

        assertEquals(hotelEntities.size(), result.getTotalElements());
        assertEquals(hotelEntities.size(), result.getContent().size());
        assertEquals(hotelEntities.get(0).getId(), result.getContent().get(0).getId());
        assertEquals(hotelEntities.get(0).getName(), result.getContent().get(0).getName());
        assertEquals(hotelEntities.get(0).getAddress(), result.getContent().get(0).getAddress());
        assertEquals(hotelEntities.get(0).getRating(), result.getContent().get(0).getRating());
        assertEquals(hotelEntities.get(0).getPrice(), result.getContent().get(0).getPrice());

        verify(hotelRepository, times(1)).findAll(pageRequest);
    }

    /*@Test
    @DisplayName("Should return a page of hotels sorted in descending order when sort type is UPPER")
    void readAllWhenSortTypeIsUpper() {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.UPPER;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("price").descending()); // Change "price: ASC" to "price: DESC"
        List<HotelEntity> hotelEntities = List.of(
                new HotelEntity(1L, "Hotel A", "Address A", 4, BigDecimal.valueOf(100), null),
                new HotelEntity(2L, "Hotel B", "Address B", 3, BigDecimal.valueOf(200), null),
                new HotelEntity(3L, "Hotel C", "Address C", 5, BigDecimal.valueOf(150), null)
        );
        PageImpl<HotelEntity> hotelPage = new PageImpl<>(hotelEntities, pageRequest, hotelEntities.size());

        when(hotelRepository.findAll(pageRequest)).thenReturn(hotelPage);

        Page<HotelResponse> result = hotelService.readAll(page, size, sortType);

        assertEquals(3, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals("Hotel C", result.getContent().get(0).getName());
        assertEquals("Address C", result.getContent().get(0).getAddress());
        assertEquals(5, result.getContent().get(0).getRating());
        assertEquals(BigDecimal.valueOf(150), result.getContent().get(0).getPrice());

        assertEquals(2L, result.getContent().get(1).getId());
        assertEquals("Hotel A", result.getContent().get(1).getName());
        assertEquals("Address A", result.getContent().get(1).getAddress());
        assertEquals(4, result.getContent().get(1).getRating());
        assertEquals(BigDecimal.valueOf(100), result.getContent().get(1).getPrice());

        assertEquals(3L, result.getContent().get(2).getId());
        assertEquals("Hotel B", result.getContent().get(2).getName());
        assertEquals("Address B", result.getContent().get(2).getAddress());
        assertEquals(3, result.getContent().get(2).getRating());
        assertEquals(BigDecimal.valueOf(200), result.getContent().get(2).getPrice());

        verify(hotelRepository, times(1)).findAll(pageRequest);
    }*/

    @Test
    @DisplayName("Should return a page of hotels with no sorting when sort type is NONE")
    void readAllWhenSortTypeIsNone() {
        int page = 0;
        int size = 10;
        SortType sortType = SortType.NONE;
        PageRequest pageRequest = PageRequest.of(page, size);
        List<HotelEntity> hotelEntities = List.of(
                new HotelEntity(1L, "Hotel A", "Address A", 4, BigDecimal.valueOf(100), null),
                new HotelEntity(2L, "Hotel B", "Address B", 3, BigDecimal.valueOf(200), null),
                new HotelEntity(3L, "Hotel C", "Address C", 5, BigDecimal.valueOf(150), null)
        );
        Page<HotelEntity> hotelEntityPage = new PageImpl<>(hotelEntities, pageRequest, hotelEntities.size());

        when(hotelRepository.findAll(pageRequest)).thenReturn(hotelEntityPage);

        Page<HotelResponse> result = hotelService.readAll(page, size, sortType);

        assertEquals(hotelEntities.size(), result.getContent().size());
        assertEquals(hotelEntities.get(0).getId(), result.getContent().get(0).getId());
        assertEquals(hotelEntities.get(0).getName(), result.getContent().get(0).getName());
        assertEquals(hotelEntities.get(0).getAddress(), result.getContent().get(0).getAddress());
        assertEquals(hotelEntities.get(0).getRating(), result.getContent().get(0).getRating());
        assertEquals(hotelEntities.get(0).getPrice(), result.getContent().get(0).getPrice());

        verify(hotelRepository, times(1)).findAll(pageRequest);
    }
}
