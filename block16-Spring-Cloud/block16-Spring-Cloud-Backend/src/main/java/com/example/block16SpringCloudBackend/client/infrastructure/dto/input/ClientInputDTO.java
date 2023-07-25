package com.example.block16SpringCloudBackend.client.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
public class ClientInputDTO{

    @NotNull
    private String name;

    private String surname;

    private int age;

    @NotNull
    private String email;

    @NotNull
    private int phoneNumber;
}
