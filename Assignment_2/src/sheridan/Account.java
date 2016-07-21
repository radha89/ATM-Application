package sheridan;

import java.util.Date;

/**
 * Represents a bank account with an ID, balance, annual interest rate.
 * @author Magdin Stoica
 * @version 2.0
 * */
public class Account
{

    /**
     * The account ID. Note that this is not an index in an array but an ID
     */
    protected int _id;
    /**
     * The current balance in the account
     */
    protected double _balance;
    /**
     * The annual interest for this account stored as a percentage
     * 4.5% would be stored as 4.5. When used in calculations it must be divided by 100
     */
    protected double _annualInterestRate;
    /**
     * The date the account was created
     */
    protected Date _dateCreated;

    /**
     * Creates a default account (new Account())
     *
     */
    public Account()
    {
        //NOTE: you can call another constructor (chaining constructors) which allows you to
        //implement the initialization code in one place but call it with different parameters
        this(0);
    }

    /**
     * Create an account with the a given id (new Account(1122))
     * @param id - the ID of the account
     */
    public Account(int id)
    {
        this(id, 0.0, 0.0);
    }

    /**
     * Creates an account with the given ID, balance and annual interest (e.g. new Account(345, 3000, 2.3))
     * @param id - the ID of the account
     * @param balance - the initial balance
     * @param annInterestRate - the annual interest, assumed to be given in percentage
     */
    public Account(int id, double balance, double annInterestRate)
    {
        _id = id;
        _balance = balance;
        _annualInterestRate = annInterestRate; 
        _dateCreated = new Date();

    }

    /*
     * Changes the ID of the account
     */
    public void setId(int id)
    {
        if(id <= 0) 
        {
            throw new IllegalArgumentException("Invalid Id. Id cannot be a non-negative integer or a zero. Please choose another id");
        }
        _id = id;
    }

    /**
     * Returns the ID of the account
     * @return account ID
     */
    public int getId()
    {
        return _id;
    }

    /**
     * Change the account balance
     * @param balance - the new balance
     * @throws IllegalArgumentException if the new balance is negative
     */
    public void setBalance(double balance)
    {
        if (balance < 0)
        {
            throw new IllegalArgumentException("Invalid balance. Please use only positive numbers. Cannot set balance");
        }

        _balance = balance;
    }

    /**
     * Returns the current balance in the account
     * @return - the account balance
     */
    public double getBalance()
    {
        return _balance;
    }

    /**
     * Changes the annual interest rate
     * @param annualInterestRate - the new annual interest rate as an actual percentage (e.g. 4.5% would be passed in as 4.5)
     * @throws IllegalArgumentException - if the new annual interest rate is negative
     */
    public void setAnnualInterestRate(double annualInterestRate)
    {
        if (annualInterestRate < 0)
        {
            throw new IllegalArgumentException("Invalid annual interest. Please use only positive numbers. Cannot set the annual interest");
        }

        _annualInterestRate = annualInterestRate;
    }

    /**
     * Returns the annual interest rate
     * @return annual interest
     */
    public double getAnnualInterestRate()
    {
        return _annualInterestRate;
    }

    /**
     * Returns the monthly interest rate calculated from the annual one
     * NOTE: this is a read-only property since there is no way to set the monthly interest rate
     * @return - monthly interest rate
     */
    public double getMonthlyInterestRate()
    {
        return _annualInterestRate / 12;
    }
    
    public double getMonthlyInterestRateValue()
    {
        return getMonthlyInterestRate() / 100;
    }
    
    /**
     * This methods calculates and returns the monthly interest given the balance of the account. Note this is
     * different from getMonthlyInterestRate which only returns the monthly rate as calculated from the annual one
     * @return 
     */
    public double getMonthlyInterest()
    {
        return _balance * getMonthlyInterestRateValue();
    }

    /**
     * Establishes the date the account was created
     * @param dateCreated - the date the account was created
     */
    public void setDateCreated(Date dateCreated)
    {
        _dateCreated = dateCreated;
    }

    /**
     * Returns the date the account was created
     * @return the date the account was created
     */
    public Date getDateCreated()
    {
        return _dateCreated;
    }

    /**
     * Withdraws the given amount from the account. Performs all the error handling if the amount is negative or
     * if the withdrawal would result into a negative balance
     * @param amount - the amount to be withdrawn
     * @return the new account balance AFTER the amount was withdrawn
     * @throws IllegalArgumentException if the amount is negative or if there are insufficient funds
     */
    public double withdraw(double amount)
    {
        //check if the withdrawal is possible (for invalid amounts or balance overruns)
        if (amount < 0)
        {
            throw new IllegalArgumentException("Invalid amount provided. Cannot withdraw a negative amount.");
        }

        if (amount > _balance)
        {
            throw new IllegalArgumentException("Insufficient funds. Cannot withdraw the provided amount.");
        }

        //perform the withdrawal
        _balance -= amount;

        return _balance;
    }

    /**
     * Deposits the given amount from the account. Performs all the error handling if the amount is negative
     * @param amount - the amount to be deposited
     * @return the new account balance AFTER the amount was deposited
     * @throws IllegalArgumentException if the amount is negative
     */
    public double deposit(double amount)
    {
        //check if the deposit is possible (for invalid amounts)
        if (amount < 0)
        {
            throw new IllegalArgumentException("Invalid amount provided. Cannot deposit a negative amount.");
        }

        //perform the deposit
        _balance += amount;

        return _balance;
    }
    
     /** toString method to print Account information
     *
     * @return
     */
    public String toString() {
        return "Account id = " + _id + " , balance = " + _balance
                + " , annual interest rate = " + _annualInterestRate
                + " , date created = " + _dateCreated;
    }
}
