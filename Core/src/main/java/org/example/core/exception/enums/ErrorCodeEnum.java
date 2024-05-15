package org.example.core.exception.enums;

public enum ErrorCodeEnum {
    PAY0001("Tipo de pagamento invalido", "PAY-0001"),
    PAY0002("Estorno nao encontrado", "PAY-0002"),
    PAY0003("Pagamento nao encontrado", "PAY-0003"),
    PAY0004("Invalid Request", "PAY-0004"),
    PAY0005("Houve um erro interno", "PAY-0005"),
    ;

    private final String message;
    private final String code;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
