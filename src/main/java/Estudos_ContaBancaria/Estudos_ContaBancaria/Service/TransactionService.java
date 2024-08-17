package Estudos_ContaBancaria.Estudos_ContaBancaria.Service;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Banks.BankAccount;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Repository.BankAccountRepository;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Repository.TransactionRepository;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;

@Service
public class TransactionService  {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public TransactionService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Long receiverId, Long senderId, double amount) throws IllegalAccessException {
        if (receiverId.equals(senderId)) {
            throw new IllegalAccessException("No transfer money in the same account");
        }

        BankAccount sender = bankAccountRepository.findById(senderId)
                .orElseThrow(() -> new ResourceAccessException("Sender not found"));
        BankAccount receiver = bankAccountRepository.findById(receiverId)
                .orElseThrow(() -> new ResourceAccessException("Receiver not found"));

        if (amount <= 0) {
            throw new IllegalAccessException("Negative amount");
        }

        if (sender.getBalance() < amount) {
            throw new IllegalAccessException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);

        Transaction transaction = new Transaction(receiverId, amount, senderId, LocalDateTime.now());

        return transactionRepository.save(transaction);

    }


}
