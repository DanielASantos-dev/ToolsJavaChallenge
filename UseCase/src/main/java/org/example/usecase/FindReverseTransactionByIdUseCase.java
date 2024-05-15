package org.example.usecase;

import org.example.core.domain.Transaction;
import org.example.core.exception.NotFoundException;

public interface FindReverseTransactionByIdUseCase {
    Transaction findById(Long id) throws NotFoundException;
}
