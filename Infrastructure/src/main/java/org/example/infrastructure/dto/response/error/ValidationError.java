package org.example.infrastructure.dto.response.error;

public record ValidationError(String field, String message) {
}
