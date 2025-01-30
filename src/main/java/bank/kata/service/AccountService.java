package bank.kata.service;

import java.math.BigDecimal;

/**
 * @author Hamza Saybari
 **/
public interface AccountService {

     void deposit(int amount);
     void withdraw(int amount);
     void printStatement();

}
