

public class Cards {
    private String suit;
    private String number;
    private String color;
    private boolean faceState;

    final static boolean UP = true;
    final static boolean DOWN = false;

    public Cards(String number, String suit, String color)
    {
        this.number = number;
        this.suit = suit;
        this.color = color;
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
        return suit + number + color;
    }

    public String getColor()
    {
        return color;
    }

    public String getNumber()
    {
        return number;
    }

    public boolean getFaceState()
    {
        return faceState;
    }
}
