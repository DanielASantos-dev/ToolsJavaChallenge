package org.example.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.core.domain.Transaction;
import org.example.core.domain.enums.PaymentFormEnum;
import org.example.core.domain.enums.StatusEnum;
import org.example.core.exception.NotFoundException;
import org.example.infrastructure.Constants;
import org.example.infrastructure.dto.request.TransactionRequest;
import org.example.usecase.CreateTransactionUseCase;
import org.example.usecase.FindAllTransactionUseCase;
import org.example.usecase.FindReverseTransactionByIdUseCase;
import org.example.usecase.FindTransactionByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    private final String baseURL = "/api/v1/transactions";
    Transaction transactionReturn;
    Transaction transaction;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateTransactionUseCase createTransactionUseCase;
    @MockBean
    private FindAllTransactionUseCase findAllTransactionUseCase;
    @MockBean
    private FindReverseTransactionByIdUseCase findReverseTransactionByIdUseCase;
    @MockBean
    private FindTransactionByIdUseCase findTransactionByIdUseCase;


    private final TransactionRequest request = new TransactionRequest(
            new TransactionRequest.TransactionDetail(
                    "4444256523251234",
                    new TransactionRequest.Description("22.00", LocalDateTime.now(), "Store Test"),
                    new TransactionRequest.FormPayment(PaymentFormEnum.AVISTA, "2")
            )
    );
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        var id = 1L;
        var card = "1111222233334444";
        var value = new BigDecimal(22);
        var establishment = "Test Store";
        var type = PaymentFormEnum.AVISTA;
        var invoces = "1";
        var nsu = "123456789";
        var authorizationCode = "1234";
        var status = StatusEnum.AUTORIZADO;
        var dateTime = LocalDateTime.now();

        transactionReturn = new Transaction(
                id, card, value, establishment, type, invoces, nsu, authorizationCode, status, dateTime
        );

        transaction = new Transaction(card, value, establishment, PaymentFormEnum.AVISTA, "1", LocalDateTime.MIN);
    }

    @Test
    void createTransactionWithValidDataRetunrs() throws Exception {
        when(createTransactionUseCase.create(any())).thenReturn(transactionReturn);

        mockMvc.perform(post(baseURL)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.transacao.id").value("1"))
                .andExpect(jsonPath("$.result.transacao.cartao").value("1111222233334444"))
                .andExpect(jsonPath("$.result.transacao.descricao.valor").value(22))
                .andExpect(jsonPath("$.result.transacao.descricao.estabelecimento").value("Test Store"))
                .andExpect(jsonPath("$.result.transacao.formaPagamento.tipo").value("AVISTA"))
                .andExpect(jsonPath("$.result.transacao.formaPagamento.parcelas").value("1"))
                .andExpect(jsonPath("$.message").isEmpty())
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void findAllWithExistingDataReturnsTransactios() throws Exception {
        when(findAllTransactionUseCase.findAll()).thenReturn(Constants.transactions);

        mockMvc.perform(get(baseURL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[*].transacao.id").exists());
    }

    @Test
    void findReverseByIdWithExistingDataReturnsTransaction() throws Exception {
        when(findReverseTransactionByIdUseCase.findById(any())).thenReturn(Constants.transactionsUnauthorized.get(0));

        mockMvc.perform(get(baseURL+"/reverse/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.transacao.id").value(Constants.transactionsUnauthorized.get(0).getId()));
    }

    @Test
    void findReverseByIdWithNotExistingDataThrowNotFoundException() throws Exception {
        when(findReverseTransactionByIdUseCase.findById(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get(baseURL+"/reverse/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result").doesNotExist());
    }

    @Test
    void findByIdWithExistingDataReturnsTransaction() throws Exception {
        when(findTransactionByIdUseCase.findById(any())).thenReturn(Constants.transactions.get(0));

        mockMvc.perform(get(baseURL+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.transacao.id").value(Constants.transactions.get(0).getId()));
    }

    @Test
    void findByIdWithNotExistingDataThrowNotFoundException() throws Exception {
        when(findTransactionByIdUseCase.findById(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get(baseURL+"/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result").doesNotExist());
    }
}
