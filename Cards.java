package Patience_game;

public class Cards {
    private String suit;
    private String number;
    private String color;
    private boolean faceState;


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
        return suit + number;
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
