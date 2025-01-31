package bank.kata.service;

import bank.kata.beans.Transaction;
import bank.kata.repository.TransactionRepository;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
public class Account implements AccountService{
    private TransactionRepository transactionRepository;
    private Properties properties = new Properties();
    private DateTimeFormatter dateFormat;

    /**
     * Constructs an {@code Account} instance, initializes the transaction repository,
     * and loads message properties from the `messages.properties` resource file.
     *
     * @param transactionRepository The repository for managing transactions.
     * @throws RuntimeException If the `messages.properties` file is not found or fails to load.
     * @author Hamza saybari
     */
    public Account( TransactionRepository transactionRepository) {
        // initialize the transactionRepository
        this.transactionRepository = transactionRepository;
        // Load messages.properties output config file and fill the properties attribute
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("messages.properties")) {
            if (input == null) {
                throw new IOException("Unable to find messages.properties resource file");
            } else {
                this.properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount The amount to be deposited. Must be a positive integer.
     */
    @Override
    public void deposit(int amount) {
        transactionRepository.saveTransaction(amount);
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount The amount to be withdrawn. Must be a positive integer.
     *               The method internally stores it as a negative transaction.
     */
    @Override
    public void withdraw(int amount) {
        transactionRepository.saveTransaction(-amount);
    }

    /**
     * Prints the account statement, showing all transactions in order,
     * along with their corresponding balances.
     * The output format is determined by the messages stored in the `messages.properties` file.
     */
    @Override
    public void printStatement() {
        System.out.println(getText("statement.header"));
        transactionRepository.findAllTransactions()
                .stream()
                .reduce(0, (balance, transaction) -> {
                    int newBalance = balance + transaction.getAmount();
                    System.out.println(formatTransaction(transaction, newBalance));
                    return newBalance;
                }, Integer::sum);
    }
    /**
     * Formats a transaction entry into a readable statement row.
     *
     * @param transaction The transaction to be formatted.
     * @param balance     The current balance after applying this transaction.
     * @return A formatted transaction string.
     */
    public String formatTransaction(Transaction transaction, int balance) {
        return MessageFormat.format(
                getText("statement.row"),
                dateFormat.format(transaction.getDate()),
                transaction.getAmount(),
                balance
        );
    }

    /**
     * Retrieves a localized message from the properties file.
     *
     * @param key The key corresponding to the message in `messages.properties`.
     * @return The localized message text, or {@code null} if the key is not found.
     */
    public String getText(String key) {
        return properties.getProperty(key);
    }
}