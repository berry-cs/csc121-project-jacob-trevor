import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

/*
 * Represents the history of scores of all played games
 */
public class ScoreBoard {
	
	ArrayList<ScoreData> history = new ArrayList<ScoreData>();
	
	public ScoreBoard(ArrayList<ScoreData> history) {
		this.history = history;
		
		try { 
			Scanner sc = new Scanner (new File("output.txt")); 
			this.history.clear(); 
			
			while (sc.hasNextInt()) { 
				ScoreData s = new ScoreData (sc); 
				recordAScore(s); 
			}
			
			sc.close(); 
		}catch (IOException exp) { 
			System.out.println ("Problem loading scores" + exp.getMessage()); 
		
		}
		
	}
	
	/*
	 * adds a score to the history of scores
	 */
	public void recordAScore(ScoreData score) {
		history.add(score);
	}
	
	/*
	 * finds the highest score in a history of scores
	 */
	public ScoreData findHighScore() {
		return new ScoreData("Trevor", 1);
	}
	
	/*
	 * saves the score data from a game to an output file that can later
	 * be loaded in the display the score board
	 */
	public void saveToFile() {
		// loop through the history array list and write out 
		// all the scoreData object to it, line by line

		try {
			PrintWriter pw = new PrintWriter(new File("output.txt"));
			
			for (int i = 0; i < history.size(); i++) {
				ScoreData cur = history.get(i);
				
				System.out.println(cur.getScore() + " " + cur.getName());
			}

			pw.close();
		} catch (IOException exp) {
			System.out.println("Problem Saving Score: " + exp.getMessage());
		}
	}
	
	/*
	 * draws the score board
	 */
	public PApplet draw(PApplet w) {
		w.fill(255);
		w.textSize(100);
		w.text("Score Board: " + history, w.width / 2, w.height / 2);
		return w;
	}
}
