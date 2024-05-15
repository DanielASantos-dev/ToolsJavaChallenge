package org.example.application.impl;

import org.example.application.gateway.FindAllTransactionGateway;
import org.example.core.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllTransactionUseCaseImplTest {

    @Mock
    private FindAllTransactionGateway findAllTransactionGateway;
    private FindAllTransactionUseCaseImpl findAllTransactionUseCase;

    @BeforeEach
    void setup(){
        findAllTransactionUseCase = new FindAllTransactionUseCaseImpl(findAllTransactionGateway);
    }

    @Test
    void findAll(){
        when(findAllTransactionGateway.findAll()).thenReturn(Constants.transactions);

        List<Transaction> sut = findAllTransactionUseCase.findAll();
        verify(findAllTransactionGateway, times(1)).findAll();

        assertEquals(Constants.transactions, sut);
        assertEquals(5, sut.size());
    }
}
