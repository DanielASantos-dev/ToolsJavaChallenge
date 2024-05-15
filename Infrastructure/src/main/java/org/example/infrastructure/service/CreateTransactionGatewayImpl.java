package org.example.infrastructure.service;

import org.example.application.gateway.CreateTransactionGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.InternalServerErrorException;
import org.example.core.exception.enums.ErrorCodeEnum;
import org.example.infrastructure.entity.TransactionEntity;
import org.example.infrastructure.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionGatewayImpl implements CreateTransactionGateway {
    private final Logger log = LoggerFactory.getLogger(CreateTransactionGatewayImpl.class);
    private final TransactionRepository transactionRepository;

    public CreateTransactionGatewayImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(Transaction transaction) throws InternalServerErrorException {
        log.info("Star::create");
        try {
            return transactionRepository.save(TransactionEntity.from(transaction)).toTransaction();
        } catch (Exception ex) {
            log.error("Error::create::"+ex.getMessage());
            throw new InternalServerErrorException(ErrorCodeEnum.PAY0005.getMessage(), ErrorCodeEnum.PAY0005.getMessage());
        }
    }
}
