package bank.kata.repository;

import bank.kata.beans.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TransactionRepository} class serves as an in-memory storage for bank transactions.
 * It provides methods to store new transactions and retrieve all stored transactions.
 *
 * This repository acts as a simple persistence layer, managing a list of transactions
 * without an external database.
 *
 * @author Hamza Saybari
 */
public class TransactionRepository {

    /**
     * A list to store all recorded transactions.
     */
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Saves a new transaction with the specified amount.
     *
     * @param amount The amount of the transaction.
     *               - A **positive number** represents a **deposit**.
     *               - A **negative number** represents a **withdrawal**.
     */
    public void saveTransaction(int amount) {
        transactions.add(new Transaction(amount));
    }

    /**
     * Retrieves all stored transactions in the repository.
     *
     * @return A list of all transactions.
     */
    public List<Transaction> findAllTransactions() {
        return transactions;
    }
}
