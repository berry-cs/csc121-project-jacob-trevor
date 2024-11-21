import java.util.Comparator;

/*
 * a comparator that allows score values to be compared
 * if a negative number is returned then we know that the left player score is the highest
 * if a 0 is returned then we know that the scores are equal
 * if a positive number is returned then the left players score is the highest 
 */
public class ScoreComparator implements Comparator<ScoreData>{
	
	public int compare(ScoreData leftPlayer, ScoreData rightPlayer) {
		return leftPlayer.getScore() - rightPlayer.getScore();
	}
	
}
