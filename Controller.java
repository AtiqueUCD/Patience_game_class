

public class Controller {
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

        

        //Accessing a very specific suit - should not be done
        // Deck.heartSuits[0].setState(true);
        // Presenter.displayUPFacingCards();
    }
}
