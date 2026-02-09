package test;

import customer.Account;
import customer.Unlimited;
import customer.Alacarte;

public class TestAccount {
    public static void main(String[] args) {
        Account account1 = new Unlimited();
        Account account2 = new Alacarte();

        boolean successful = (account1.getAccountNumber() == 1 ) && (account2.getAccountNumber() == 2);
        
        if (!successful){
            System.out.println("FAIL!!! Expected account numbers 1 and 2 but got"+ account1.getAccountNumber()+ "and"+ account2.getAccountNumber());
            System.exit(1);
        }

    }
    
}