

public class Controller {

    public static final int EXIT = 9;
    public static int commandString;
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
        while(commandString != EXIT)
        {
            Command.processCommand(player, commandString);
            Presenter.displayPlayArea();
            commandString = Command.getCommand();
        }

        

        //Accessing a very specific suit - should not be done
        // Deck.heartSuits[0].setState(true);
        // Presenter.displayUPFacingCards();
    }
}
