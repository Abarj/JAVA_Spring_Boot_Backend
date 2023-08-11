package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.application.models.request.TicketRequest;
import com.example.block22SpringAdvanced.application.models.responses.ErrorsResponse;
import com.example.block22SpringAdvanced.application.models.responses.TicketResponse;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.ITicketService;
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
@RequestMapping(path = "ticket")
@AllArgsConstructor
@Tag(name = "Ticket")
public class TicketController {

    private final ITicketService ticketService;

    @ApiResponse(
            responseCode = "400",
            description = "When the request contains an invalid field we return this",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Save a ticket in the system based on a flight")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.create(ticketRequest));
    }

    @Operation(summary = "Returns a ticket with the id that we pass to it as a parameter")
    @GetMapping(path = "{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.read(id));
    }

    @Operation(summary = "Return the price of a flight")
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long flyId, @RequestHeader(required = false) Currency currency) {
        if (Objects.isNull(currency)) currency = Currency.getInstance("USD");
        return ResponseEntity.ok(Collections.singletonMap("flyPrice", this.ticketService.findPrice(flyId, currency)));
    }

    @Operation(summary = "Modifies a ticket")
    @PutMapping(path = "{id}")
    public ResponseEntity<TicketResponse> updateTicket(@Valid @PathVariable UUID id, @RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.update(ticketRequest, id));
    }

    @Operation(summary = "Deletes a ticket")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
