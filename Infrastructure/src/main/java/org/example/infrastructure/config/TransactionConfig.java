package org.example.infrastructure.config;

import org.example.application.gateway.*;
import org.example.application.impl.CreateTransactionUseCaseImpl;
import org.example.application.impl.FindAllTransactionUseCaseImpl;
import org.example.application.impl.FindReverseTransactionByIdUseCaseImpl;
import org.example.application.impl.FindTransactionByIdUseCaseImpl;
import org.example.usecase.CreateTransactionUseCase;
import org.example.usecase.FindAllTransactionUseCase;
import org.example.usecase.FindReverseTransactionByIdUseCase;
import org.example.usecase.FindTransactionByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(CreateTransactionGateway createTransactionGateway, ValidateTransactionGateway validateTransactionGateway){
        return new CreateTransactionUseCaseImpl(createTransactionGateway, validateTransactionGateway);
    }

    @Bean
    public FindAllTransactionUseCase findAllTransactionUseCase(FindAllTransactionGateway findAllTransactionGateway){
        return new FindAllTransactionUseCaseImpl(findAllTransactionGateway);
    }

    @Bean
    public FindReverseTransactionByIdUseCase findReverseTransactionByIdUseCase(FindReverseTransactionByIdGateway findReverseTransactionByIdGateway){
        return new FindReverseTransactionByIdUseCaseImpl(findReverseTransactionByIdGateway);
    }

    @Bean
    public FindTransactionByIdUseCase findTransactionByIdUseCase(FindTransactionByIdGateway findTransactionByIdGateway){
        return new FindTransactionByIdUseCaseImpl(findTransactionByIdGateway);
    }
}
