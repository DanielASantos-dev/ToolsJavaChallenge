package org.example.infrastructure.service;

import org.example.application.gateway.FindAllTransactionGateway;
import org.example.core.domain.Transaction;
import org.example.infrastructure.entity.TransactionEntity;
import org.example.infrastructure.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllTransactionGatewayImpl implements FindAllTransactionGateway {
    private final Logger log = LoggerFactory.getLogger(FindAllTransactionGatewayImpl.class);
    private final TransactionRepository transactionRepository;
    public FindAllTransactionGatewayImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        log.info("Star::findAll");
        return transactionRepository.findAll().stream().map(TransactionEntity::toTransaction).collect(Collectors.toList());
    }
}
