package org.example.infrastructure.repository;

import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.example.infrastructure.entity.TransactionEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TransactionRepositoryTest {
    Transaction transaction;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

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
    void createTransactionWithValidDataReturnTransactionEntity(){
        transaction.authorizeTransaction();
        var transactionCreated = transactionRepository.save(TransactionEntity.from(transaction));
        var sut = testEntityManager.find(TransactionEntity.class, transactionCreated.getId());

        assertNotNull(sut);
        assertEquals(transaction.getCard(), sut.getCard());
        assertEquals(transaction.getType(), sut.getType());
    }

    @Test
    void findByIdAndStatusWithExistingDataReturnTransactionEntity(){
        transaction.authorizeTransaction();
        var id = (Long)testEntityManager.persistAndGetId(TransactionEntity.from(transaction));
        var sut = transactionRepository.findByIdAndStatus(id, StatusEnum.AUTORIZADO);
        assertNotNull(sut);
        assertTrue(sut.isPresent());
        assertEquals(id, sut.get().getId());
    }

    @Test
    void findByIdAndStatusWithNotExistingDataReturnOptionalEmpty(){
            var sut = transactionRepository.findByIdAndStatus(1L, StatusEnum.AUTORIZADO);
            assertTrue(sut.isEmpty());
    }

    @Test
    void findAllWithExistingData(){
        transaction.authorizeTransaction();
        testEntityManager.persist(TransactionEntity.from(transaction));
        var sut = transactionRepository.findAll();
        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals(StatusEnum.AUTORIZADO, sut.get(0).getStatus());
    }

}
