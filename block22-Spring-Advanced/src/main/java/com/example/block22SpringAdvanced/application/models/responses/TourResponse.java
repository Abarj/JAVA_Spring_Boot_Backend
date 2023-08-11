package com.example.block22SpringAdvanced.application.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourResponse implements Serializable {

    private Long id;

    private Set<UUID> ticketsIds;

    private Set<UUID> reservationIds;
}
