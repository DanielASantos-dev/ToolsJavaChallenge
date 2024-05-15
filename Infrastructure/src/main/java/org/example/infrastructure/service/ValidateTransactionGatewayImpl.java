package org.example.infrastructure.service;

import org.example.application.dto.ValidateDto;
import org.example.application.gateway.ValidateTransactionGateway;
import org.example.core.domain.Transaction;
import org.example.infrastructure.client.validatetransaction.ValidateTransactionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidateTransactionGatewayImpl implements ValidateTransactionGateway {
    private final Logger log = LoggerFactory.getLogger(ValidateTransactionGatewayImpl.class);
    private final ValidateTransactionClient validateTransactionClient;

    public ValidateTransactionGatewayImpl(ValidateTransactionClient validateTransactionClient) {
        this.validateTransactionClient = validateTransactionClient;
    }

    @Override
    public ValidateDto validate(Transaction transaction) {
        log.info("Star::validate");
        return validateTransactionClient.validateTransaction();
    }
}
