public class Controller {

    public static final String EXIT_U = "E";
    public static final String EXIT_L = "e";
    public static String commandString;
    public static void main(String[] args)
    {
        // Deck player = new Deck();

        PlayArea player = new PlayArea();
        Score playerScore = new Score();

        player.initiateSuit();
        player.initiateDeck();
        player.shuffleDeck();
        player.setupPlayArea();
        // Presenter.displayDeck();
        

        player.initPlayDeck();
        Presenter.displayPlayArea(playerScore);

        System.out.print("Enter D for Draw or E for Exit -> ");
        commandString = Command.getCommand();
        String Alphabet = Command.separateAlphabets(commandString);
        String Number = Command.separateNumbers(commandString);

        while( (!Alphabet.equals(EXIT_U)) && (!Alphabet.equals(EXIT_L)) )
        {
            Command.processCommand(player, Number, Alphabet, playerScore);
            Presenter.displayPlayArea(playerScore);
            System.out.print("Enter D for Draw or E for Exit -> ");
            commandString = Command.getCommand();
            Alphabet = Command.separateAlphabets(commandString);
            Number = Command.separateNumbers(commandString);
        }
        System.out.println("EXIT.");
    }
}
