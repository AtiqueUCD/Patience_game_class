
public class Presenter {


    public static void displayPlayArea(Score viewScore)
    {

        System.out.flush();
        for(int i = 2 ; i < 14 ; i++)
        {

            
            if(i >= 10)
            {
                System.out.print(Deck.__suits[i-10] + " -> ");
            }
            else if(i == 2)
            {
                System.out.print("P -> ");
            }else{
                System.out.print((i - Command.CMD_OFFSET)  + " -> ");
            }
            for(Cards j : PlayArea.playDeckStacks.get(i))
            {
                if(j.getFaceState())
                    System.out.print(j.getCardName() + " ");
                else
                    System.out.print("xx " + "\u001B[0m");
            }
            System.out.println("");
        }

        System.out.println("Score: " + viewScore.getScore());
    }

    public static void displayDeck()
    {
        System.out.flush();
        for(int i = 1; i <= 52; i++)
        {
            System.out.print(Deck.initDeck.get(i-1).getCardName() + " ");

            if(i % 13 == 0)
                System.out.print("\n");
        }
    }

    public static void displayCards()
    {
        System.out.println("Heart");
        for(Cards i : Deck.heartSuits)
        {
            System.out.print(i.getCardName() + " ");
        }
        
        System.out.println("\nDiamonds");
        for(Cards i : Deck.diamondsSuits)
        {
            System.out.print(i.getCardName() + " ");
        }
        
        System.out.println("\nSpades");
        for(Cards i : Deck.speadSuits)
        {
            System.out.print(i.getCardName() + " ");
        }
        
        System.out.println("\nClubs");
        for(Cards i : Deck.clubSuits)
        {
            System.out.print(i.getCardName() + " ");
        }
    }

    public static void displayUPFacingCards()
    {
        System.out.println("Showing FaceUp cards");
        System.out.println("\nHeart");
        for(Cards i : Deck.heartSuits)
        {
            if(i.getFaceState())
                System.out.print(i.getCardName() + " ");
            else
                System.out.print("xxx ");
        }
        
        System.out.println("\nDiamonds");
        for(Cards i : Deck.diamondsSuits)
        {
            if(i.getFaceState())
                System.out.print(i.getCardName() + " ");
            else
                System.out.print("xxx ");
        }
        
        System.out.println("\nSpades");
        for(Cards i : Deck.speadSuits)
        {
            if(i.getFaceState())
                System.out.print(i.getCardName() + " ");
            else
                System.out.print("xxx ");
        }
        
        System.out.println("\nClubs");
        for(Cards i : Deck.clubSuits)
        {
            if(i.getFaceState())
                System.out.print(i.getCardName() + " ");
            else
                System.out.print("xxx ");
        }
    }
}
