package sheridan;


/**
 *
 * @author radhabhambwani
 */
public class ChequingAccount extends Account {


    /**
     * Changes the annual interest rate
     * @param annualInterestRate - the new annual interest rate as an actual percentage (e.g. 4.5% would be passed in as 4.5)
     * @throws IllegalArgumentException - if the new annual interest rate is negative
     */
    @Override
    public void setAnnualInterestRate(double annualInterestRate)
    {
        //interest rate for chequing account is required to be 1 percent max
        if (annualInterestRate > 1 || annualInterestRate < 0)
        {
            throw new IllegalArgumentException("Invalid annual interest. Interest rate has to be more than 0 and maximum 1%. Cannot set the annual interest");
        }
        _annualInterestRate = annualInterestRate;
    }

    
    /**
     * Withdraws the given amount from the account. Performs all the error handling
     * if the withdrawal would result into a negative balance of less than -500
     * @param amount - the amount to be withdrawn
     * @return the new account balance AFTER the amount was withdrawn
     * @throws IllegalArgumentException if the amount is negative or if there are insufficient funds
     */
    @Override
    public double withdraw(double amount)
    {
        //check if the withdrawal is possible (for invalid amounts or balance overruns)
        if (amount < 0)
        {
            throw new IllegalArgumentException("Invalid amount provided. Cannot withdraw a negative amount.");
        }

        //Overdraft limit of $500 so minimum _balance in account should be -500 for chequing account
        if (_balance - amount < -500)
        {
            throw new IllegalArgumentException("Insufficient funds. Cannot withdraw the provided amount. Overdraft of $500 maximum");
        }

        //perform the withdrawal
        _balance -= amount;

        return _balance;
    }
   
}
