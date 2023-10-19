

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

/**
 * Sets the number for the card.
 * @return void
 */
    public void setNumber(String num)
    {
        number = num;
    }
/**
 * Sets the suit for the card.
 * @return void
 */
    public void setSuit(String suit)
    {
        this.suit = suit;
    }
/**
 * Sets the color of the card.
 * @return void
 */
    public void setColor(String color)
    {
        this.color = color;
    }
/**
 * Sets the face status of the card.
 * @return void
 */
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
/**
 * Getter fucntion for suit of the card.
 * @return Returns the suit of the card.
 */
    public String getSuit()
    {
        return suit;
    }
/**
 * Getter fucntion for card color
 * @return The string for the color of the card.
 */
    public String getColor()
    {
        return color;
    }
/**
 * Return the number of the card.
 * 
 * @return card number in string format
 */
    public String getNumber()
    {
        return number;
    }
/**
 * Return the integer form of the card number. Convertes the alphabet numbering into integer.
 *
 * @param temp interger form of the stack number
 * @return The index number for the PlayArea stack
 */
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
/**
 * Gets the face status of the card, whether the card is faced up or down.

 * @return face up or down status.
 */
    public boolean getFaceState()
    {
        return faceState;
    }
}