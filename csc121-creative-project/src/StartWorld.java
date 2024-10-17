import processing.core.PApplet;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {

	public PApplet draw(PApplet w) { 
		w.background(0,190, 190);
		w.fill(255);
		w.text("Click to start", 110, 110);
		return w; 	
	}
	

	public IWorld mouseClicked(MouseEvent mev) {
		return new PongWorld(new Paddle(0, 200, 25, 150), new Paddle(775, 200, 25, 150), new Ball( new Posn(400, 300), 20, new Posn (-5, 5)));   	// <----- 2. create your initial world object
	}
}


