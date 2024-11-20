import processing.core.PApplet;
import processing.event.MouseEvent;

/*
 * represents another state of this pong game.
 * acts as an end screen and allows the players to save their scores 
 * and restart the game.
 */

public class ResetWorld implements IWorld {

	public PApplet draw(PApplet w) {
		w.background(42);
		w.fill(255, 0, 0);
		w.textSize(100);
		w.text("Game Over", 170, 100);
		w.fill(255);
		w.textSize(50);
		w.text("Press 's' to save your scores", 125, 300);
		w.textSize(35);
		w.text("Click the mouse to go back to the home screen", 90, 500);

		return w;
	}
	
	public IWorld mousePressed (MouseEvent mev, PongWorld aWorld) {
		if (mev.getButton() == PApplet.LEFT) {
			return new StartWorld();
		}
		else if (mev.getButton() == PApplet.RIGHT) {
			 aWorld.saveScore();
			 return this;
		} 
		 return new StartWorld();
	}

}
