package org.example.core.domain.enums;

import org.example.core.exception.BadRequestException;
import org.example.core.exception.enums.ErrorCodeEnum;

public enum PaymentFormEnum {

    AVISTA("AVISTA"), PARCELADO_LOJA("PARCELADO LOJA"), PARCELADO_EMISSOR("PARCELADO EMISSOR");
    private final String description;

    PaymentFormEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentFormEnum getByDescription(String description) throws BadRequestException {
        for (PaymentFormEnum enumValue : PaymentFormEnum.values()) {
            if (enumValue.getDescription().equalsIgnoreCase(description)) {
                return enumValue;
            }
        }
        throw new BadRequestException(ErrorCodeEnum.PAY0001.getMessage(), ErrorCodeEnum.PAY0001.getCode());
    }
}
