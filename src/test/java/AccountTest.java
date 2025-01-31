import bank.kata.beans.Transaction;
import bank.kata.repository.TransactionRepository;
import bank.kata.service.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@code Account} class.
 * This class follows a **Test-Driven Development (TDD)** approach to ensure the correctness
 * of deposit, withdrawal, and bank statement printing functionalities.
 *
 * Using **Mockito** to mock dependencies and **JUnit for assertions.
 *
 * @author Hamza Saybari
 */
public class AccountTest {

    /**
     * Mocked instance of the {@code TransactionRepository}.
     */
    private TransactionRepository transactionRepository;

    /**
     * The account service under test.
     */
    private Account account;

    /**
     * Sets up the test environment before each test method.
     * It initializes the mock repository and injects it into the {@code Account} instance.
     */
    @BeforeEach
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        account = new Account(transactionRepository);
    }

    /**
     * Tests that calling {@code deposit(int amount)} correctly stores the transaction.
     * The method should interact with {@code TransactionRepository} to save the deposit.
     */
    @Test
    public void testDeposit() {
        account.deposit(1000);
        verify(transactionRepository, times(1)).saveTransaction(1000);
    }

    /**
     * Tests that calling {@code withdraw(int amount)} correctly stores a negative transaction.
     * The method should interact with {@code TransactionRepository} to save the withdrawal.
     */
    @Test
    public void testWithdraw() {
        account.withdraw(500);
        verify(transactionRepository, times(1)).saveTransaction(-500);
    }

    /**
     * Tests the {@code printStatement()} method to ensure correct formatting of transactions.
     * This test captures console output and verifies that:
     * - The correct transactions are retrieved from the repository.
     * - The output contains the expected transaction values.
     */
    @Test
    public void testPrintStatement() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1000));
        transactions.add(new Transaction(2000));
        transactions.add(new Transaction(-500));

        // Mock transaction repository to return predefined transactions
        when(transactionRepository.findAllTransactions()).thenReturn(transactions);

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute statement printing
        account.printStatement();

        // Restore normal System.out
        System.setOut(System.out);

        // Verify that transactions were retrieved
        verify(transactionRepository, times(1)).findAllTransactions();

        // Get the printed output and normalize spacing issues
        String output = outContent.toString().replace("\u202F", "");

        // Validate that expected amounts are present in the statement output
        assertTrue(output.contains("1000"), "Statement should include deposit of 1000");
        assertTrue(output.contains("2000"), "Statement should include deposit of 2000");
        assertTrue(output.contains("-500"), "Statement should include withdrawal of 500");
    }
}
