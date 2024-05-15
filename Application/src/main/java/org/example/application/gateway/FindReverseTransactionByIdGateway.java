package org.example.application.gateway;

import org.example.core.domain.Transaction;
import org.example.core.exception.NotFoundException;

public interface FindReverseTransactionByIdGateway {
    Transaction findById(Long id) throws NotFoundException;
}
