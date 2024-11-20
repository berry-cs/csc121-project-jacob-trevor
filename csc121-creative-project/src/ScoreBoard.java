import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Represents the history of scores of all played games
 */
public class ScoreBoard {
	
	ArrayList<ScoreData> history;
	
	public ScoreBoard() {
		
		// open the output.txt file and read in all the 
		//   records into the history arraylist
		// i.e.
		// for each line of the output.txt file,
		//    create a ScoreData object for it and add to the arraylist
		
		
	}
	
	
	public void recordAScore(ScoreData score) {
		history.add(score);
	}
	
	public ScoreData findHighScore() {
		// TODO
		return null;
	}
	
	public void saveToFile() {
		// loop through the history arraylist and write out 
		// all the scoreData object to it, line by line
		
	}
	
	public PApplet draw(PApplet w) {
		
		
		return w;
	}
}
