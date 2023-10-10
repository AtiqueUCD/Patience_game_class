import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command{

    public static final int INIT_DRAW_ID = 1;
    public static final int DRAW_ID_0 = 0;
    public static final int DRAW_ID_1 = 1;
    public static final int CMD_OFFSET = 1;

    public static String commandString;

    public static int drawID = INIT_DRAW_ID;
    public static int place_ID = DRAW_ID_0;
    public static boolean draw_picked_state = false;

    public static final String DRAW_CARD_U = "D";
    public static final String DRAW_CARD_L = "d";

    public static String getCommand()
    {
        Scanner in = new Scanner(System.in);
        commandString = in.nextLine();
        
        return commandString;
        
    }

    public static String separateAlphabets(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(input);

        StringBuilder alphabets = new StringBuilder();
        while (matcher.find()) {
            alphabets.append(matcher.group());
        }

        return alphabets.toString();
    }
    public static String separateNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(input);

        StringBuilder numbers = new StringBuilder();
        while (matcher.find()) {
            numbers.append(matcher.group());
        }

        return numbers.toString();
    }

    public static int getFinalSuitStackNumber(String str)
    {
        int stackNumber = 0;
        switch(str)
        {
            case "H":
            case "h":
                //stack number 9
                stackNumber = 10;
            break;

            case "S":
            case "s":
                //stack number 10
                stackNumber = 11;
            break;

            case "D":
            case "d":
                //stack number 11
                stackNumber = 12;
            break;

            case "C":
            case "c":
                //stack number 12
                stackNumber = 13;
            break;
        }
        return stackNumber;
    }
    /*
     * Alphabets will always be on the placed end
     * numbered will always be on picked, placed and no of cards
     * 
     * picked:-
     *          max length - 3 - { picked + placed + no of cards}
     *          min length - 2 - {picked + placed}; (no of cards, picked) + Alphabet no. stacked
     * placed:-
     *          min length - 1
     *          max length - 1
     */
    public static void processCommand(PlayArea indeck, String picked, String placed)
    {

        int len_1 = 0;
        int len_2 = 0;
        
        len_1 = picked.length();
        len_2 = placed.length();

        int[] cmd = new int[]{0,0,0}; /* 0-> placed, 1-> picked 2-> no of cards*/

        int command = 0, pickedStackNumber = 0, placeStackNumber = 0, noOfCards = 0;
        if(len_1 > 1)
        {
            command = Integer.parseInt(picked);

            int i = 0;
            while (command != 0) {
                cmd[i++] = command % 10; // Get the last digit
                command = command / 10; 
            }

            pickedStackNumber = cmd[1] + CMD_OFFSET;
            placeStackNumber = cmd[0] + CMD_OFFSET;
            noOfCards = cmd[2];
        }else if(len_1 == 1)
        {
            command = Integer.parseInt(picked);
            cmd[0] = command % 10;
            pickedStackNumber = cmd[0] + CMD_OFFSET;
        }


        if((len_1 == 2) && (len_2 == 0))
        {
            //single transaction
            System.out.println("Single tranaction");

            singleTransaction(indeck, pickedStackNumber, placeStackNumber);
            if(draw_picked_state)
            {
                draw_picked_state = false;
                indeck.popStack(place_ID);
            }

        }
        else if(len_1 == 3)
        {
            //Multiple transactions
            System.out.println("Multiple tranaction");
            multipleTransaction(indeck, pickedStackNumber, placeStackNumber, noOfCards);
        }else if((len_1 == 2) && (len_2 != 0))
        {
            //multiple transaction with Apha stack
            System.out.println("multiple transaction with Apha stack");


        }else if((len_1 == 1) && (len_2 != 0))
        {
            //single transaction with Apha stack
            System.out.println("single transaction with Apha stack");

            singleTransaction(indeck, pickedStackNumber, getFinalSuitStackNumber(placed));
            if(draw_picked_state)
            {
                draw_picked_state = false;
                indeck.popStack(place_ID);
            }
        }else if(len_1 == 0 && (placed.equals(DRAW_CARD_U) || placed.equals(DRAW_CARD_L)))
        {
            System.out.println("Draw");
            flipDrawCard(indeck);
        }else
        {
            System.out.println("Invalid Command!!!");
        }

    }
    public static boolean processCommand(PlayArea indeck, int commaString)
    {
        

        int command = commaString;
        int[] cmd = new int[]{0,0,0}; /* 0-> placed, 1-> picked 2-> no of cards*/
        int i = 0;


        while (command != 0) {
            cmd[i++] = command % 10; // Get the last digit
            command = command / 10; 
        }

        int pickedStackNumber = cmd[1] + CMD_OFFSET;
        int placeStackNumber = cmd[0] + CMD_OFFSET;
        int noOfCards = cmd[2];
        
        if(pickedStackNumber == CMD_OFFSET)
        {
            if(placeStackNumber == 9)
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
