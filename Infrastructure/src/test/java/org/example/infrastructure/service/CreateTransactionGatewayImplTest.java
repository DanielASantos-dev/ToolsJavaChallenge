package org.example.infrastructure.service;

import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.example.core.exception.InternalServerErrorException;
import org.example.infrastructure.entity.TransactionEntity;
import org.example.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CreateTransactionGatewayImpl.class)
public class CreateTransactionGatewayImplTest {
    Transaction transaction;
    TransactionEntity transactionEntity;
    @MockBean
    TransactionRepository transactionRepository;
    @Autowired
    CreateTransactionGatewayImpl createTransactionGateway;

    @BeforeEach
    void setup(){
        var id = 1L;
        var card = "1111222233334444";
        var value = new BigDecimal(22);
        var establishment = "Test Store";
        var type = PaymentFormEnum.AVISTA;
        var invoces = "1";
        var nsu = "123456789";
        var authorizationCode = "1234";
        var status = StatusEnum.AUTORIZADO;
        var dateTime = LocalDateTime.now();

        transactionEntity = new TransactionEntity(
                id, card, value, establishment, type, invoces, nsu, authorizationCode, status, dateTime
        );

        this.transaction = new Transaction(
                card, value, establishment, type, invoces, dateTime
        );

        this.transaction.authorizeTransaction();
    }

    @Test
    void createTransactionEntityWithExistingDataReturnTransaction() throws InternalServerErrorException {
        when(transactionRepository.save(TransactionEntity.from(transaction))).thenReturn(transactionEntity);
        var sut = createTransactionGateway.create(transaction);
        assertNotNull(sut);
        assertEquals(transaction.getCard(), sut.getCard());
        verify(transactionRepository, times(1)).save(TransactionEntity.from(transaction));
    }
}
