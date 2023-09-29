

import java.util.ArrayList;
import java.util.Stack;


public class Model {

    Suits[] heartSuits = new Suits[13];
    Suits[] diamondsSuits = new Suits[13];
    Suits[] speadSuits = new Suits[13];
    Suits[] clubSuits = new Suits[13];

    public ArrayList<Stack<String>> playArrayList = new ArrayList<Stack<String>>();


    public void initiateCardDeck()
    {
        String tempNumString = "";

        for(String suit_type : Suits.__suits)
        {

            for(int i = 0; i < 13; i++)
            {
                switch(i)
                {
                    case 0:
                        tempNumString = "A";
                    break;

                    case 10:
                        tempNumString = "J";
                    break;

                    case 11:
                        tempNumString = "Q";
                    break;

                    case 12:
                        tempNumString = "K";
                    break;

                    default:
                        tempNumString = Integer.toString(i);
                    break;
                }
                

                switch(suit_type)
                {
                    case "H":
                        heartSuits[i] = new Suits(tempNumString,"H" , "\u001B[31m");
                    break;

                    case "D":
                        diamondsSuits[i] = new Suits(tempNumString,"D" , "\u001B[31m");
                    break;

                    case "S":
                        speadSuits[i] = new Suits(tempNumString, "S", "\u001B[33m");
                    break;

                    case "C":
                        clubSuits[i] = new Suits(tempNumString, "C", "\u001B[33m");
                    break;
                }
            }
        }


    }
    
}
