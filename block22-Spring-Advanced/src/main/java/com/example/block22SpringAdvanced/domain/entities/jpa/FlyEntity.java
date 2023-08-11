package com.example.block22SpringAdvanced.domain.entities.jpa;

import com.example.block22SpringAdvanced.util.enums.Airlines;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity(name = "fly")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String destinyName;

    @Column(length = 20)
    private String originName;

    private Double originLat;

    private Double originLng;

    private Double destinyLat;

    private Double destinyLng;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Airlines airline;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "fly",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<TicketEntity> tickets;
}
