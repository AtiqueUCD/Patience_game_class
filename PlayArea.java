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
}
