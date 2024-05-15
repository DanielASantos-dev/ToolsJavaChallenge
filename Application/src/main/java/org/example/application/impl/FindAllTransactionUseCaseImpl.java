package org.example.application.impl;

import org.example.application.gateway.FindAllTransactionGateway;
import org.example.core.domain.Transaction;
import org.example.usecase.FindAllTransactionUseCase;

import java.util.List;

public class FindAllTransactionUseCaseImpl implements FindAllTransactionUseCase {

    private final FindAllTransactionGateway findAllTransactionGateway;

    public FindAllTransactionUseCaseImpl(FindAllTransactionGateway findAllTransactionGateway) {
        this.findAllTransactionGateway = findAllTransactionGateway;
    }

    @Override
    public List<Transaction> findAll() {
        return findAllTransactionGateway.findAll();
    }
}
