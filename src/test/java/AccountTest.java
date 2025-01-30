import bank.kata.beans.Transaction;
import bank.kata.repository.TransactionRepository;
import bank.kata.service.Account;
import bank.kata.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AccountTest {
    private TransactionRepository transactionRepository;
    private Account account;

    @BeforeEach
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        account = new Account(transactionRepository);
    }

    @Test
    public void testDeposit() {
        account.deposit(1000);
        verify(transactionRepository, times(1)).saveTransaction(1000);
    }

    @Test
    public void testWithdraw() {
        account.withdraw(500);
        verify(transactionRepository, times(1)).saveTransaction(-500);
    }

    @Test
    public void testPrintStatement() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1000));
        transactions.add(new Transaction(2000));
        transactions.add(new Transaction(-500));

        when(transactionRepository.findAllTransactions()).thenReturn(transactions);

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        account.printStatement();

        // Restore normal System.out
        System.setOut(System.out);

        // Verify transactions were fetched
        verify(transactionRepository, times(1)).findAllTransactions();

        // Get the printed output
        String output = outContent.toString();

        // Normalize numbers by removing non-breaking spaces
        output = output.replace("\u202F", ""); // Replaces thin space if present

        // Check that output contains expected numbers
        assertTrue(output.contains("1000"));
        assertTrue(output.contains("2000"));
        assertTrue(output.contains("-500"));
    }

}
