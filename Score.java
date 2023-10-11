public class Score {
    
    private static int score;
    
    Score()
    {
        score = 0;
    }

    public int getScore()
    {
        return score;
    }

    public void incDrawScore()
    {
        score += 20;
    }

    public void incLametoDeckScore()
    {
        score += 10;
    }

    public void incLanetoLaneScore()
    {
        score += 5;
    }
}
