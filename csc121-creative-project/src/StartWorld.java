import java.util.ArrayList;

import javax.swing.JOptionPane;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {
	
	public PApplet draw(PApplet w) { 
		ScoreBoard sb = new ScoreBoard(new ArrayList<ScoreData>());
		// ScoreData high = sb.findHighScore();
		
		w.background(0, 90, 190);
		w.fill(255);
		w.textSize(75);
		w.text("Click to Play Pong World!", w.width / 40, w.height / 4);
		sb.draw(w);
		
		return w; 	
	}
	
	// Allows the user to start the game, 
	// enter both of their names, and 
	// load in PongWorld 
	
	public IWorld mouseClicked(MouseEvent mev) {
		int paddleHeight = 150; 
		int paddleWidth = 25; 
		int speed = 7;
		int diameter = 20;
		
		String leftName = JOptionPane.showInputDialog("Left player name: ");
		String rightName =  JOptionPane.showInputDialog("Right player name: ");
		return new PongWorld(new Paddle(0, 200, paddleWidth, paddleHeight), new Paddle(775, 200, paddleWidth, paddleHeight),
				new Ball(new Posn(400, 300), diameter, new Posn(RandomExclude.randomInt(), speed)),
				new ScoreData(leftName, ScoreData.getStartingValue()), new ScoreData(rightName, ScoreData.getStartingValue())
				); 
	}
	
}


