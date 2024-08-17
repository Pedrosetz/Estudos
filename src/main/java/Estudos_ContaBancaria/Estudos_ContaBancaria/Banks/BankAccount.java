package Estudos_ContaBancaria.Estudos_ContaBancaria.Banks;

import jakarta.persistence.*;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String accountnumber;
    private double balance;


    public BankAccount(Long id, String accountnumber, double balance) {
        this.id = id;
        this.accountnumber = accountnumber;
        this.balance = balance;
    }

    public BankAccount() {
    }


    public Long getId() {
        return id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
