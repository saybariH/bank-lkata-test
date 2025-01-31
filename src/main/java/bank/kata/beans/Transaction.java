package bank.kata.beans;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a financial transaction within a bank account.
 * Each transaction records an amount and the date it was created.
 * <p>
 * This class is immutable, ensuring that transaction data remains unchanged after creation.
 * </p>
 *
 * @author Hamza Saybari
 */
public class Transaction implements Serializable {

    /**
     * The amount of the transaction.
     */
    private final int amount;

    /**
     * The date when the transaction occurred.
     */
    private final LocalDate date;

    /**
     * Constructs a new transaction with the specified amount.
     * The transaction date is automatically set to the current date.
     *
     * @param amount The transaction amount.
     *               - A **positive number** represents a **deposit**.
     *               - A **negative number** represents a **withdrawal**.
     */
    public Transaction(int amount) {
        this.amount = amount;
        this.date = LocalDate.now();
    }

    /**
     * Retrieves the transaction amount.
     *
     * @return The amount of the transaction.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Retrieves the date when the transaction occurred.
     *
     * @return The date of the transaction.
     */
    public LocalDate getDate() {
        return date;
    }
}
