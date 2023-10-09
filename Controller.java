import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public static final int EXIT = 9;
    public static String commandString;
    public static void main(String[] args)
    {
        // Deck player = new Deck();

        PlayArea player = new PlayArea();
        player.initiateSuit();
        player.initiateDeck();
        player.shuffleDeck();
        player.setupPlayArea();
        // Presenter.displayDeck();
        

        player.initPlayDeck();
        Presenter.displayPlayArea();

        commandString = Command.getCommand();
        String Alphabet = Command.separateAlphabets(commandString);
        String Number = Command.separateNumbers(commandString);
        System.out.println("CMD : " + commandString);
        System.out.println("CMD alpha: " + Alphabet.length());
        System.out.println("CMD num: " + Number.length());
        Command.processCommand(player, Alphabet, Number);


        // while(commandString != EXIT)
        // {
        //     Command.processCommand(player, commandString);
        //     Presenter.displayPlayArea();
        //     commandString = Command.getCommand();
        // }
    }
}
