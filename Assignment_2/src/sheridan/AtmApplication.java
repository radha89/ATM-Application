package sheridan;

/**
 * The main class of the program. Used when running the AtmApplicationMain configuration
 * @author Magdin Stoica
 * @version 2.0
 *
 */
public class AtmApplication
{

    /**
     * The main method that starts the program
     * @param args - not used
     */
    public static void main(String[] args)
    {
        try
        {
            AtmApplication app = new AtmApplication();
            app.run();
        }
        catch (Exception e)
        {
            System.out.println("An error occurred. Please contact your ... administrator.");
        }
    }

    /**
     * Truly the main method of the application object. Invoked as soon as an application object is created.
     * Will create a predefined bank and start an ATM
     */
    private void run()
    {
        Bank bank = new Bank();
        Atm atm = new Atm(bank);
        atm.start();
    }
}
