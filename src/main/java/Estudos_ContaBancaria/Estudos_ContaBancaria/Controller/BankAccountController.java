package Estudos_ContaBancaria.Estudos_ContaBancaria.Controller;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Banks.BankAccount;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Repository.BankAccountRepository;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountController (BankAccountService bankAccountService, BankAccountRepository bankAccountRepository){
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public  BankAccount getAccountById(@PathVariable Long id) throws AccountNotFoundException {
        return bankAccountService.getAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account ID " + id + " not found"));
    }

    @PostMapping
    public BankAccount createAccount(@RequestBody BankAccount account){
        return bankAccountService.createAccount(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account ID" + id + " not found"));

        bankAccountRepository.delete(account);
        return ResponseEntity.ok("Account Deleted");
    }
}
