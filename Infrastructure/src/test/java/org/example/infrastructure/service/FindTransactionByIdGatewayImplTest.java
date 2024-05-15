package org.example.infrastructure.service;

import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.example.core.exception.NotFoundException;
import org.example.infrastructure.entity.TransactionEntity;
import org.example.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = FindTransactionByIdGatewayImpl.class)
public class FindTransactionByIdGatewayImplTest {
    TransactionEntity transactionEntity;
    @MockBean
    TransactionRepository transactionRepository;

    @Autowired
    FindTransactionByIdGatewayImpl findTransactionByIdGateway;

    @BeforeEach
    void setup(){
        var id = 1L;
        var card = "1111222233334444";
        var value = new BigDecimal(22);
        var establishment = "Test Store";
        var type = PaymentFormEnum.AVISTA;
        var invoces = "1";
        var nsu = "123456789";
        var status = StatusEnum.NEGADO;
        var dateTime = LocalDateTime.now();

        transactionEntity = new TransactionEntity(
                id, card, value, establishment, type, invoces, nsu, null, status, dateTime
        );
    }

    @Test
    void findByIdWithExistingDataReturnTransaction() throws NotFoundException {
        when(transactionRepository.findByIdAndStatus(1L, StatusEnum.AUTORIZADO)).thenReturn(Optional.of(transactionEntity));

        var sut = findTransactionByIdGateway.findById(1L);

        assertNotNull(sut);
        verify(transactionRepository, times(1)).findByIdAndStatus(1L, StatusEnum.AUTORIZADO);
    }

    @Test
    void findByIdWithNotExistingDataThrowNotException() {
        when(transactionRepository.findByIdAndStatus(1L, StatusEnum.AUTORIZADO)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> findTransactionByIdGateway.findById(1L));
        verify(transactionRepository, times(1)).findByIdAndStatus(1L, StatusEnum.AUTORIZADO);
    }
}
