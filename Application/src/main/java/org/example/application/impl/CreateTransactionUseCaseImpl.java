package org.example.application.impl;

import org.example.application.gateway.CreateTransactionGateway;
import org.example.application.gateway.ValidateTransactionGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.InternalServerErrorException;
import org.example.usecase.CreateTransactionUseCase;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final CreateTransactionGateway createTransactionGateway;
    private final ValidateTransactionGateway validateTransactionGateway;

    public CreateTransactionUseCaseImpl(CreateTransactionGateway createTransactionGateway, ValidateTransactionGateway validateTransactionGateway) {
        this.createTransactionGateway = createTransactionGateway;
        this.validateTransactionGateway = validateTransactionGateway;
    }

    @Override
    public Transaction create(Transaction transaction) throws InternalServerErrorException {
        var authorized = validateTransactionGateway.validate(transaction);

        if (authorized.success()){
            transaction.authorizeTransaction();
            transaction.setAuthorizationCode(authorized.authorizationCode());
        }else {
            transaction.denyTransaction();
        }

        return createTransactionGateway.create(transaction);
    }


}
