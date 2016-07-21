package sheridan;

/**
 * Test harness for the Account class. Note that this test harness uses asserts as well as print statements. You were not required
 * to use asserts of course but I have shown it in here because it is a great technique that all professional developers employ,
 * a technique called "Programming your assumptions". To read more about using asserts see http://docs.oracle.com/javase/7/docs/technotes/guides/language/assert.html
 * To learn more about using and developing test harness research the JUnit framework
 * @author Magdin Stoica
 * @version 2.0
 *
 */
public class TestApplication
{

    /** Starts the test. Used when running/debugging with the AtmApplicationTest configuration
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            //create the test account per the assignment requirements
            Account testAccount = new Account(1122, 20000, 4.5);

            //print initial properties
            System.out.printf("Account ID: %d\n", testAccount.getId());
            System.out.printf("Initial Balance: %.2f\n", testAccount.getBalance());
            System.out.printf("Annual Interest rate: %.3f\n", testAccount.getAnnualInterestRate());
            System.out.printf("Monthly Intereste rate: %.3f\n", testAccount.getMonthlyInterestRate());

            //test account withdraw
            System.out.println("Withdrawing 2,500$...");

            double newBalance = testAccount.withdraw(2500);

            //See http://docs.oracle.com/javase/7/docs/technotes/guides/language/assert.html
            //For asserts to work they need to be enabled in the Project Properties\Arguments -> VM Arguments -> -enableassertions or -ea
            //Assertions should only be enabled in debugging builds
            assert (newBalance == (20000 - 2500));
            assert (newBalance == testAccount.getBalance());

            System.out.printf("New Balance: %.2f\n", newBalance);

            //test account deposit
            System.out.println("Depositing 3000...");
            newBalance = testAccount.deposit(3000);

            //Notice that through the use of assertions one does not have to inspect the output to know that the test succeeded or not.
            //When asserts fail they end up in AssertException being through with a message with the line number where the assertions happen
            assert (newBalance == (20000 - 2500 + 3000));
            assert (newBalance == testAccount.getBalance());

            System.out.printf("The account was created on %s, holds a balance of %.2f and has a monthly interest of %.3f.\n", 
                     testAccount.getDateCreated().toString(),
                     testAccount.getBalance(),
                     testAccount.getMonthlyInterest());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
