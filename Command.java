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

        int pickedStackNumber = command / 10;
        int placeStackNumber = command % 10;
        int noOfCards = command / 100;


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

        String placeCardColor = indeck.getCardColor(placeStackNumber);
        int placeCardNumber = indeck._getCardsNumber(placeStackNumber);

        while((transactionNumber++) >= noOfCards)
        {
            transportList.add(transactionNumber,indeck.popStack(pickedStackNumber));
        }

        while((transactionNumber--) > 0)
        {
            String pickedCardColor = transportList.get(transactionNumber).getColor();//indeck.getCardColor(pickedStackNumber);
            int pickedCardNumber = Integer.parseInt(transportList.get(transactionNumber).getNumber());// indeck._getCardsNumber(pickedStackNumber);
            
            if(!checkValidTransaction(pickedCardColor, placeCardColor, pickedCardNumber, placeCardNumber))
                return false;
        }

        while((noOfCards--) > 0)
        {
            System.out.println("M");
            //transfer the picked card into the placed stack
            indeck.puchStack(pickedStackNumber, transportList.get(noOfCards));
        }

        return true;

    }

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

    public static boolean checkValidTransaction(String pickedCardColor, String placeCardColor, int pickedCardNumber, int placeCardNumber)
    {
        boolean returnStatus = false;

        if((pickedCardColor != placeCardColor))
            if(placeCardNumber - pickedCardNumber == 1)
                returnStatus = true;

        return returnStatus;
    }
    
}
