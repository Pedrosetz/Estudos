package Estudos_ContaBancaria.Estudos_ContaBancaria.Repository;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Banks.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findUserById(Long id);
}
