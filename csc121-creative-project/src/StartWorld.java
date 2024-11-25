/* @author Trevor Childers and Jacob Bridges */

import javax.swing.JOptionPane;
import processing.core.PApplet;
import processing.event.MouseEvent;

/* represents the start screen of the pong world */
public class StartWorld implements IWorld {
	
	/* draws all of the text and score information on the start screen */
	public PApplet draw(PApplet w) { 
		ScoreBoard sb = new ScoreBoard();
		
		w.background(0, 90, 190);
		w.fill(255);
		w.textSize(75);
		w.text("Click to Play Pong World!", w.width / 40, 60);
		sb.draw(w);
		
		return w; 	
	}
	
	/* provides a dialogue box that allows the players to input
	 * their own names once the player clicks the mouse. Once the player names are entered,
	 * the game starts.
	 */
	public IWorld mouseClicked(MouseEvent mev) {
		int paddleHeight = 150; 
		int paddleWidth = 25; 
		int speed = 7;
		int diameter = 20;
		
		String leftName = JOptionPane.showInputDialog("Left player name: ");
		String rightName =  JOptionPane.showInputDialog("Right player name: ");
		return new PongWorld(new Paddle(0, 200, paddleWidth, paddleHeight), new Paddle(775, 200, paddleWidth, paddleHeight),
				new Ball(new Posn(400, 300), diameter, new Posn(RandomExclude.randomInt(), speed)),
				new ScoreData(leftName, ScoreData.getStartingValue()), new ScoreData(rightName, ScoreData.getStartingValue()), new ScoreBoard()); 
	}
	
}


