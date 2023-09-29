

public class Controller {
    public static void main(String[] args)
    {
        Deck player = new Deck();
        player.initiateSuit();
        player.initiateDeck();
        Presenter.displayDeck();//displayCards();
        

        //Accessing a very specific suit - should not be done
        // Deck.heartSuits[0].setState(true);
        // Presenter.displayUPFacingCards();
    }
}
