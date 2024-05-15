package org.example.infrastructure.client.validatetransaction;

import org.example.application.dto.ValidateDto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ValidateTransactionClient {

    public ValidateDto validateTransaction(){
        Random random = new Random();
        var validate = random.nextInt(2);

        if (validate == 1){
            return new ValidateDto(true, generateAuthorizationCode());
        }else{
            return new ValidateDto(false, null);
        }
    }

    private String generateAuthorizationCode() {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomCode = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * alphanumeric.length());
            randomCode.append(alphanumeric.charAt(index));
        }
        return randomCode.toString();
    }



}
