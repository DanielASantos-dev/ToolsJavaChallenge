package org.example.infrastructure.service;

import org.example.application.gateway.FindReverseTransactionByIdGateway;
import org.example.core.domain.Transaction;
import org.example.core.domain.enums.StatusEnum;
import org.example.core.exception.NotFoundException;
import org.example.core.exception.enums.ErrorCodeEnum;
import org.example.infrastructure.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindReverseTransactionByIdGatewayImpl implements FindReverseTransactionByIdGateway {
    private final Logger log = LoggerFactory.getLogger(FindReverseTransactionByIdGatewayImpl.class);
    private final TransactionRepository transactionRepository;
    public FindReverseTransactionByIdGatewayImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findById(Long id) throws NotFoundException {
        log.info("Star::findById");
        return transactionRepository.findByIdAndStatus(id, StatusEnum.NEGADO).orElseThrow(() -> new NotFoundException(ErrorCodeEnum.PAY0002.getMessage(), ErrorCodeEnum.PAY0002.getCode())).toTransaction();
    }
}
