package org.example.application.impl;

import org.example.application.gateway.FindTransactionByIdGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.NotFoundException;
import org.example.usecase.FindTransactionByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class FindTransactionByIdUseCaseTest {

    @Mock
    private FindTransactionByIdGateway findTransactionByIdGateway;


    private FindTransactionByIdUseCase findTransactionByIdUseCase;


    @BeforeEach
    void setup() {
        findTransactionByIdUseCase = new FindTransactionByIdUseCaseImpl(findTransactionByIdGateway);
    }

    @Test
    void findTransactionByIdUseCaseWithExistingDataReturnTransaction() throws NotFoundException {
        when(findTransactionByIdGateway.findById(1L)).thenReturn(Constants.transactions.get(0));

        Transaction sut = findTransactionByIdUseCase.findById(1L);

        verify(findTransactionByIdGateway, times(1)).findById(1L);
        assertEquals(Constants.transactions.get(0), sut);
    }

    @Test
    void findTransactionByIdUseCaseWithNotExistingDataThrowNotFoundException() throws NotFoundException {
        when(findTransactionByIdGateway.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {
            findTransactionByIdUseCase.findById(1L);
        });
        verify(findTransactionByIdGateway, times(1)).findById(1L);
    }

}
