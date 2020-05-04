/*
 * Divyesh Johri
   1/29/20
 */
package savingsaccount;

public class SavingsAccountTester {
    public static void main(String[]args){
        //Initializing objects and setting static variable
        SavingsAccount saver1 = new SavingsAccount(2000);
        SavingsAccount saver2 = new SavingsAccount(3000);
        SavingsAccount.setInterestRate(.04);
        
        //Printing out results with correct formatting
        System.out.println("Savings Account Balances");
        System.out.format("%s\t\t%s\t\t%s%n","Month","Saver1","Saver2");
        
        for(int i = 0; i < 12; i++){    // First 12 months
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            System.out.format("%d\t\t", i + 1);
            System.out.format("%.2f\t\t", saver1.savingsBalance);
            System.out.format("%.2f%n", saver2.savingsBalance);
        }
        
        SavingsAccount.setInterestRate(.05);    //New interest rate for 13th month
        
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.format("%d\t\t", 13);
        System.out.format("%.2f\t\t", saver1.savingsBalance);
        System.out.format("%.2f", saver2.savingsBalance);
    }
}
