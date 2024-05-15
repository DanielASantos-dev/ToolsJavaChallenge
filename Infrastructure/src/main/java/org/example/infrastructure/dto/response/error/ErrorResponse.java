package org.example.infrastructure.dto.response.error;

import java.util.List;

public record ErrorResponse(String message, String code, List<ValidationError> validationErrors) {
}
