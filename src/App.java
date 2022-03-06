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
        Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.setLevel(Level.ALL);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        log.addHandler(handler);
        log.setUseParentHandlers(false);
        boolean b = false; // flag to exit the game
        int compare = number(); // generate random number
        int upper = 9999;
        int lower = 0;
        // main program loop
        do {
            int i = scan.nextInt(); // scan user input via scanner object (int)
            // if-else block to compare random number and user input
            if (i == compare) {
                log.finest("Du hast die richtige Nummer erraten !");
                b = true;
            } else if (i < lower) {
                throw new IllegalArgumentException(" Deine Eingabe liegt unter dem zugelassenen Bereich !");
            } else if (i >= upper) {
                throw new IllegalArgumentException(" Deine Eingabe liegt über dem zugelassenen Bereich !");
            } else if (i < compare) {
                log.warning("Deine Nummer ist zu klein !");
            } else {
                log.warning("Deine Nummer ist zu groß !");
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
                Willkommen ! \n
                Errate eine Zahl zwischen 0 und 9999 \n
                0 ist inklusive, 9999 ist  exklusiv \n
                nur positive ganze Zahlen erlaubt ! \n
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
    public static int number() {

        Random r = new Random();
        int low = 0;
        int high = 9999;
        return r.nextInt(high - low) + low;
    }

}
