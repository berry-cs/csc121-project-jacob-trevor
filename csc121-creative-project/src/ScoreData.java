import java.io.PrintWriter;
import java.util.Objects;

/*
 * represents a players score within this pong world
 */
public class ScoreData {
	private String name;
	private int score;
	
	public ScoreData(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	/*
	 * if the ball hits the left players paddle, add one to the left players score
	 */
	public ScoreData addToLeft(Ball aBall, Paddle paddle) {
		if (aBall.hitPaddleLeft(paddle)) {
			return new ScoreData(this.name, this.score + 1);
		}
		else return this;
	}

	/*
	 * if the ball hits the right players paddle, add one to the right players scor
	 */
	public ScoreData addToRight(Ball aBall, Paddle paddle) { 
		if (aBall.hitPaddleRight(paddle)) {
			return new ScoreData(this.name, this.score + 1);
		}
		else return this;
	}
	
	 /*
	  * writes the score value and the name of the player to the output file
	  */
	 public void writeToFile(PrintWriter pw) {
		 pw.println(this.score + " " + this.name);
	 }
	 
	/*
	 * returns the score of this score data
	 */
	public int getScore() {
		return this.score;
	}
	
	/*
	 * returns the name of this score data
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * hash code and equals methods
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreData other = (ScoreData) obj;
		return Objects.equals(name, other.name) && score == other.score;
	}

	/*
	 * to string method
	 */
	@Override
	public String toString() {
		return "ScoreData [name=" + name + ", score=" + score + "]";
	}
	
}
