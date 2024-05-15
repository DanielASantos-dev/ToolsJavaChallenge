package org.example.core.domain;

import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.example.core.utils.NSUGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private final String card;
    private final BigDecimal value;
    private final String establishment;
    private final PaymentFormEnum type;
    private final String invoces;
    private final String nsu;
    private String authorizationCode;
    private StatusEnum status;
    private LocalDateTime dateTime;

    public Transaction(Long id, String card, BigDecimal value, String establishment, PaymentFormEnum type, String invoces, String nsu, String authorizationCode, StatusEnum status, LocalDateTime dateTime) {
        this.id = id;
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

    public Transaction(String card, BigDecimal value, String establishment, PaymentFormEnum type, String invoces, LocalDateTime dateTime) {
        this.card = card;
        this.value = value;
        this.establishment = establishment;
        this.type = type;
        this.invoces = invoces;
        this.dateTime = LocalDateTime.now();
        this.nsu = NSUGenerator.generateNSU();
        this.dateTime = dateTime;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
    public Long getId() {
        return id;
    }
    public String getCard() {
        return card;
    }
    public BigDecimal getValue() {
        return value;
    }
    public String getEstablishment() {
        return establishment;
    }
    public PaymentFormEnum getType() {
        return type;
    }
    public String getInvoces() {
        return invoces;
    }
    public String getNsu() {
        return nsu;
    }
    public String getAuthorizationCode() {
        return authorizationCode;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public void authorizeTransaction(){
        this.status = StatusEnum.AUTORIZADO;
    }
    public void denyTransaction(){
        this.status = StatusEnum.NEGADO;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction transaction)) return false;

        if (getId() != null ? !getId().equals(transaction.getId()) : transaction.getId() != null) return false;
        if (!getCard().equals(transaction.getCard())) return false;
        if (!getValue().equals(transaction.getValue())) return false;
        if (!getEstablishment().equals(transaction.getEstablishment())) return false;
        if (getType() != transaction.getType()) return false;
        if (!getInvoces().equals(transaction.getInvoces())) return false;
        if (!getNsu().equals(transaction.getNsu())) return false;
        if (getAuthorizationCode() != null ? !getAuthorizationCode().equals(transaction.getAuthorizationCode()) : transaction.getAuthorizationCode() != null)
            return false;
        if (getStatus() != transaction.getStatus()) return false;
        return getDateTime().equals(transaction.getDateTime());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getCard().hashCode();
        result = 31 * result + getValue().hashCode();
        result = 31 * result + getEstablishment().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getInvoces().hashCode();
        result = 31 * result + getNsu().hashCode();
        result = 31 * result + (getAuthorizationCode() != null ? getAuthorizationCode().hashCode() : 0);
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getDateTime().hashCode();
        return result;
    }
}
