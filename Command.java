import java.util.ArrayList;
import java.util.Scanner;

public class Command{

    public static final int INIT_DRAW_ID = 1;
    public static final int DRAW_ID_0 = 0;
    public static final int DRAW_ID_1 = 1;
    public static final int CMD_OFFSET = 1;

    public static int commandString;

    public static int drawID = INIT_DRAW_ID;
    public static int place_ID = DRAW_ID_0;
    public static boolean draw_picked_state = false;

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

        int pickedStackNumber = cmd[1] + CMD_OFFSET;//command / 10;
        int placeStackNumber = cmd[0] + CMD_OFFSET;//command % 10;
        int noOfCards = cmd[2];//command / 100;
        
        if(pickedStackNumber == CMD_OFFSET)// && placeStackNumber == 8)
        {
            if(placeStackNumber == 9)//8)
                flipDrawCard(indeck);
            else
            {
                System.out.println("CMD -> INV");
            }
        }
        else if(noOfCards == 0)
        {
            singleTransaction(indeck, pickedStackNumber, placeStackNumber);
            if(draw_picked_state)
            {
                draw_picked_state = false;
                indeck.popStack(place_ID);
            }
        }else{
            multipleTransaction(indeck, pickedStackNumber, placeStackNumber, noOfCards);
        }




        return true;
    }

    public static void flipDrawCard(PlayArea indeck)
    {
        Cards tempCard = new Cards();
        place_ID = DRAW_ID_0;
        place_ID = (drawID == DRAW_ID_1) ? DRAW_ID_0 : DRAW_ID_1;

        if(!indeck.getStackIsEmpty(drawID))
        {
            tempCard = indeck.popStack(drawID);
            indeck.puchStack(place_ID, tempCard);
        }else{
            drawID = place_ID;
            place_ID = (drawID == DRAW_ID_1) ? DRAW_ID_0 : DRAW_ID_1;
            tempCard = indeck.popStack(drawID);
            indeck.puchStack(place_ID, tempCard);
        }
        if(!indeck.getStackIsEmpty(2))
            indeck.popStack(2);
        indeck.puchStack(2, tempCard);

    }

    public static boolean singleTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber)
    {
        Cards tempCardsObj = new Cards();
        boolean returnStatus = false;

        boolean state = checkValidTransaction(indeck,pickedStackNumber, placeStackNumber);
        if(state == true)
        {
            tempCardsObj = indeck.popStack(pickedStackNumber);
            indeck.puchStack(placeStackNumber, tempCardsObj);
            returnStatus = true;
            if(pickedStackNumber == 2)
                draw_picked_state = true;
            System.out.println("S");
        }
        return returnStatus;
    }

    public static boolean multipleTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber, int noOfCards)
    {
        ArrayList<Cards> transportList = new ArrayList<Cards>();

        int transactionNumber = 0;

        String pickedCardColor = "", placeCardColor = "";
        int pickedCardNumber = 0, placedCardNumber = 0;

        /* Used for checking single card */
        String first_Cards = "", first_Cards_color = "";
        String placed_Cards = "",place_Cards_color = "";
        int first_cards_number, placed_cards_number;
        boolean first_card_state = false, placed_card_state = false;

        //This loop is to be executed after checking for the first card the the picked array and card from the placed stack.
        while((transactionNumber++) < noOfCards)
        {
            transportList.add(indeck.popStack(pickedStackNumber));
        }

        //get the first card from the picked up deck
        first_Cards = transportList.get(noOfCards - 1).getNumber();
        first_Cards_color = transportList.get(noOfCards - 1).getColor();
        first_cards_number = indeck._getCardsNumber(first_Cards);
        first_card_state = transportList.get(noOfCards - 1).getFaceState();

        //Check for a single card
        if(!indeck.getStackIsEmpty(placeStackNumber))
        {
            placed_Cards = indeck.getCardNumber(placeStackNumber);
            place_Cards_color = indeck.getCardColor(placeStackNumber);
            placed_cards_number = indeck._getCardsNumber(placed_Cards);
            first_card_state = indeck.getCardFaceStatus(placeStackNumber);
        }else{
            placed_cards_number = first_cards_number + 1;
            place_Cards_color = (first_Cards_color == "\u001B[31m") ? ("\u001B[33m") : ("\u001B[31m");
        }

        //Check if the first cards from picked and card on which the card has to be placed are all face up
        if(first_card_state != Cards.UP && placed_card_state != Cards.UP)
        {
            System.out.println("FC -> FAIL");

            for(Cards i : transportList)
            {
                indeck.puchStack(pickedStackNumber, i);
            }
            return false;       
        }

        System.out.println("FC -> OK");

        //check if they are faced up or not
        if(!checkValidTransaction(first_Cards_color,place_Cards_color,first_cards_number,placed_cards_number))
        {
            System.out.println("SC -> FAIL");

            for(Cards i : transportList)
            {
                indeck.puchStack(pickedStackNumber, i);
            }
            return false;
        }
        System.out.println("SC -> OK");


        transactionNumber = noOfCards - 1;

        boolean n_card_status = false, n_1_card_status = false;

        while((transactionNumber) > 0)
        {
            pickedCardColor = transportList.get(transactionNumber).getColor();
            pickedCardNumber = transportList.get(transactionNumber).getNumber(0);
            n_card_status = transportList.get(transactionNumber).getFaceState();

            placeCardColor = transportList.get(--transactionNumber).getColor();
            placedCardNumber = transportList.get(transactionNumber).getNumber(0);
            n_1_card_status = transportList.get(transactionNumber).getFaceState();

            //Check the face status
            if(!(n_card_status == true && n_1_card_status == true))
            {
                System.out.println("MSFC -> FAIL");
                return false;        
            }
            //card number is zero
            if(!checkValidTransaction(pickedCardColor, placeCardColor, placedCardNumber, pickedCardNumber))
            {
                System.out.println("MTC -> FAIL");
                for(Cards i : transportList)
                {
                    indeck.puchStack(pickedStackNumber, i);
                }
                return false;
            }
        }
        
        
        while((--noOfCards) >= 0)
        {
            System.out.println("MT -> OK");
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
