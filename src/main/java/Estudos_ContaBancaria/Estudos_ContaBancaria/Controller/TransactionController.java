package Estudos_ContaBancaria.Estudos_ContaBancaria.Controller;

import Estudos_ContaBancaria.Estudos_ContaBancaria.Transactions.Transaction;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Service.TransactionService;
import Estudos_ContaBancaria.Estudos_ContaBancaria.Transactions.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest request) throws IllegalAccessException {
        return transactionService.createTransaction(request.getReceiverId(), request.getSenderId(), request.getAmount());
    }

}
