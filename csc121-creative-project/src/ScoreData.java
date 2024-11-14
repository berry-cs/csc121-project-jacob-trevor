import java.util.Objects;

public class ScoreData {
	String leftName;
	String rightName;
	int leftScore;
	int rightScore;
	
	ScoreData(String leftName, String rightName, int leftScore, int rightScore) {
		super();
		this.leftName = leftName;
		this.rightName = rightName;
		this.leftScore = leftScore;
		this.rightScore = rightScore;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(leftName, leftScore, rightName, rightScore);
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
		return Objects.equals(leftName, other.leftName) && leftScore == other.leftScore
				&& Objects.equals(rightName, other.rightName) && rightScore == other.rightScore;
	}

	@Override
	public String toString() {
		return "ScoreData [leftName=" + leftName + ", rightName=" + rightName + ", leftScore=" + leftScore
				+ ", rightScore=" + rightScore + "]";
	}

	// if the ball hits the left players paddle, add one to the left players score
	 public ScoreData addToLeft(Ball aBall, Paddle paddle) {
		 if (aBall.hitPaddleLeft(paddle)) {
			 return new ScoreData(this.leftName, this.rightName, this.leftScore += 1, this.rightScore);
		 }
		 else return this;
	 }
	 
	// if the ball hits the right players paddle, add one to the right players score
	 public ScoreData addToRight(Ball aBall, Paddle paddle) { 
		 if (aBall.hitPaddleRight(paddle)) {
			 return new ScoreData(this.leftName, this.rightName, this.leftScore, this.rightScore += 1);
		 }
		 else return this;
	 }
}
