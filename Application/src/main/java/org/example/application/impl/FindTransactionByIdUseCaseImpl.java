package org.example.application.impl;

import org.example.application.gateway.FindTransactionByIdGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.NotFoundException;
import org.example.usecase.FindTransactionByIdUseCase;

public class FindTransactionByIdUseCaseImpl implements FindTransactionByIdUseCase {
    private final FindTransactionByIdGateway findTransactionByIdGateway;

    public FindTransactionByIdUseCaseImpl(FindTransactionByIdGateway findTransactionByIdGateway) {
        this.findTransactionByIdGateway = findTransactionByIdGateway;
    }

    @Override
    public Transaction findById(Long id) throws NotFoundException {
        return findTransactionByIdGateway.findById(id);
    }
}
