package Estudos_ContaBancaria.Estudos_ContaBancaria.Repository;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
