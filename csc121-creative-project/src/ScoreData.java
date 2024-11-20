import java.util.Objects;

public class ScoreData {
	private String name;
	private int score;
	
	public ScoreData(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	// if the ball hits the left players paddle, add one to the left players score
	public ScoreData addToLeft(Ball aBall, Paddle paddle) {
		if (aBall.hitPaddleLeft(paddle)) {
			return new ScoreData(this.name, this.score + 1);
		}
		else return this;
	}

	// if the ball hits the right players paddle, add one to the right players score
	public ScoreData addToRight(Ball aBall, Paddle paddle) { 
		if (aBall.hitPaddleRight(paddle)) {
			return new ScoreData(this.name, this.score + 1);
		}
		else return this;
	}
	
	/*
	 * returns the score of this score data
	 */
	public int getScore() {
		return this.score;
	}

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

}
