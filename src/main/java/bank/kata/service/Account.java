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
    Properties properties = new Properties();
    private DateTimeFormatter dateFormat;
    private TransactionRepository transactionRepository;

    public Account( TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

        // Load the properties file
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("messages.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            } else {
                properties.load(input);  // Load properties correctly
            }
        } catch (IOException e) {
            e.printStackTrace();  // Properly handle IOException
        }
        this.dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    }

    public String formatTransaction(Transaction transaction, int balance) {
        return MessageFormat.format(
                getText("statement.row"),
                dateFormat.format(transaction.getDate()),
                transaction.getAmount(),
                balance
        );
    }

    public String getText(String key) {
        return properties.getProperty(key);
    }
    @Override
    public void deposit(int amount) {
        transactionRepository.saveTransaction(amount);
    }

    @Override
    public void withdraw(int amount) {
        transactionRepository.saveTransaction(-amount);
    }

    @Override
    public void printStatement() {
        System.out.println(getText("statement.header"));
        int balance = 0;
        for (Transaction transaction : transactionRepository.findAllTransactions()) {
            balance += transaction.getAmount();
            System.out.println(formatTransaction(transaction, balance));
        }
    }
}