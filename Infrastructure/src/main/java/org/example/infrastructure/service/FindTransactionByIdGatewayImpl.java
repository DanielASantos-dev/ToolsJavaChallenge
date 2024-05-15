package org.example.infrastructure.service;

import org.example.application.gateway.FindTransactionByIdGateway;
import org.example.core.domain.Transaction;
import org.example.core.domain.enums.StatusEnum;
import org.example.core.exception.NotFoundException;
import org.example.core.exception.enums.ErrorCodeEnum;
import org.example.infrastructure.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindTransactionByIdGatewayImpl implements FindTransactionByIdGateway {
    private final Logger log = LoggerFactory.getLogger(FindTransactionByIdGatewayImpl.class);
    private final TransactionRepository transactionRepository;
    public FindTransactionByIdGatewayImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findById(Long id) throws NotFoundException {
        log.info("findByid::findByid");
        return transactionRepository.findByIdAndStatus(id, StatusEnum.AUTORIZADO)
                .orElseThrow(() -> new NotFoundException(ErrorCodeEnum.PAY0003.getMessage(), ErrorCodeEnum.PAY0003.getCode())).toTransaction();
    }
}
