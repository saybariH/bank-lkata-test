package bank.kata.repository;

import bank.kata.beans.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamza Saybari
 **/
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public void saveTransaction(int amount) {
        transactions.add(new Transaction(amount));
    }

    public List<Transaction> findAllTransactions() {
        return transactions;
    }
}