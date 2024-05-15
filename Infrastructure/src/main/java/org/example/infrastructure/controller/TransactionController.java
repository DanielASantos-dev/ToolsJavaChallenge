package org.example.infrastructure.controller;

import jakarta.validation.Valid;
import org.example.core.exception.InternalServerErrorException;
import org.example.core.exception.NotFoundException;
import org.example.infrastructure.dto.request.TransactionRequest;
import org.example.infrastructure.dto.response.BaseResponse;
import org.example.infrastructure.dto.response.TransactionResponse;
import org.example.usecase.CreateTransactionUseCase;
import org.example.usecase.FindAllTransactionUseCase;
import org.example.usecase.FindReverseTransactionByIdUseCase;
import org.example.usecase.FindTransactionByIdUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindAllTransactionUseCase findAllTransactionUseCase;
    private final FindReverseTransactionByIdUseCase findReverseTransactionByIdUseCase;
    private final FindTransactionByIdUseCase findTransactionByIdUseCase;
    public TransactionController(CreateTransactionUseCase createTransactionUseCase, FindAllTransactionUseCase findAllTransactionUseCase, FindReverseTransactionByIdUseCase findReverseTransactionByIdUseCase, FindTransactionByIdUseCase findTransactionByIdUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.findAllTransactionUseCase = findAllTransactionUseCase;
        this.findReverseTransactionByIdUseCase = findReverseTransactionByIdUseCase;
        this.findTransactionByIdUseCase = findTransactionByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<TransactionResponse>> createTransaction(@Valid @RequestBody TransactionRequest request) throws InternalServerErrorException {
        log.info("Star::createTransaction");
        var response = createTransactionUseCase.create(request.toTransaction());
        log.info("End::createTransaction");
        return  ResponseEntity.status(201).body(BaseResponse.<TransactionResponse>builder().success(true).result(TransactionResponse.from(response)).build());
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<TransactionResponse>>> findAll(){
        log.info("Star::findAll");
        var response = findAllTransactionUseCase.findAll().stream().map(TransactionResponse::from).collect(Collectors.toList());
        log.info("End::findAll");
        return ResponseEntity.ok(BaseResponse.<List<TransactionResponse>>builder().success(true).result(response).build());
    }

    @GetMapping("reverse/{id}")
    public ResponseEntity<BaseResponse<TransactionResponse>> findReverseById(@PathVariable Long id) throws NotFoundException {
        log.info("Star::findReverseById");
        var response = findReverseTransactionByIdUseCase.findById(id);
        log.info("End::findReverseById");
        return ResponseEntity.ok(BaseResponse.<TransactionResponse>builder().success(true).result(TransactionResponse.from(response)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<TransactionResponse>> findRById(@PathVariable Long id) throws NotFoundException {
        log.info("Star::findRById");
        var response = findTransactionByIdUseCase.findById(id);
        log.info("End::findRById");
        return ResponseEntity.ok(BaseResponse.<TransactionResponse>builder().success(true).result(TransactionResponse.from(response)).build());
    }
}
