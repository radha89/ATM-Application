package sheridan;

import java.util.Scanner;

/**
 * The Atm class representing an ATM machine. The class displays and performs the the account management functions
 * on a given bank account: checking balance, withdrawing and depositing money
 * @author Magdin Stoica
 * @version 2.0
 *
 */
public class Atm
{

    /**
     * The bank this ATM object is working with
     */
    private Bank _bank;
    /**
     * Input scanner for reading menu options, account IDs and amounts
     */
    private Scanner _in = new Scanner(System.in);
    /**
     * MAIN MENU options
     */
    private static final int CREATE_ACCOUNT_OPTION = 1;
    private static final int SELECT_ACCOUNT_OPTION = 2;
    private static final int EXIT_ATM_APPLICATION_OPTION = 3;
    /**
     * ACCOUNT MENU options
     */
    private static final int CHECK_BALANCE_OPTION = 1;
    private static final int WITHDRAW_OPTION = 2;
    private static final int DEPOSIT_OPTION = 3;
    private static final int EXIT_ACCOUNT_OPTION = 4;
    /**
     * CREATE NEW ACCOUNT MENU options
     */
    private static final int CHEQUINGS_ACCOUNT = 1;
    private static final int SAVINGS_ACCOUNT = 2;
    private static final int EXIT_NEWACCOUNT_OPTION = 3;
    
  
    
    /**
     * Constructor that initializes the bank the ATM is working with
     * @param bank
     */
    public Atm(Bank bank)
    {
        _bank = bank;
    }

    /**
     * Starts the ATM program by displaying the required user options. User navigates the menus
     * managing their accounts
     */
    public void start()
    {
        //keep displaying the menu until the user chooses to exit the application
        while (true)
        {
            //display the main menu and perform the main actions depending on the user's choice
            switch (showMainMenu())
            {
                case CREATE_ACCOUNT_OPTION:
                    createAccount();    
                    break;
                    
                case SELECT_ACCOUNT_OPTION:
                    Account account = selectAccount();
                    manageAccount(account);
                    break;

                case EXIT_ATM_APPLICATION_OPTION:
                    return;

                default:
                    //go again when the user choose 4 instead of 1, 2 or 3
                    System.out.println("Please enter a valid menu option");
                    break;
            }
        }
    }

    /**
     * Displays the main ATM menu and ensure the user picks an option. Handles invalid input but doesn't check
     * that the menu option is one of the displayed ones.
     * NOTE: you were not required to present two menus but the application works better like that
     * @return the option selected by the user
     */
    private int showMainMenu()
    {
        while (true)
        {
            try
            {
                System.out.print("\nMain Menu\n\n1: Create Account\n2: Select Account\n3: Exit\nEnter a choice: ");
                return Integer.parseInt(_in.nextLine());
            }
            catch (NumberFormatException e)
            {
                //if the user enters "abc" instead of a number
                System.out.println("Please enter a valid menu option.");
            }
        }
    }

    /**
     * Displays the ACCOUNT menu that allows the user to perform account operations. Handles invalid input but doesn't check
     * that the menu option is one of the displayed ones.
     * @return the option selected by the user
     */
    private int showAccountMenu()
    {
        while (true)
        {
            try
            {
                System.out.print("\nAccount Menu\n\n1: Check Balance\n2: Withdraw\n3: Deposit\n4: Exit\n\nEnter a choice: ");
                return Integer.parseInt(_in.nextLine());
            }
            catch (NumberFormatException e)
            {
                //if the user enters "abc" instead of a number
                System.out.println("Please enter a valid menu option.");
            }
        }
    }
    
    /**
     * Displays the CREATE NEW ACCOUNT Menu that allow the user to open chequings and savings account or exit.
     * 
     * @return 
     */
    private int showCreateAccountMenu()
    {
        while(true)
        {
            try
            {
                System.out.print("\nCreate New Account Menu\n1: Chequings\n2: Savings\n3: Exit\n\nEnter a choice: ");
                return Integer.parseInt(_in.nextLine());
            }
            catch (NumberFormatException e)
            {
                //if the user enters letters instead of a number
                System.out.println("Please enter a valid menu option.");
            }
        }
    }
    
    /** Performs creating new Account functions such as creating a new account object
     * of Chequings or Savings type. Also creates sub-element of accounts such as id,
     * creating balance, annual interest rate. After all that it adds the appropriate 
     * account to the appropriate spot in the array in order. Also displays information
     * about the transaction. 
     * 
     */
    private void createAccount() 
    {
        while (true) 
        {
            switch (showCreateAccountMenu()) 
            {
                case CHEQUINGS_ACCOUNT:
                    ChequingAccount newChequingAccount = new ChequingAccount();
                    createAccountId(newChequingAccount);
                    createAccountBalance(newChequingAccount);
                    createAccountAnnualInterestRate(newChequingAccount);
                    _bank.addAccount(newChequingAccount);
                    System.out.println("\nChequings Account has been created!");
                    System.out.println(newChequingAccount);
                    break;

                case SAVINGS_ACCOUNT:
                    SavingsAccount newSavingsAccount = new SavingsAccount();
                    createAccountId(newSavingsAccount);
                    createAccountBalance(newSavingsAccount);
                    createAccountAnnualInterestRate(newSavingsAccount);
                    _bank.addAccount(newSavingsAccount);
                    System.out.println("\nSavings account has been created!");
                    System.out.println(newSavingsAccount);
                    break;

                case EXIT_NEWACCOUNT_OPTION:
                    return;

                default:
                    System.out.println("Please enter a valid menu option.");
            }
        }
    }

    /**
     * validates id and created new account's id accordingly
     * 
     * @param account 
     */
    private void createAccountId(Account account)
    {
        while (true)
        {
            try
            {
                System.out.print("Create account ID: ");
                String accountId_in = _in.nextLine();
                if(accountId_in.isEmpty() == false) 
                {
                    int accountId = Integer.parseInt(accountId_in);
                    //ensure entered accountId doesn't already exist
                    _bank.validateAccount(accountId);
                    account.setId(accountId);
                    
                }
                
                return;
            }
            catch (NumberFormatException e)
            {
                //if letters are inputted, display error
                System.out.println("Invalid entry. Please enter numbers only.");
            }   
            catch (IllegalArgumentException e) 
            {
                //if account id already exists, display error message
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * validates and initialize's new account's balance
     * 
     * @param account 
     */
    private void createAccountBalance(Account account) 
    {
        while (true)
        {
            try
            {
                System.out.print("Set Initial Balance: ");
                String initialBalance_in = _in.nextLine();
                if(initialBalance_in.isEmpty() == false) 
                {
                    double initialBalance = Double.parseDouble(initialBalance_in);
                    account.deposit(initialBalance);
                    
                }
                return;
            }
            
            catch (NumberFormatException e)
            {
                //if letters are inputted, display error
                System.out.println("Invalid entry. Please enter numbers only.");
            }
            catch (IllegalArgumentException e) 
            {
                //if initial balance entered is invalid, display error message
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * validates and creates annualInterestRate according to interest rate specifications
     * 
     * @param account 
     */
    private void createAccountAnnualInterestRate(Account account)
    {
        while (true)
        {
            try
            {
                System.out.print("Set Annual Interest Rate: ");
                String annualInterestRate_in = _in.nextLine();
                if(annualInterestRate_in.isEmpty() == false) 
                {
                    double annualInterestRate = Double.parseDouble(annualInterestRate_in);
                    account.setAnnualInterestRate(annualInterestRate);
                }  
                return;
            }
             catch (NumberFormatException e)
            {
                //if letters are inputted, display error
                System.out.println("Invalid entry. Please enter number only.");
            }
            catch (IllegalArgumentException e) 
            {
                //if initial balance entered is invalid, display error message
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prompts the user to select an account, finds the account in the bank and returns it to the caller.
     * @return a valid existing account
     */
    private Account selectAccount()
    {
        while (true)
        {
            try
            {
                System.out.print("Please enter your account ID: ");
                int accountId = Integer.parseInt(_in.nextLine());
                Account account = _bank.findAccount(accountId);
                if (account != null)
                {
                    //an account with the ID the user requested was found
                    return account;
                }
                else
                {
                    //the user entered a valid account number but there is no such account
                    System.out.println("The account was not found. Please select another account.");
                }

            }
         
            catch (NumberFormatException e)
            {
                //The user entered an invalid (e.g. abc) account ID
                System.out.println("Please enter a valid account id (1..10 or 1122)");
            }
        }
    }

    /**
     * Performs the account management functions as requested by the user through the Account Menu
     * NOTE how the actual functionality is not in this method but in smaller methods (divide and conquer)
     * This method serves as a "dispatcher"
     * @param account - the account being managed
     */
    private void manageAccount(Account account)
    {
        while (true)
        {
            switch (showAccountMenu())
            {
                case CHECK_BALANCE_OPTION:
                    checkBalance(account);
                    break;

                case WITHDRAW_OPTION:
                    widthdraw(account);
                    break;

                case DEPOSIT_OPTION:
                    deposit(account);
                    break;

                case EXIT_ACCOUNT_OPTION:
                    return;

                default:
                    System.out.println("Please enter a valid menu option");
                    break;
            }
        }

    }

    /**
     * Prompts the user for an amount and performs the deposit. Handles any errors related to incorrect amounts
     * @param account - the account in which the amount is to be deposited
     */
    private void deposit(Account account)
    {
        while (true)
        {
            try
            {
                System.out.print("Please enter an amount to deposit or type [ENTER] to exit: ");
                String input = _in.nextLine();

                //test for empty input in case the user pressed [ENTER] because they wanted to give up on depositing money
                if (input.isEmpty() == false)
                {
                    double amount = Double.parseDouble(input);

                    //NOTE: the account itself is responsible for checking the amount and raising any errors if the deposit
                    //is not possible
                    account.deposit(amount);
                }

                //break from the infinite loop now that the deposit was done or user entered nothing
                return;
            }
            catch (NumberFormatException e)
            {
                //the user must have entered and invalid (e.g. "abc") amount
                System.out.println("Invalid entry. Please enter a number for your amount.");
            }
            catch (IllegalArgumentException e)
            {
                //the account must have refused to deposit the entered amount. The reason is in the exception object
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Prompts the user for an amount and performs the withdrawal. Handles any errors related to incorrect amounts
     * @param account - the account in which the amount is to be withdrawn
     */
    private void widthdraw(Account account)
    {
        while (true)
        {
            try
            {
                System.out.print("Please enter an amount to withdraw or type [ENTER] to exit: ");
                String input = _in.nextLine();
                if (input.isEmpty() == false)
                {
                    double amount = Double.parseDouble(input);

                    //NOTE: the account itself is responsible for checking the amount and raising any errors if the deposit
                    //is not possible like negative amounts and balance overruns
                    account.withdraw(amount);
                }

                //break from the infinite loop now that the deposit was done or user entered nothing
                return;
            }
            catch (NumberFormatException e)
            {
                //the user must have entered and invalid (e.g. "abc") amount
                System.out.println("Invalid entry. Please enter a number for your amount.");
            }
            catch (IllegalArgumentException e)
            {
                //the account must have refused to withdraw the entered amount. The reason is in the exception object
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints the balance in the given account
     * @param account - the account for which the balance is printed
     */
    private void checkBalance(Account account)
    {
        System.out.printf("The balance is %.2f\n", account.getBalance());

    }
}

