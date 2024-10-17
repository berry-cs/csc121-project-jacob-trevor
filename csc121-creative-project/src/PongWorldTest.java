import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.event.KeyEvent;

class PongWorldTest {

	Ball b1 = new Ball(new Posn (40, 50), 10, new Posn (5,5));
	Ball b2 = new Ball(new Posn (50, 60), 10, new Posn (5,5));
	Ball b3 = new Ball(new Posn (100, 200), 10, new Posn (5,5));
	Ball b4 = new Ball(new Posn (105, 205),10, new Posn (5,5));
	Ball b5 = new Ball(new Posn(100, 5), 10, new Posn(10, -1));
	
	Paddle paddleLeft = new Paddle(0, 225, 25, 150);
	Paddle paddleRight = new Paddle(775, 225, 25, 150);
	Paddle paddleRightUpdateDown = new Paddle(775, 235, 25, 150);
	Paddle paddleLeftUpdateDown = new Paddle(0, 235, 25, 150);
	Paddle paddleLeftUpdateUp = new Paddle(0, 215, 25, 150);
	Paddle paddleRightUpdateUp = new Paddle(775, 215, 25, 150);
	
	PongWorld w1 = new PongWorld(paddleLeft, paddleRight, b1);
	PongWorld w2 = new PongWorld(paddleLeft, paddleRight, b2);
	PongWorld w3 = new PongWorld(paddleLeft, paddleRight, b3);
	PongWorld w1UpdatedPaddleRightUP = new PongWorld(paddleLeft, paddleRightUpdateUp, b1);
	PongWorld w1UpdatedPaddleRightDown = new PongWorld(paddleLeft, paddleRightUpdateDown, b1);
	PongWorld w1UpdatedPaddleLeftDown = new PongWorld(paddleLeftUpdateDown, paddleRight, b1);
	PongWorld w1UpdatedPaddleLeftUp = new PongWorld(paddleLeftUpdateUp, paddleRight, b1);
	PongWorld w3UpdatedBall = new PongWorld(paddleLeft, paddleRight, b4);
	
	Posn p1 = new Posn(5, 5);
	Posn p2 = new Posn(10, 1);
	
	// Paddle p1 = ...
	// Paddle p2 == new Paddle( ... );
	// PongWorld w1 = new PongWorld( p1, p2, b1 );
	
	
	@Test
	void testMoveBall() {
		assertEquals(new Ball(new Posn (45, 55), 10, new Posn (5,5)),  b1.ballMove());
		assertEquals(new Ball(new Posn (55, 65), 10, new Posn (5,5)), b2.ballMove());
		assertEquals(new Ball(new Posn (105, 205), 10, new Posn (5,5)), b3.ballMove());
	}
	
	@Test
	void testupdate() {
		assertEquals(new PongWorld(paddleLeft, paddleRight, new Ball(new Posn (105, 205), 10, new Posn (5,5))), w3.update());
  }
	
	@Test
	void testkeyPressed() {
		assertEquals(w1UpdatedPaddleRightUP, w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.UP)));
		assertEquals(w1UpdatedPaddleRightDown, w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.DOWN)));
		assertEquals(w1UpdatedPaddleLeftUp, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 'w', 'w')));
		assertEquals(w1UpdatedPaddleLeftDown, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 's', 's')));
	}
	
	@Test 
	void updateSpeedDirection() { 
		assertEquals(p1, b1.updateSpeedDirection(new Posn(5, 5)));
		assertEquals(p2, b5.updateSpeedDirection(new Posn(10, -1)));
	}
	
	@Test
	void BounceX() {
		// assertEquals(new Ball(new Posn(40, 50), 10, new Posn(-5, 5)), b1.ballBounce());
	}
	
	
}
