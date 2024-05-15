package org.example.infrastructure.dto.response;

import lombok.Builder;
import org.example.infrastructure.dto.response.error.ErrorResponse;

@Builder
public record BaseResponse<T>(Boolean success, T result, String message, ErrorResponse error) {
}
