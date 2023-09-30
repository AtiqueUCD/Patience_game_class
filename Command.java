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
        Cards tempCardsObj = new Cards();

        // int command = Integer.parseInt(commaString);
        int command = commaString;

        int pickedStackNumber = command / 10;
        int placeStackNumber = command % 10;

        // String pickedCardString = indeck.getCards(pickedStackNumber);
        // String placedCardString = indeck.getCards(placeStackNumber);

        String pickedCardColor = indeck.getCardColor(pickedStackNumber);
        String placeCardColor = indeck.getCardColor(placeStackNumber);

        int pickedCardNumber = Integer.parseInt(indeck.getCardNumber(pickedStackNumber));

        System.out.println("pi : " + pickedCardNumber);
        int placeCardNumber = Integer.parseInt(indeck.getCardNumber(placeStackNumber));
        System.out.println("pi : " + placeCardNumber);


        if(pickedCardColor == placeCardColor)
            return false;
        
        if(placeCardNumber - pickedCardNumber == 1)
        {
            tempCardsObj = indeck.popStack(pickedStackNumber);
            indeck.puchStack(placeStackNumber, tempCardsObj);
        }

        return true;
    }
}
