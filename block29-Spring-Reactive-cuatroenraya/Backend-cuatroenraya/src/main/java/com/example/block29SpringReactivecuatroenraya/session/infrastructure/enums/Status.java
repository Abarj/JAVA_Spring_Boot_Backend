package com.example.block29SpringReactivecuatroenraya.session.infrastructure.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    OPEN,
    CLOSE,
    TIE,
    WINNER_ONE,
    WINNER_TWO;

    @JsonValue
    public String toJson() {
        return this.name();
    }

    @JsonCreator
    public static Status fromJson(String value) {
        if (value == null || value.isEmpty()) {
            return null; // or a default value if needed
        }
        return Status.valueOf(value);
    }
}