import java.util.ArrayList;
import java.util.Scanner;

public class Command{

    public static int commandString;
    public static int getCommand()
    {
        Scanner in = new Scanner(System.in);
        commandString = in.nextInt();
        
        return commandString;
        
    }

    public static boolean processCommand(PlayArea indeck, int commaString)
    {
        

        int command = commaString;
        int[] cmd = new int[]{0,0,0}; /* 0-> placed, 1-> picked 2-> no of cards*/
        int i = 0;

        // int pickedStackNumber = command / 10;
        // int placeStackNumber = command % 10;
        // int noOfCards = command / 100;


        while (command != 0) {
            cmd[i++] = command % 10; // Get the last digit
            command = command / 10; 
        }

        int pickedStackNumber = cmd[1];//command / 10;
        int placeStackNumber = cmd[0];//command % 10;
        int noOfCards = cmd[2];//command / 100;
        

        if(noOfCards == 0)
        {
            singleTransaction(indeck, pickedStackNumber, placeStackNumber);
        }else{
            multipleTransaction(indeck, pickedStackNumber, placeStackNumber, noOfCards);
        }




        return true;
    }

    public static boolean singleTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber)
    {
        Cards tempCardsObj = new Cards();
        boolean returnStatus = false;

        // String pickedCardColor = indeck.getCardColor(pickedStackNumber);
        // String placeCardColor = indeck.getCardColor(placeStackNumber);

        // int pickedCardNumber = indeck._getCardsNumber(pickedStackNumber);
        // int placeCardNumber = indeck._getCardsNumber(placeStackNumber);

        // if(pickedCardColor == placeCardColor)
        //     return false;
        
        // if(placeCardNumber - pickedCardNumber == 1)
        // {
        //     tempCardsObj = indeck.popStack(pickedStackNumber);
        //     indeck.puchStack(placeStackNumber, tempCardsObj);
        // }
        boolean state = checkValidTransaction(indeck,pickedStackNumber, placeStackNumber);
        if(state == true)
        {
            tempCardsObj = indeck.popStack(pickedStackNumber);
            indeck.puchStack(placeStackNumber, tempCardsObj);
            returnStatus = true;
            System.out.println("S");
        }
        return returnStatus;
    }

    public static boolean multipleTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber, int noOfCards)
    {
        ArrayList<Cards> transportList = new ArrayList<Cards>();

        int transactionNumber = 0;

        String pickedCardColor = "";
        int pickedCardNumber = 0;

        /* Used for checking single card */
        String first_Cards = "", first_Cards_color = "";
        String placed_Cards = "",place_Cards_color = "";
        int first_cards_number, placed_cards_number;
        boolean first_card_state = false, placed_card_state = false;
        


        
        /*
        String placeCardColor = indeck.getCardColor(placeStackNumber);
        int placeCardNumber = indeck._getCardsNumber(placeStackNumber);
        */

        //This loop is to be executed after checking for the first card the the picked array and card from the placed stack.
        while((transactionNumber++) < noOfCards)
        {
            transportList.add(indeck.popStack(pickedStackNumber));
        }

        //debugging - remove latter
        System.out.println(transportList);

        //get the first card from the picked up deck
        first_Cards = transportList.get(transactionNumber - 1).getCardName();
        first_Cards_color = transportList.get(transactionNumber - 1).getColor();
        first_cards_number = indeck._getCardsNumber(first_Cards);
        first_card_state = transportList.get(first_cards_number).getFaceState();

        //Check for a single card
        placed_Cards = indeck.getCards(placeStackNumber);
        place_Cards_color = indeck.getCardColor(placeStackNumber);
        placed_cards_number = indeck._getCardsNumber(placed_Cards);
        first_card_state = indeck.getCardFaceStatus(placeStackNumber);

        //Check if the first cards from picked and card on which the card has to be placed are all face up
        if(first_card_state != Cards.UP && placed_card_state != Cards.UP)
        {
            System.out.println("FC -> FAIL");
            return false;       
        }

        System.out.println("FC -> OK");

        //check if they are faced up or not
        if(!checkValidTransaction(first_Cards_color,place_Cards_color,first_cards_number,placed_cards_number))
        {
            System.out.println("SC -> FAIL");
            return false;
        }
        System.out.println("SC -> OK");
        /* 
        if(!checkValidTransaction(transportList, indeck, pickedStackNumber, placeStackNumber))
            return false;
        */

        /*
        while((transactionNumber--) > 0)
        {
            pickedCardColor = transportList.get(transactionNumber).getColor();
            pickedCardNumber = Integer.parseInt(transportList.get(transactionNumber).getNumber());
            
            if(!checkValidTransaction(pickedCardColor, placeCardColor, pickedCardNumber, placeCardNumber))
                return false;
        }
        */
        while((--noOfCards) >= 0)
        {
            System.out.println("M");
            //transfer the picked card into the placed stack
            indeck.puchStack(placeStackNumber, transportList.get(noOfCards));
        }

        return true;

    }

    /*
     * Currently used for single transaction
     */
    public static boolean checkValidTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber)
    {
        boolean returnStatus = false;
        String placeCardColor = " ";
        int placeCardNumber = 0;
        
        String pickedCardColor = indeck.getCardColor(pickedStackNumber);
        int pickedCardNumber = indeck._getCardsNumber(pickedStackNumber);

        if(!indeck.getStackIsEmpty(placeStackNumber))
        {
            placeCardColor = indeck.getCardColor(placeStackNumber);
            placeCardNumber = indeck._getCardsNumber(placeStackNumber);
        }else{
            placeCardNumber = pickedCardNumber + 1;
            placeCardColor = (pickedCardColor == "\u001B[31m") ? ("\u001B[33m") : ("\u001B[31m");
        }

        if((pickedCardColor != placeCardColor))
            if(placeCardNumber - pickedCardNumber == 1)
                returnStatus = true;

        return returnStatus;
    }

    public static boolean checkValidTransaction(ArrayList<Cards> arrayList, PlayArea indeck, int pickedStackNumber, int placeStackNumber)
    {
        boolean returnStatus = false;
        String placeCardColor = " ";
        int placeCardNumber = 0;
        
        String pickedCardColor = arrayList.get(0).getColor();
        int pickedCardNumber = arrayList.get(0).getNumber(0);

        if(!indeck.getStackIsEmpty(placeStackNumber))
        {
            placeCardColor = indeck.getCardColor(placeStackNumber);
            placeCardNumber = indeck._getCardsNumber(placeStackNumber);
        }else{
            placeCardNumber = pickedCardNumber + 1;
            placeCardColor = (pickedCardColor == "\u001B[31m") ? ("\u001B[33m") : ("\u001B[31m");
        }

        if((pickedCardColor != placeCardColor))
            if(placeCardNumber - pickedCardNumber == 1)
                returnStatus = true;

        return returnStatus;
    }

    public static boolean checkValidTransaction(String pickedCardColor, String placeCardColor, int pickedCardNumber, int placeCardNumber)
    {
        boolean returnStatus = false;
        
        if((pickedCardColor != placeCardColor))
            if(placeCardNumber - pickedCardNumber == 1)
                returnStatus = true;

        return returnStatus;
    }
    
}
