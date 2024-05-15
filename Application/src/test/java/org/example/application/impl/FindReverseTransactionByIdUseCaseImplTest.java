package org.example.application.impl;

import org.example.application.gateway.FindReverseTransactionByIdGateway;
import org.example.core.domain.Transaction;
import org.example.core.exception.NotFoundException;
import org.example.usecase.FindReverseTransactionByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FindReverseTransactionByIdUseCaseImplTest {
    @Mock
    private FindReverseTransactionByIdGateway findReverseTransactionByIdGateway;
    FindReverseTransactionByIdUseCase findReverseTransactionByIdUseCase;

    @BeforeEach
    void setup(){
        findReverseTransactionByIdUseCase = new FindReverseTransactionByIdUseCaseImpl(findReverseTransactionByIdGateway);
    }

    @Test
    void findReverseTransactionByIdUseCaseWithExistingDataReturnTransaction() throws NotFoundException {
        when(findReverseTransactionByIdGateway.findById(1L)).thenReturn(Constants.transactionsUnauthorized.get(1));

        Transaction sut = findReverseTransactionByIdUseCase.findById(1L);

        verify(findReverseTransactionByIdGateway, times(1)).findById(1L);
        assertEquals(Constants.transactions.get(1), sut);
    }

    @Test
    void findReverseTransactionByIdUseCaseWithNotExistingDataThrowNotFoundException() throws NotFoundException {
        when(findReverseTransactionByIdGateway.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {
            findReverseTransactionByIdUseCase.findById(1L);
        });

        verify(findReverseTransactionByIdGateway, times(1)).findById(1L);
    }








}
