package org.example.infrastructure;

import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Constants {

    public static List<Transaction> transactions = List.of(
            new Transaction(1L, "1111222233334444", new BigDecimal(22), "Test Store", PaymentFormEnum.AVISTA, "1", "123456789", "34156464", StatusEnum.AUTORIZADO, LocalDateTime.now()),
            new Transaction(2L, "2235232523252589", new BigDecimal(40), "Test Store", PaymentFormEnum.AVISTA, "1", "654654654", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(3L, "1345646546542131", new BigDecimal(90), "Test Store", PaymentFormEnum.AVISTA, "1", "565465465", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(4L, "3546574654646465", new BigDecimal(85), "Test Store", PaymentFormEnum.AVISTA, "1", "467497464", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(5L, "1341654646466464", new BigDecimal(18), "Test Store", PaymentFormEnum.AVISTA, "1", "657897646", "16546546", StatusEnum.AUTORIZADO, LocalDateTime.now())
    );

    public static List<Transaction> transactionsUnauthorized  = List.of(
            new Transaction(1L, "1111222233334444", new BigDecimal(22), "Test Store", PaymentFormEnum.AVISTA, "1", "123456789", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(2L, "2235232523252589", new BigDecimal(40), "Test Store", PaymentFormEnum.AVISTA, "1", "654654654", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(3L, "1345646546542131", new BigDecimal(90), "Test Store", PaymentFormEnum.AVISTA, "1", "565465465", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(4L, "3546574654646465", new BigDecimal(85), "Test Store", PaymentFormEnum.AVISTA, "1", "467497464", null, StatusEnum.NEGADO, LocalDateTime.now()),
            new Transaction(5L, "1341654646466464", new BigDecimal(18), "Test Store", PaymentFormEnum.AVISTA, "1", "657897646", null, StatusEnum.NEGADO, LocalDateTime.now())
    );
}
