package org.example.application.impl;

import org.example.application.dto.ValidateDto;
import org.example.application.gateway.CreateTransactionGateway;
import org.example.application.gateway.ValidateTransactionGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.InternalServerErrorException;
import org.example.usecase.CreateTransactionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CreateTransactionUseCaseImplTest {
    @Mock
    private  CreateTransactionGateway createTransactionGateway;
    @Mock
    private  ValidateTransactionGateway validateTransactionGateway;
    private CreateTransactionUseCase createTransactionUseCase;

    @BeforeEach
    void setup(){
        createTransactionUseCase = new CreateTransactionUseCaseImpl(createTransactionGateway, validateTransactionGateway);
    }

    @Test
    void testCreateAuthorizedTransaction() throws InternalServerErrorException {
        Transaction transaction = mock(Transaction.class);
        when(validateTransactionGateway.validate(transaction)).thenReturn(new ValidateDto(true, "123456"));

        Transaction createdTransactionMock = mock(Transaction.class);
        when(createTransactionGateway.create(transaction)).thenReturn(createdTransactionMock);

        Transaction sut = createTransactionUseCase.create(transaction);

        verify(validateTransactionGateway, times(1)).validate(transaction);
        verify(transaction, times(1)).authorizeTransaction();
        verify(transaction, times(1)).setAuthorizationCode("123456");
        verify(transaction, never()).denyTransaction();
        verify(createTransactionGateway, times(1)).create(transaction);
        assertEquals(createdTransactionMock, sut);
    }

    @Test
    void testCreateUnauthorizedTransaction() throws InternalServerErrorException {
        Transaction transaction = mock(Transaction.class);
        when(validateTransactionGateway.validate(transaction)).thenReturn(new ValidateDto(false, null));

        Transaction createdTransactionMock = mock(Transaction.class);
        when(createTransactionGateway.create(transaction)).thenReturn(createdTransactionMock);

        Transaction sut = createTransactionUseCase.create(transaction);

        verify(transaction, times(1)).denyTransaction();
        verify(transaction, never()).authorizeTransaction();
        verify(createTransactionGateway, times(1)).create(transaction);
        assertEquals(createdTransactionMock, sut);
    }
}
