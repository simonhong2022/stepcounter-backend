package com.exorlive.stepcounter.exception;

public record ErrorDTO(
        int status,
        String message
) {
}
