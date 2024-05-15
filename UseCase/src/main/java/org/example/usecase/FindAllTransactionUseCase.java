package org.example.usecase;

import org.example.core.domain.Transaction;

import java.util.List;

public interface FindAllTransactionUseCase {
    List<Transaction> findAll();
}
