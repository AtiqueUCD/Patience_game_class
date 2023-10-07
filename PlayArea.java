import java.util.ArrayList;
import java.util.Stack;

public class PlayArea extends Deck{
    
    public static ArrayList<Stack<Cards>> playDeckStacks = new ArrayList<Stack<Cards>>();
    public static Stack<Cards> stack_play_1 = new Stack<Cards>();
    public static Stack<Cards> stack_play_2 = new Stack<Cards>();
    public static Stack<Cards> stack_play_3 = new Stack<Cards>();
    public static Stack<Cards> stack_play_4 = new Stack<Cards>();
    public static Stack<Cards> stack_play_5 = new Stack<Cards>();
    public static Stack<Cards> stack_play_6 = new Stack<Cards>();
    public static Stack<Cards> stack_play_7 = new Stack<Cards>();

    public Cards tempString;

    public void setupPlayArea()
    {
        for(int i = 0; i < initDeck.size(); i++)
        {
            tempString = initDeck.get(i);

            if(i == 0)
                stack_play_1.push(tempString);
            else if(i < 3)
            {
                stack_play_2.push(tempString);
            }
            else if(i < 6)
            {
                stack_play_3.push(tempString);
            }
            else if(i < 10)
            {
                stack_play_4.push(tempString);
            }
            else if(i < 15)
            {
                stack_play_5.push(tempString);
            }
            else if(i < 21)
            {
                stack_play_6.push(tempString);
            }
            else if(i < 28)
            {
                stack_play_7.push(tempString);
            }
            // else if(i < 52)
            //     stack_draw_A.push(tempString);
        }

        playDeckStacks.add(0, stack_play_1);
        playDeckStacks.add(0, stack_play_2);
        playDeckStacks.add(0, stack_play_3);
        playDeckStacks.add(0, stack_play_4);
        playDeckStacks.add(0, stack_play_5);
        playDeckStacks.add(0, stack_play_6);
        playDeckStacks.add(0, stack_play_7);
    }

    public void initPlayDeck()
    {
        for(int i = 0 ;i < 7; i++)
            playDeckStacks.get(i).peek().setState(true);
    }

    public String getCards(int stackNumber)
    {
        return playDeckStacks.get(stackNumber).peek().getCardName();
    }

    public String getCardColor(int stackNumber)
    {
        return playDeckStacks.get(stackNumber).peek().getColor();
    }

    public String getCardNumber(int stackNumber)
    {
        return playDeckStacks.get(stackNumber).peek().getNumber();
    }

    public boolean getCardFaceStatus(int stackNumber)
    {
        return playDeckStacks.get(stackNumber).peek().getFaceState();
    }
    public int _getCardsNumber(int stackNumber)
    {
        String tempSring = getCardNumber(stackNumber);
        int returnNumber;

        switch(tempSring)
        {
            case "A":
                returnNumber = 1;
            break;

            case "J":
                returnNumber = 11;
            break;

            case "Q":
                returnNumber = 12;
            break;

            case "K":
                returnNumber = 13;
            break;

            default:
                returnNumber = Integer.parseInt(tempSring);
        }

        return returnNumber;
    }

    public int _getCardsNumber(String Card_name)
    {
        String tempSring = Card_name;//getCardNumber(stackNumber);
        int returnNumber;

        switch(tempSring)
        {
            case "A":
                returnNumber = 1;
            break;

            case "J":
                returnNumber = 11;
            break;

            case "Q":
                returnNumber = 12;
            break;

            case "K":
                returnNumber = 13;
            break;

            default:
                returnNumber = Integer.parseInt(tempSring);
        }

        return returnNumber;
    }

    public Cards popStack(int stackNumber)
    {
        Cards tempObj = new Cards();

        if(!playDeckStacks.get(stackNumber).isEmpty())
        {
            tempObj = playDeckStacks.get(stackNumber).pop();
        }

        if(!playDeckStacks.get(stackNumber).isEmpty() && !playDeckStacks.get(stackNumber).peek().getFaceState())
        {
            setCardFaceUP(stackNumber);
        }
        return tempObj;
    }

    public void puchStack(int stackNumber, Cards newCard)
    {
        playDeckStacks.get(stackNumber).push(newCard);
    }

    public void setCardFaceUP(int stackNumber)
    {
        playDeckStacks.get(stackNumber).peek().setState(Cards.UP);
    }

    public boolean getStackIsEmpty(int stackNumber)
    {
        return playDeckStacks.get(stackNumber).isEmpty();
    }
}
