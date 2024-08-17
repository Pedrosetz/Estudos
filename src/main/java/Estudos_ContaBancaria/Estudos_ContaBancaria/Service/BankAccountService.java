package Estudos_ContaBancaria.Estudos_ContaBancaria.Service;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Banks.BankAccount;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount createAccount(BankAccount account){
        return bankAccountRepository.save(account);
    }
}