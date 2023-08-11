package com.example.block22SpringAdvanced.domain.repositories.jpa;

import com.example.block22SpringAdvanced.domain.entities.jpa.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    Set<HotelEntity> findByPriceLessThan(BigDecimal price); // JPQL Snippet -> … where x.price < ?1
    Set<HotelEntity> findByPriceIsBetween(BigDecimal min, BigDecimal max); // JPQL Snippet -> … where x.price between 1? and ?2
    Set<HotelEntity> findByRatingGreaterThan(Integer rating); // JPQL Snippet -> … where x.price > ?1
}
