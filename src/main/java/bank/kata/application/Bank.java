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

        accountService.deposit( 10000);
        accountService.withdraw( 500);
        accountService.deposit( 1000);
        accountService.deposit( 2000);
        accountService.withdraw( 100);
        accountService.withdraw( 200);
        accountService.withdraw( 300);
        accountService.withdraw( 500);

        accountService.printStatement();
    }

}
