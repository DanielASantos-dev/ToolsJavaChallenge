package org.example.infrastructure.service;

import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.example.infrastructure.entity.TransactionEntity;
import org.example.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = FindAllTransactionGatewayImpl.class)
public class FindAllTransactionGatewayImplTest {
    TransactionEntity transactionEntity;
    @MockBean
    TransactionRepository transactionRepository;
    @Autowired
    FindAllTransactionGatewayImpl findAllTransactionGateway;

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
    }

    @Test
    void findAll(){
        when(transactionRepository.findAll()).thenReturn(List.of(transactionEntity));

        var sut = findAllTransactionGateway.findAll();
        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals(transactionEntity.getAuthorizationCode(), sut.get(0).getAuthorizationCode());
        verify(transactionRepository, times(1)).findAll();
    }

}
