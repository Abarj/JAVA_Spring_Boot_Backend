package com.example.block22SpringAdvanced.application.models.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest implements Serializable {

    @Size(min = 18, max = 20, message = "The size must be between 10 and 20 characters")
    @NotBlank(message = "Id client is mandatory")
    private String idClient;

    @Positive
    @NotNull(message = "Id hotel is mandatory")
    private Long idHotel;

    @Min(value = 1, message = "It is necessary at least one day to make the reservation")
    @Max(value = 30, message = "Max 30 days to make reservation")
    @NotNull(message = "Total days is mandatory")
    private Integer totalDays;

    // @Pattern(regexp = "^(.+)@(.+)$") -> Expresión regular para validar email
    @Email(message = "Invalid email format")
    private String email;
}
