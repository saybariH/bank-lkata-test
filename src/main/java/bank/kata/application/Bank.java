package bank.kata.application;

import bank.kata.repository.TransactionRepository;
import bank.kata.service.Account;
import bank.kata.service.AccountService;

/**
 * @author Hamza Saybari
 **/
public class Bank {


    public static void main(String[] args){

        AccountService accountService = new Account(new TransactionRepository());

        accountService.deposit( 2231);
        accountService.withdraw( 131);
        accountService.printStatement();

    }

}
