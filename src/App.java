/*
@Author: Felix Schumacher
@Last Access: 28.02.2022
@Description:
    A small number guessing game using the command line

*/

//Imports
import java.util.Random;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {


    int upper;
    int lower;

    public App(int up, int low){
        upper = up;
        lower = low;
    }


    /*
     * Name: main
     * parameters: args type: String[]
     * return value: void
     * description:
     * main program
     */
    public static void main(String[] args) throws Exception {
        hello(); // intro message
        Scanner scan = new Scanner(System.in); // set up scanner object
        System.out.println("Enter upper boundary: ");
        int u = scan.nextInt();
        System.out.println("Enter lower boundary: ");
        int l = scan.nextInt();
        App start = new App(u,l);
        Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); // create global logger object
        log.setLevel(Level.ALL); // log all levels that are available
        Handler handler = new ConsoleHandler(); // create a handler object
        handler.setLevel(Level.ALL); // handle all levels that are available
        log.addHandler(handler); // add handler to logger
        log.setUseParentHandlers(false); // deactivate parent dependency
        boolean b = false; // flag to exit the game
        int compare = number(l, u); // generate random number
        // main program loop
        do {
            System.out.println("Enter a number: ");
            int i = scan.nextInt(); // scan user input via scanner object (int)
            // if-else block to compare random number and user input
            if (i == compare) {
                log.finest("You found the correct number !");
                scan.close();
                b = true;
            } else if (i < start.lower) {
                throw new IllegalArgumentException(" Your input is below the allowed range  !");
            } else if (i >= start.upper) {
                throw new IllegalArgumentException(" Your input is above the allowed range !");
            } else if (i < compare) {
                log.warning("Your number is too small !");
            } else {
                log.warning("Your number is too big !");
            }
        } while (!b);

        scan.close();
    }

    /*
     * Name: intro
     * parameters: none
     * return value: void
     * description:
     * generates a welcome message for the user.
     */
    public static void hello() {
        String i = """
                Welcome ! \n
                Enter a number for your upper boundary,  \n
                Enter a number for your lower boundary,  \n
                lower is inclusive, upper is exclusive \n
                only positive integers allowed ! \n
                """;

        System.out.println(i);

    }

    /*
     * Name: number
     * parameters: none
     * return value: int
     * description:
     * generates a random number between high and low,
     * excludes high value and includes low value
     */
    public static int number(int l, int h) {

        Random r = new Random();
        int low = l;
        int high = h;
        return r.nextInt(high - low) + low;
    }

}
