package bank.kata.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Hamza Saybari
 **/


public class Transaction implements Serializable {
    private final int amount;
    private final LocalDate date;

    public Transaction(int amount) {
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
