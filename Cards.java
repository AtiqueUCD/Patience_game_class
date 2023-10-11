

public class Cards {
    private String suit;
    private String number;
    private String color;
    private boolean faceState;

    final static boolean UP = true;
    final static boolean DOWN = false;
    final static String RESET_COLOUR = "\u001B[0m";

    public Cards(String number, String suit, String color)
    {
        this.number = number;
        this.suit = suit;
        this.color = color;

        //Set the initial state of the cards faced down
        setState(DOWN);
    }

    public Cards()
    {
        /*
         * Empty
         */
    }

    /*
     * Setters
     */
    public void setNumber(String num)
    {
        number = num;
    }

    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setState(boolean state)
    {
        faceState = state;
    }

    /*
     * Getters
     */
    public String getCardName()
    {
        return (color + suit + number) + RESET_COLOUR;
    }

    public String getSuit()
    {
        return suit;
    }

    public String getColor()
    {
        return color;
    }

    public String getNumber()
    {
        return number;
    }

    public int getNumber(int temp)
    {
        int cardNumber = 0;
        switch(getNumber())
        {
            case "A":
                cardNumber = 1;
            break;
            case "J":
                cardNumber = 11;
            break;
            case "Q":
                cardNumber = 12;
            break;
            case "K":
                cardNumber = 13;
            break;
            default:
                cardNumber = Integer.parseInt(getNumber());
            break;
        }
        return cardNumber;
    }

    public boolean getFaceState()
    {
        return faceState;
    }
}
