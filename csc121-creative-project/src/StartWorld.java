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
		return new PongWorld(new Paddle(0, 200, 25, 50), new Paddle(400, 200, 50, 25), new Ball( new Posn(100, 300), 20, new Posn (-5,-5)));   	// <----- 2. create your initial world object
	}
}


