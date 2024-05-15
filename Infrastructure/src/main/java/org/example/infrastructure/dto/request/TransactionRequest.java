package org.example.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequest(@Valid @NotNull TransactionDetail transacao) {
    public Transaction toTransaction(){
        return new Transaction(
                this.transacao.cartao,
                new BigDecimal(this.transacao.descricao.valor),
                this.transacao.descricao.estabelecimento,
                this.transacao.formaPagamento.tipo,
                this.transacao.formaPagamento.parcelas,
                this.transacao.descricao.dataHora
        );
    }
    public record TransactionDetail(@NotBlank String cartao, @Valid @NotNull Description descricao, @Valid @NotNull FormPayment formaPagamento){}
    public record Description(@NotBlank String valor, @NotNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora, @NotBlank String estabelecimento) {}
    public record FormPayment(@NotNull PaymentFormEnum tipo, @NotBlank String parcelas) {}
}
