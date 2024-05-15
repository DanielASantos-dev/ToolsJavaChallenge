package org.example.application.gateway;

import org.example.application.dto.ValidateDto;
import org.example.core.domain.Transaction;

public interface ValidateTransactionGateway {

    ValidateDto validate(Transaction transaction);
}
