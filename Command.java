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

    public static void multipleTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber, int noOfCards)
    {
        
    }

    public static boolean checkValidTransaction(PlayArea indeck, int pickedStackNumber, int placeStackNumber)
    {
        boolean returnStatus = false;
        String pickedCardColor = indeck.getCardColor(pickedStackNumber);
        String placeCardColor = indeck.getCardColor(placeStackNumber);

        int pickedCardNumber = indeck._getCardsNumber(pickedStackNumber);
        int placeCardNumber = indeck._getCardsNumber(placeStackNumber);

        if((pickedCardColor != placeCardColor))
            if(placeCardNumber - pickedCardNumber == 1)
                returnStatus = true;

        return returnStatus;
    }
}
