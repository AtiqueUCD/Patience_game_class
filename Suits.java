
public class Suits extends Cards{
    private int numberOfCards;
    private boolean completeState;

    public static String[] __suits = new String[]{"H","D","S","C"};

    public static String RESET = "\u001B[0m";     /* Not Used */
    public static String RED = "\u001B[31m";      /* Used for Heart and Diamonds */
    public static String GREEN = "\u001B[32m";    /* Not Used */
    public static String YELLOW = "\u001B[33m";   /* Used for Spades and Clubs */
    String[] colors = new String[]{RED, YELLOW};

    enum __colors{
        RED,
        YELLOW
    }

    public Suits(String number, String suit, String color)
    {
        super(number, suit, color);
        setState(DOWN);
    }
}
