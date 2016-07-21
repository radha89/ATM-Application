package sheridan;


/**
 *
 * @author radhabhambwani
 */
public class SavingsAccount extends Account {
    

    /**
     * Changes the annual interest rate
     * @param annualInterestRate - the new annual interest rate as an actual percentage (e.g. 4.5% would be passed in as 4.5)
     * @throws IllegalArgumentException - if the new annual interest rate is less than 3 percent
     */
    @Override
    public void setAnnualInterestRate(double annualInterestRate)
    {
        if (annualInterestRate < 3)
        {
            throw new IllegalArgumentException("Invalid annual interest. Interest rate has to be at least 3%. Cannot set the annual interest");
        }

        _annualInterestRate = annualInterestRate;
    }

    /**
     * Deposits the given amount with $0.50 for every dollar deposited. Performs all the error handling if the amount is negative
     * @param amount - the amount to be deposited
     * @return the new account balance AFTER the new amount was deposited
     * @throws IllegalArgumentException if the amount is negative
     */
    @Override
    public double deposit(double amount)
    {
        double bankDeposit = 0;
        
        //check if the deposit is possible (for invalid amounts)
        if (amount < 0)
        {
            throw new IllegalArgumentException("Invalid amount provided. Cannot deposit a negative amount.");
        }
        
        //for every dollar amount deposited, an addition $0.50 is to be deposited
        bankDeposit = amount * 0.5;
        
        //adjust amount accprdingly
        amount += bankDeposit;
                
        //perform the deposit
        _balance += amount;

        return _balance;
    }
    
}
