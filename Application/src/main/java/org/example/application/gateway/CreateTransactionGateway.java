package org.example.application.gateway;

import org.example.core.domain.Transaction;
import org.example.core.exception.InternalServerErrorException;

public interface CreateTransactionGateway {

    Transaction create(Transaction transaction) throws InternalServerErrorException;
}
