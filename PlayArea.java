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
    public static Stack<Cards> stack_play_draw = new Stack<Cards>();
    public static Stack<Cards> stack_play_draw_A = new Stack<Cards>();
    public static Stack<Cards> stack_play_draw_B = new Stack<Cards>();

    public static Stack<Cards> stack_play_diamond = new Stack<Cards>();
    public static Stack<Cards> stack_play_spades = new Stack<Cards>();
    public static Stack<Cards> stack_play_heart = new Stack<Cards>();
    public static Stack<Cards> stack_play_clubs = new Stack<Cards>();

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
            else if(i < 52)
            {
                stack_play_draw_A.push(tempString);
                tempString.setState(Cards.UP);
            }
        }

        playDeckStacks.add(0, stack_play_heart);
        playDeckStacks.add(0, stack_play_spades);
        playDeckStacks.add(0, stack_play_diamond);
        playDeckStacks.add(0, stack_play_clubs);
        
        
        
        playDeckStacks.add(0, stack_play_1);
        playDeckStacks.add(0, stack_play_2);
        playDeckStacks.add(0, stack_play_3);
        playDeckStacks.add(0, stack_play_4);
        playDeckStacks.add(0, stack_play_5);
        playDeckStacks.add(0, stack_play_6);
        playDeckStacks.add(0, stack_play_7);
        playDeckStacks.add(0, stack_play_draw);
        playDeckStacks.add(0, stack_play_draw_A);
        playDeckStacks.add(0, stack_play_draw_B);
    }

    public void initPlayDeck()
    {
        for(int i = 3 ;i < 10; i++)
            if(!playDeckStacks.get(i).empty())
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

    public String getCardSuit(int stackNumber)
    {
        String suit = playDeckStacks.get(stackNumber).peek().getSuit();
        String returnSuit = "";
        switch(suit)
        {
            case HEART:
                returnSuit = "H";
            break;

            case SPADES:
                returnSuit = "S";
            break;

            case CLUBS:
                returnSuit = "C";
            break;

            case DIAMOND:
                returnSuit = "D";
            break;
                
        }
        return returnSuit;
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

    public int _getCardsNumber(String Card_number)
    {
        String tempSring = Card_number;//getCardNumber(stackNumber);
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
