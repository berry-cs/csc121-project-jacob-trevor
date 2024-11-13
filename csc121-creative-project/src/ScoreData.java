
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
	
	
	//
	 public ScoreData addToLeft() { // TODO
		 return this;
	 }
	// 
	
	 public ScoreData addToRight() { 
		 return this;
	 }
}
