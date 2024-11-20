import java.util.Comparator;

/*
 * a comparator that allows score values to be compared
 */
public class ScoreComparator implements Comparator<ScoreData>{
	
	public boolean compare(ScoreData p1, ScoreData p2) {
		return p1.getScore() > p2.getScore();
	}
	

}
