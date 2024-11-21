import javax.swing.JOptionPane;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {
	
	public PApplet draw(PApplet w) { 
		ScoreBoard sb = new ScoreBoard();
		//ScoreData high = sb.findHighScore();
		
		w.background(0,90, 190);
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
		String leftName = JOptionPane.showInputDialog("Left player name: ");
		String rightName =  JOptionPane.showInputDialog("Right player name: ");
		return new PongWorld(new Paddle(0, 200, 25, 150), new Paddle(775, 200, 25, 150),
				new Ball(new Posn(400, 300), 20, new Posn(RandomExclude.randomInt(), 5)),
				new ScoreData(leftName, 0), new ScoreData(rightName, 0)
				); 
	}
	
}


