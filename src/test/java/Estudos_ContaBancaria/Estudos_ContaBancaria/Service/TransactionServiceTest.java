package Estudos_ContaBancaria.Estudos_ContaBancaria.Service;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Banks.BankAccount;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Repository.BankAccountRepository;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Repository.TransactionRepository;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Transactions.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {
    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private BankAccount sender;
    private BankAccount receiver;

    @Test
    @BeforeEach
    void createTransaction() {
        MockitoAnnotations.openMocks(this);
        sender = new BankAccount(1L, "12345", 500.0);
        receiver = new BankAccount(2L, "67891", 300.0);
    }

    @Test
    void createTransactionSuccess() throws IllegalAccessException {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(bankAccountRepository.findById(2L)).thenReturn(Optional.of(receiver));
        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Transaction transaction = transactionService.createTransaction(2L, 1L, 100.0);

        assertNotNull(transaction);
        assertEquals(100.0, transaction.getAmount());
        assertEquals(400.0, sender.getBalance());
        assertEquals(400.0, receiver.getBalance());

        verify(bankAccountRepository, times(2)).save(any(BankAccount.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }
}