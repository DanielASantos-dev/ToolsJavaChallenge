package org.example.application.gateway;

import org.example.core.domain.Transaction;

import java.util.List;

public interface FindAllTransactionGateway {

    List<Transaction> findAll();
}
