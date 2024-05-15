package org.example.infrastructure.exception;

import org.example.core.exception.BadRequestException;
import org.example.core.exception.InternalServerErrorException;
import org.example.core.exception.NotFoundException;
import org.example.core.exception.enums.ErrorCodeEnum;
import org.example.infrastructure.dto.response.BaseResponse;
import org.example.infrastructure.dto.response.error.ErrorResponse;
import org.example.infrastructure.dto.response.error.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleNotFoundException(NotFoundException ex){
        var error = new ErrorResponse(ex.getMessage(), ex.getCode(), null);
        return ResponseEntity.status(404).body(BaseResponse.<ErrorResponse>builder().success(false).error(error).build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBadRequestException(BadRequestException ex){
        var error = new ErrorResponse(ex.getMessage(), ex.getCode(), null);
        return ResponseEntity.status(400).body(BaseResponse.<ErrorResponse>builder().success(false).error(error).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var error = new ErrorResponse(
                ErrorCodeEnum.PAY0004.getMessage(), ErrorCodeEnum.PAY0004.getCode(), ex.getBindingResult().getFieldErrors().stream().map(it -> new ValidationError(it.getField(), it.getDefaultMessage())).collect(Collectors.toList())
        );

        return ResponseEntity.status(400).body(BaseResponse.<ErrorResponse>builder().success(false).error(error).build());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<BaseResponse<String>> handleInternalServerError(InternalServerErrorException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode(), null);
        return new ResponseEntity<>(BaseResponse.<String>builder().success(false).error(error).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<BaseResponse<String>> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "PAY-00099", null);

        return new ResponseEntity<>(BaseResponse.<String>builder().success(false).error(error).build(), HttpStatus.BAD_REQUEST);
    }
}
