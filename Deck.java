

import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    final static String HEART = "\u2764";
    final static String DIAMOND = "\u2666";
    final static String SPADES = "\u2660";
    final static String CLUBS = "\u2663";

    public static String[] __suits = new String[]{HEART,SPADES,DIAMOND,CLUBS};

    public static ArrayList<Cards> heartSuits = new ArrayList<Cards>();
    public static ArrayList<Cards> diamondsSuits = new ArrayList<Cards>();
    public static ArrayList<Cards> speadSuits = new ArrayList<Cards>();
    public static ArrayList<Cards> clubSuits = new ArrayList<Cards>();

    // public static ArrayList<Stack<String>> playDeck = new ArrayList<Stack<String>>();

    public static ArrayList<Cards> initDeck = new ArrayList<Cards>();


    public void initiateSuit()
    {
        String tempStringNumString = "";

        for(String suit_type : __suits)
        {

            for(int i = 1; i <= 13; i++)
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
                        heartSuits.add(i-1, new Cards(tempStringNumString,HEART , "\u001B[31m"));
                    break;

                    case DIAMOND:
                        diamondsSuits.add(i-1, new Cards(tempStringNumString,DIAMOND , "\u001B[31m"));//[i] = new Cards(tempStringNumString,"D" , "\u001B[31m");
                    break;

                    case SPADES:
                        speadSuits.add(i-1, new Cards(tempStringNumString,SPADES , "\u001B[33m"));//[i] = new Cards(tempStringNumString, "S", "\u001B[33m");
                    break;

                    case CLUBS:
                        clubSuits.add(i-1, new Cards(tempStringNumString,CLUBS , "\u001B[33m"));//[i] = new Cards(tempStringNumString, "C", "\u001B[33m");
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
