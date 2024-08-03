package br.com.acmepay.adapters.input.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateAccountUseCase;
import br.com.acmepay.application.ports.in.ICreateCardUseCase;
import br.com.acmepay.application.ports.in.IListAccountsUseCase;
import br.com.acmepay.application.ports.in.IMakeTransactionUseCase;

@WebMvcTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ICreateAccountUseCase createAccountUseCase;

    @MockBean
    ICreateCardUseCase createCardUseCase;

    @MockBean
    IListAccountsUseCase listAccountsUseCase;

    @MockBean
    IMakeTransactionUseCase makeTransactionUseCase;

    List<AccountDomain> accounts;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setUpOnce() {
        mapper = new ObjectMapper();

        var module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

        mapper.registerModule(module);
    }

    @BeforeEach
    void setUp() {

        accounts = List.of(
                AccountDomain.builder()
                        .created_at(LocalDateTime.now())
                        .updated_at(null)
                        .id(1L)
                        .agency(1)
                        .number(1)
                        .balance(BigDecimal.valueOf(500))
                        .document("11122233396")
                        .close(false)
                        .build(),
                AccountDomain.builder()
                        .created_at(LocalDateTime.now())
                        .updated_at(null)
                        .id(2L)
                        .agency(1)
                        .number(2)
                        .balance(BigDecimal.valueOf(500))
                        .document("11122233397")
                        .close(false)
                        .build(),
                AccountDomain.builder()
                        .created_at(LocalDateTime.now())
                        .updated_at(null)
                        .id(3L)
                        .agency(1)
                        .number(3)
                        .balance(BigDecimal.valueOf(500))
                        .document("11122233398")
                        .close(false)
                        .build());
    }

    @Test
    void shouldListAllAccounts() throws Exception {
        String jsonAccounts = mapper.writeValueAsString(accounts);

        when(listAccountsUseCase.execute()).thenReturn(accounts);

        mockMvc.perform(get("/api/v1/accounts/list"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAccounts));
    }

}
