package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String card;
    @Column(name = "paymentValue")
    private BigDecimal value;
    private String establishment;
    @Column(name = "paymentType")
    @Enumerated(EnumType.STRING)
    private PaymentFormEnum type;
    private String invoces;
    private String nsu;
    private String authorizationCode;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private LocalDateTime dateTime;

    public TransactionEntity(String card, BigDecimal value, String establishment, PaymentFormEnum type, String invoces, String nsu, String authorizationCode, StatusEnum status, LocalDateTime dateTime) {
        this.card = card;
        this.value = value;
        this.establishment = establishment;
        this.type = type;
        this.invoces = invoces;
        this.nsu = nsu;
        this.authorizationCode = authorizationCode;
        this.status = status;
        this.dateTime = dateTime;
    }

    public static TransactionEntity from(Transaction transaction){
        return new TransactionEntity(
                transaction.getCard(),
                transaction.getValue(),
                transaction.getEstablishment(),
                transaction.getType(),
                transaction.getInvoces(),
                transaction.getNsu(),
                transaction.getAuthorizationCode(),
                transaction.getStatus(),
                transaction.getDateTime()
        );
    }

    public Transaction toTransaction(){
        return new Transaction(
              this.id,
              this.card,
              this.value,
              this.establishment,
              this.type,
              this.invoces,
              this.nsu,
              this.authorizationCode,
              this.status,
              this.dateTime
        );
    }
}
