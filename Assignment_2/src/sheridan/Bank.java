package sheridan;

/**
 * Represents the bank entity responsible for holding and managing accounts
 * (like a real-world bank)
 *
 * @author Magdin Stoica
 * @version 2.0
 */
public class Bank {

    /**
     * The list of accounts in the bank. Currently supports only 100 accounts
     * from 0 to 99.
     */
    private Account[] _accountList = new Account[100];

    /**
     * keep track of all accounts added to the _accountList
     */
    private int _accountCount = 0;

    /**
     * Default constructor. Will automatically create the required accounts
     */
    public Bank() {

    }

    /**
     * validates account to let user know if the account id matches 
     * the if of an existing account
     *  
     * @param accountId 
     */
    public void validateAccount(int accountId)
    {
        for(int iAccount = 0; iAccount < _accountCount; iAccount++) {
            if(accountId == _accountList[iAccount].getId())
            {
                throw new IllegalArgumentException("Account with this id already exists. Please enter a valid id.");
            }
        }
    }

    /**
     * Returns the account with the given ID or null if no account with that ID
     * can be found
     *
     * @param acctId - the ID of the account to return
     * @return the account object with the given ID
     */
    public Account findAccount(int acctId) 
    {
        //go through all the accounts until one is found with the given ID
        for (int iAccount = 0; iAccount < _accountCount; iAccount++) 
        {
            if (_accountList[iAccount].getId() == acctId) 
            {
                return _accountList[iAccount];
            }
        }

        //if the program got here it means there was no account with the given ID
        return null;
    }

    /**
     * @param account
     * Create new accounts and store them in _accountList
     */
    public void addAccount(Account account) 
    {
        //add account to _accountList
        _accountList[_accountCount] = account;                     

        //update account count after storing each account
        _accountCount++;
    }
}
