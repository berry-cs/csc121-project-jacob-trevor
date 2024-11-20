import java.io.PrintWriter;
import java.util.Objects;

public class ScoreDataPair {
	private String leftName;
	private String rightName;
	private int leftScore;
	private int rightScore;
	
	ScoreDataPair(String leftName, String rightName, int leftScore, int rightScore) {
		super();
		this.leftName = leftName;
		this.rightName = rightName;
		this.leftScore = leftScore;
		this.rightScore = rightScore;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getLeftName(), getLeftScore(), getRightName(), getRightScore());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreDataPair other = (ScoreDataPair) obj;
		return Objects.equals(getLeftName(), other.getLeftName()) && getLeftScore() == other.getLeftScore()
				&& Objects.equals(getRightName(), other.getRightName()) && getRightScore() == other.getRightScore();
	}

	@Override
	public String toString() {
		return "ScoreData [leftName=" + getLeftName() + ", rightName=" + getRightName() + ", leftScore=" + getLeftScore()
				+ ", rightScore=" + getRightScore() + "]";
	}

	// if the ball hits the left players paddle, add one to the left players score
	 public ScoreDataPair addToLeft(Ball aBall, Paddle paddle) {
		 if (aBall.hitPaddleLeft(paddle)) {
			 return new ScoreDataPair(this.getLeftName(), this.getRightName(), (this.getLeftScore() + 1), this.getRightScore());
		 }
		 else return this;
	 }
	 
	// if the ball hits the right players paddle, add one to the right players score
	 public ScoreDataPair addToRight(Ball aBall, Paddle paddle) { 
		 if (aBall.hitPaddleRight(paddle)) {
			 return new ScoreDataPair(this.getLeftName(), this.getRightName(), this.getLeftScore(), (this.getRightScore() + 1));
		 }
		 else return this;
	 }
	 
	 /*
	  * 
	  */
	 public void writeToFile(PrintWriter pw) {
		 pw.println(getLeftScore() + " " + getLeftName());
		 pw.println(getRightScore() + " " + getRightName());
	 }
	 
	 /*
	  * returns the left name of this ScoreData
	  */
	 public String getLeftName() {
		 return leftName;
	 }

	 /*
	  * returns the right name of this ScoreData
	  */
	 public String getRightName() {
		 return rightName;
	 }
	 
	 /*
	  * returns the left players score 
	  */
	 public int getLeftScore() {
		 return leftScore;
	 }

	 /*
	  * returns the right players score 
	  */
	 public int getRightScore() {
		 return rightScore;
	 }

}
