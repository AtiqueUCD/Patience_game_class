

import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    final static String HEART = "\u2764";
    final static String DIAMOND = "\u2666";
    final static String SPADES = "\u2660";
    final static String CLUBS = "\u2663";

    final static String COLOR_RED = "\u001B[31m";
    final static String COLOR_YELLOW = "\u001B[33m";

    final static int TOTAL_CARDS_IN_DECK = 13;

    public static String[] __suits = new String[]{ HEART, SPADES, DIAMOND, CLUBS};

    public static ArrayList<Cards> heartSuits = new ArrayList<Cards>();
    public static ArrayList<Cards> diamondsSuits = new ArrayList<Cards>();
    public static ArrayList<Cards> speadSuits = new ArrayList<Cards>();
    public static ArrayList<Cards> clubSuits = new ArrayList<Cards>();

    public static ArrayList<Cards> initDeck = new ArrayList<Cards>();


    public void initiateSuit()
    {
        String tempStringNumString = "";

        for(String suit_type : __suits)
        {

            for(int i = 1; i <= TOTAL_CARDS_IN_DECK; i++)
            {

                if(i == 1)
                {
                    tempStringNumString = "A";
                }

                else if(i == 11)
                {
                    tempStringNumString = "J";
                }

                else if(i == 12)
                {
                    tempStringNumString = "Q";
                }

                else if(i == 13)
                {
                    tempStringNumString = "K";
                }

                else{
                    tempStringNumString = Integer.toString(i);
                }
                
                

                switch(suit_type)
                {
                    case HEART:
                        heartSuits.add(i-1, new Cards(tempStringNumString,HEART , COLOR_RED));
                    break;

                    case DIAMOND:
                        diamondsSuits.add(i-1, new Cards(tempStringNumString,DIAMOND , COLOR_RED));
                    break;

                    case SPADES:
                        speadSuits.add(i-1, new Cards(tempStringNumString,SPADES , COLOR_YELLOW));
                    break;

                    case CLUBS:
                        clubSuits.add(i-1, new Cards(tempStringNumString,CLUBS , COLOR_YELLOW));
                    break;
                }
            }
        }
    }

    public void initiateDeck()
    {

        initDeck.addAll(0, heartSuits);
        initDeck.addAll(0, diamondsSuits);
        initDeck.addAll(0, speadSuits);
        initDeck.addAll(0, clubSuits);
        
    }

    public void shuffleDeck()
    {
        Collections.shuffle(initDeck);
    }
}
