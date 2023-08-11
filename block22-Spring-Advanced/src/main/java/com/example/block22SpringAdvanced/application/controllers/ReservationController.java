package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.application.models.request.ReservationRequest;
import com.example.block22SpringAdvanced.application.models.responses.ErrorsResponse;
import com.example.block22SpringAdvanced.application.models.responses.ReservationResponse;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
@Tag(name = "Reservation")
public class ReservationController {

    private final IReservationService reservationService;

    @ApiResponse(
            responseCode = "400",
            description = "When the request contains an invalid field we return this",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Save a reservation in the system based on an hotel (id), total days and an email")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(reservationService.create(reservationRequest));
    }

    @Operation(summary = "Returns a reservation with the id that we pass to it as a parameter")
    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable UUID id) {
        return ResponseEntity.ok(reservationService.read(id));
    }

    @Operation(summary = "Return the price of a reservation")
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(@RequestParam Long hotelId, @RequestHeader(required = false)Currency currency) {
        if (Objects.isNull(currency)) currency = Currency.getInstance("USD");
        return ResponseEntity.ok(Collections.singletonMap("ticketPrice", reservationService.findPrice(hotelId, currency)));
    }

    @Operation(summary = "Modifies a reservation")
    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@Valid @PathVariable UUID id, @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(reservationService.update(reservationRequest, id));
    }

    @Operation(summary = "Deletes a reservation")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
