package org.example.core.domain;

import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {
    Transaction transaction;
    @BeforeEach
    void setup(){
        var card = "1111222233334444";
        var value = new BigDecimal(22);
        var establishment = "Test Store";
        var type = PaymentFormEnum.AVISTA;
        var invoces = "1";
        var dateTime = LocalDateTime.now();

        this.transaction = new Transaction(
                card, value, establishment, type, invoces, dateTime
        );
    }

    @Test
    void testTransactionCreationWithFullData(){

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

        var transaction = new Transaction(
                id, card, value, establishment, type, invoces, nsu, authorizationCode, status, dateTime
        );

        assertNotNull(transaction);
        assertEquals(id, transaction.getId());
        assertEquals(card, transaction.getCard());
        assertEquals(value, transaction.getValue());
        assertEquals(establishment, transaction.getEstablishment());
        assertEquals(type, transaction.getType());
        assertEquals(invoces, transaction.getInvoces());
        assertEquals(nsu, transaction.getNsu());
        assertEquals(authorizationCode, transaction.getAuthorizationCode());
        assertEquals(status, transaction.getStatus());
        assertEquals(dateTime, transaction.getDateTime());
    }

    @Test
    void testTransactionCreation(){
        var card = "1111222233334444";
        var value = new BigDecimal(22);
        var establishment = "Test Store";
        var type = PaymentFormEnum.AVISTA;
        var invoces = "1";
        var dateTime = LocalDateTime.now();

        this.transaction = new Transaction(
                card, value, establishment, type, invoces, dateTime
        );

        assertNotNull(transaction);

        assertEquals(card, transaction.getCard());
        assertEquals(value, transaction.getValue());
        assertEquals(establishment, transaction.getEstablishment());
        assertEquals(type, transaction.getType());
        assertEquals(invoces, transaction.getInvoces());
        assertEquals(dateTime, transaction.getDateTime());

        assertNotNull(transaction.getNsu());
    }

    @Test
    void testCancelTransaction(){
       transaction.denyTransaction();
       assertNotNull(transaction.getStatus());
       assertEquals(transaction.getStatus(), StatusEnum.NEGADO);
    }

    @Test
    void testAuthorizeTransaction(){
        transaction.authorizeTransaction();
        assertNotNull(transaction.getStatus());
        assertEquals(transaction.getStatus(), StatusEnum.AUTORIZADO);
    }

}
