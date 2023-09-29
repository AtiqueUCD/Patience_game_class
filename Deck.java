

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck{

    public String[] __suits = new String[]{"H","S","D","C"};

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

            for(int i = 0; i < 13; i++)
            {

                if(i == 0)
                {
                    tempStringNumString = "A";
                }

                else if(i == 10)
                {
                    tempStringNumString = "J";
                }

                else if(i == 11)
                {
                    tempStringNumString = "Q";
                }

                else if(i == 12)
                {
                    tempStringNumString = "K";
                }

                else{
                    tempStringNumString = Integer.toString(i);
                }
                
                

                switch(suit_type)
                {
                    case "H":
                        heartSuits.add(i, new Cards(tempStringNumString,"H" , "\u001B[31m"));
                    break;

                    case "D":
                        diamondsSuits.add(i, new Cards(tempStringNumString,"D" , "\u001B[31m"));//[i] = new Cards(tempStringNumString,"D" , "\u001B[31m");
                    break;

                    case "S":
                        speadSuits.add(i, new Cards(tempStringNumString,"S" , "\u001B[33m"));//[i] = new Cards(tempStringNumString, "S", "\u001B[33m");
                    break;

                    case "C":
                        clubSuits.add(i, new Cards(tempStringNumString,"C" , "\u001B[33m"));//[i] = new Cards(tempStringNumString, "C", "\u001B[33m");
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
