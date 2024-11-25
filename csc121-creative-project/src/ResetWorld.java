import processing.core.PApplet;
import processing.event.MouseEvent;

/*
 * represents another state of this pong game.
 * acts as an end screen and allows the players to save their scores 
 * and restart the game.
 */
public class ResetWorld implements IWorld {

	/*
	 * draws the game over screen
	 */
	public PApplet draw(PApplet w) {
		w.background(42);
		w.fill(255, 0, 0);
		w.textSize(100);
		w.text("Game Over", 170, 100);
		w.fill(255);
		w.textSize(35);
		w.text("Click the mouse to go back to the home screen", 90, 300);

		return w;
	}

	/*
	 * allows the user to restart the game after the game ends
	 */
	@Override
	public IWorld mousePressed (MouseEvent mev) {
		return new StartWorld();
	}

}
