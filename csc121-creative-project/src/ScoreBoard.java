/* @author Trevor Childers and Jacob Bridges */

import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import processing.core.PApplet;

/*
 * Represents the history of scores of all played games
 */
public class ScoreBoard {
	
	ArrayList<ScoreData> history;
	
	public ScoreBoard() {
		this.history = new ArrayList<ScoreData>();
		loadScores();
	}
		
	/* takes all of the scores from the output file and 
	 * adds them to the history of scores
	 */
	public void loadScores() {
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
	 * adds a score to this history of scores
	 */
	public void recordAScore(ScoreData score) {
		history.add(score);
	}
	
	/*
	 * finds the highest score in this history of scores
	 */
	public int findHighScore() {
		int maxScore = -1;
		
		for (int i = 0; i < history.size(); i++) {
			if (history.get(i).getScore() > maxScore) {
				maxScore = history.get(i).getScore();
			}
		}
		
		if (maxScore < 0) {
			return 0;
		}
		
		return maxScore;
	}
	
	/*
	 * saves the score data from a game to an output file that can later
	 * be loaded in the display the score board
	 */
	public void saveToFile() {
		
		/* loops through this history array list and writes out 
		  all the scoreData objects to it, line by line */
		try {
			PrintWriter pw = new PrintWriter(new File("output.txt"));
			
			for (int i = 0; i < history.size(); i++) {
				ScoreData cur = history.get(i);
				cur.writeToFile(pw);
				
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
		int y = 360;  // represents the y position of the score board information on the start screen
		
		w.fill(255);
		w.textSize(50);
		w.text("Score Board: ", w.width / 3, w.height / 2);
		w.text("High Score: ", 250, 200);
		w.fill(0, 255, 0);
		w.text(findHighScore(), 500, 200);
		
		/* loops through this history array list and 
		 * draws all of the data within it to the start screen.
		 * incrementing the y position of each piece of data so that 
		 * the scores are spaced out correctly
		 */
		for (int i = 0; i < history.size(); i++) {
			
			w.fill(255);
			w.text(history.get(i).getName() + ":" + " " + history.get(i).getScore(), (w.width / 3), y);
			y += 60;
		}
		return w;
	}

	/* hash code and equals methods */
	@Override
	public int hashCode() {
		return Objects.hash(history);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreBoard other = (ScoreBoard) obj;
		return Objects.equals(history, other.history);
	}

	/* to string method */
	@Override
	public String toString() {
		return "ScoreBoard [history=" + history + "]";
	}
	
	
}
