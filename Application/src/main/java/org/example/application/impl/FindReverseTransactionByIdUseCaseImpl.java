package org.example.application.impl;

import org.example.application.gateway.FindReverseTransactionByIdGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.NotFoundException;
import org.example.usecase.FindReverseTransactionByIdUseCase;

public class FindReverseTransactionByIdUseCaseImpl implements FindReverseTransactionByIdUseCase {
    private final FindReverseTransactionByIdGateway findReverseTransactionByIdGateway;

    public FindReverseTransactionByIdUseCaseImpl(FindReverseTransactionByIdGateway findReverseTransactionByIdGateway) {
        this.findReverseTransactionByIdGateway = findReverseTransactionByIdGateway;
    }

    @Override
    public Transaction findById(Long id) throws NotFoundException {
        return findReverseTransactionByIdGateway.findById(id);
    }
}
