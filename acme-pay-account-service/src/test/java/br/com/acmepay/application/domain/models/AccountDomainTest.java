package br.com.acmepay.application.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;
import br.com.acmepay.application.ports.out.ICheckCustomerDocument;
import br.com.acmepay.application.ports.out.IFindAccountByNumber;
import br.com.acmepay.application.ports.out.IMakeTransaction;
import br.com.acmepay.application.ports.out.IUpdateAccount;

public class AccountDomainTest {

    AccountDomain account1;
    AccountDomain account2;

    @Mock
    IFindAccountByNumber findAccountByNumber;

    @Mock
    IMakeTransaction makeTransaction;

    @Mock
    IUpdateAccount updateAccount;

    @Mock
    ICheckCustomerDocument checkCustomerDocument;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        account1 = AccountDomain.builder()
                .created_at(LocalDateTime.now())
                .updated_at(null)
                .id(1L)
                .agency(1)
                .number(1)
                .balance(BigDecimal.valueOf(500))
                .document("11122233396")
                .close(false)
                .build();

        account2 = AccountDomain.builder()
                .created_at(LocalDateTime.now())
                .updated_at(null)
                .id(2L)
                .agency(1)
                .number(2)
                .balance(BigDecimal.valueOf(500))
                .document("11122233396")
                .close(false)
                .build();

    }

    // Deposit

    @Test
    void shouldDepositAmount() {
        account1.deposit(BigDecimal.valueOf(50));

        var got = account1.getBalance();
        var want = BigDecimal.valueOf(550);

        assertEquals(want, got);
    }

    // Withdraw

    @Test
    void shouldWithdrawAmount() throws Exception {
        account1.withdraw(BigDecimal.valueOf(100));

        var got = account1.getBalance();
        var want = BigDecimal.valueOf(400);

        assertEquals(want, got);
    }

    @Test
    void shouldNotWithdrawMoreThanBalance() {
        var exception = assertThrows(BalanceToWithdrawException.class, () -> {
            account1.withdraw(BigDecimal.valueOf(600));
        });

        var got = account1.getBalance();
        var want = BigDecimal.valueOf(500);

        assertEquals(want, got);
        assertEquals("Cannot withdraw more than the account balance", exception.getMessage());
    }

    // Transaction

    @Test
    void shouldMakeTranscation() throws Exception {
        var accountDomain = AccountDomain.builder().build();

        when(findAccountByNumber.execute(1)).thenReturn(account1);
        when(findAccountByNumber.execute(2)).thenReturn(account2);

        var gotErr = accountDomain.transaction(
                1, 2, BigDecimal.valueOf(250),
                makeTransaction,
                findAccountByNumber,
                updateAccount);

        var got = List.of(
                account1.getBalance(),
                account2.getBalance());
        var want = List.of(
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(750));

        assertEquals(want, got);
        assertTrue(gotErr.getStatus());
    }

    @Test
    void shouldNotMakeNegativeTransaction() throws Exception {
        var accountDomain = AccountDomain.builder().build();

        when(findAccountByNumber.execute(1)).thenReturn(account1);
        when(findAccountByNumber.execute(2)).thenReturn(account2);

        var gotErr = accountDomain.transaction(
                1, 2, BigDecimal.valueOf(-250),
                makeTransaction,
                findAccountByNumber,
                updateAccount);

        var got = List.of(
                account1.getBalance(),
                account2.getBalance());
        var want = List.of(
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(500));

        assertEquals(want, got);
        assertFalse(gotErr.getStatus());
    }

    // Create

    @Test
    void shouldCreateAccount() {
        account1.create(checkCustomerDocument);

        var captor = ArgumentCaptor.forClass(DocumentRequest.class);
        verify(checkCustomerDocument).execute(captor.capture());
        var documentRequest = captor.getValue();

        var document = documentRequest.getDocument();
        var validationId = documentRequest.getValidationID();

        assertEquals(document, account1.getDocument());
        assertEquals(account1, account1.getAccountFromCache(validationId));
    }
}
