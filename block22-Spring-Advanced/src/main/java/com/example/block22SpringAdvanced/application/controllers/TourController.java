package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.application.models.request.TourRequest;
import com.example.block22SpringAdvanced.application.models.responses.ErrorsResponse;
import com.example.block22SpringAdvanced.application.models.responses.TourResponse;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.ITourService;
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

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
@Tag(name = "Tour")
public class TourController {

    private final ITourService tourService;

    @ApiResponse(
            responseCode = "400",
            description = "When the request contains an invalid field we return this",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Save a tour in the system based on a list of hotels and a list of flights")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TourResponse> createTour(@Valid @RequestBody TourRequest tourRequest) {
        return ResponseEntity.ok(tourService.create(tourRequest));
    }

    @Operation(summary = "Returns a tour with the id that we pass to it as a parameter")
    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> getTour(@PathVariable Long id) {
        return ResponseEntity.ok(tourService.read(id));
    }

    @Operation(summary = "Add a ticket from tour")
    @PatchMapping(path = "{tourId}/add_ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> createTicket(@PathVariable Long tourId, @PathVariable Long flyId) {
        var response = Collections.singletonMap("ticketId", tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a reservation from tour")
    @PatchMapping(path = "{tourId}/add_reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> createReservation(@PathVariable Long tourId, @PathVariable Long hotelId, @RequestParam Integer totalDays) {
        var response = Collections.singletonMap("ticketId", tourService.addReservation(tourId, hotelId, totalDays));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a ticket from tour")
    @PatchMapping(path = "{tourId}/remove_ticket/{ticketId}") // @Update actualiza todo el objeto | @Patch actualiza solo una propiedad del objeto
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId) {
        tourService.deleteTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a reservation from tour")
    @PatchMapping(path = "{tourId}/remove_reservation/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long tourId, @PathVariable UUID reservationId) {
        tourService.deleteReservation(tourId, reservationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a tour with the id that we pass as a parameter")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
