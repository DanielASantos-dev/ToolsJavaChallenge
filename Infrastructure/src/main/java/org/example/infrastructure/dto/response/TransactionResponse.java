package org.example.infrastructure.dto.response;

import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(TransactionDetailResponse transacao) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                new TransactionDetailResponse(
                        transaction.getId().toString(),
                        transaction.getCard(),
                        new DescriptionResponse(
                                transaction.getValue(),
                                transaction.getDateTime(),
                                transaction.getEstablishment(),
                                transaction.getNsu(),
                                transaction.getAuthorizationCode(),
                                transaction.getStatus().name()
                        ),
                        new PaymentFormResponse(
                                transaction.getType(),
                                transaction.getInvoces()
                        )
                )
        );
    }

    public record TransactionDetailResponse(String id, String cartao, DescriptionResponse descricao, PaymentFormResponse formaPagamento) {}
    public record DescriptionResponse(BigDecimal valor, LocalDateTime dataHora, String estabelecimento, String nsu, String codigoAutorizacao, String status) {}
    public record PaymentFormResponse(PaymentFormEnum tipo, String parcelas) {}
}

