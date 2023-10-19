public class Score {
    
    private static int score;
/**
 * Initialise the socre to Zero.
 * @return void
 */
    public Score()
    {
        score = 0;
    }
/**
 * Getter function to get the score of the play.
 * @return returns the score.
 */
    public int getScore()
    {
        return score;
    }
/**
 * when the card is transfered from pile deck to the suit deck,
 * increment the score by 20.
 * @return void
 */
    public void incDrawScore()
    {
        score += 20;
    }
/**
 * when the card is transfered from deck to the suit deck,
 * increment the score by 10.
 * @return void
 */
    public void incLametoDeckScore()
    {
        score += 10;
    }
/**
 * when the card is transfered from one deck to another,
 * increment the score by 5.
 * @return void
 */
    public void incLanetoLaneScore()
    {
        score += 5;
    }
}
