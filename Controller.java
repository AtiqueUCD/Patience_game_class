import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public static final String EXIT = "E";
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

        while(!Alphabet.equals(EXIT))
        {
            Command.processCommand(player, Number, Alphabet);
            Presenter.displayPlayArea();
            commandString = Command.getCommand();
            Alphabet = Command.separateAlphabets(commandString);
            Number = Command.separateNumbers(commandString);
        }
        System.out.println("EXIT.");
    }
}
