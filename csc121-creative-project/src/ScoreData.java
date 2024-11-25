/* @author Trevor Childers and Jacob Bridges */

import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

/*
 * represents a players score within this pong world
 */
public class ScoreData {
	private String name;
	private int score;
	private final static int STARTING_VALUE = 0; // represents the starting value of each players score at the beginning of the game
	
	public ScoreData(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	/* overloaded constructor so that new score data can be created
	 * by reading information in from an output file
	 */
	public ScoreData (Scanner sc) { 
		this.score = sc.nextInt(); 
		this.name = sc.nextLine().trim(); 
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
	 * if the ball hits the right players paddle, add one to the right players score
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
	 * return the starting value of this score data
	 */
	public static int getStartingValue() {
		return STARTING_VALUE;
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
