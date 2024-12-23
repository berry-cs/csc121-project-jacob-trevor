/*
 * @author Trevor Childers and Jacob Bridges
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.event.KeyEvent;

class PongWorldTest {

	// ball examples
	Ball b1 = new Ball(new Posn(40, 50), 10, new Posn (5,5));
	Ball b2 = new Ball(new Posn(50, 60), 10, new Posn (5,5));
	Ball b3 = new Ball(new Posn(100, 200), 10, new Posn (5,5));
	Ball b4 = new Ball(new Posn(105, 205),10, new Posn (5,5));
	Ball b5 = new Ball(new Posn(100, 5), 10, new Posn(10, -1));  // bouncing off top of window
	Ball b6 = new Ball(new Posn(350, 560), 10, new Posn(10, 10));
	Ball b7 = new Ball(new Posn(100, 400), 10, new Posn(25, -10));
	Ball b8 = new Ball(new Posn(100, 5), 10, new Posn(10, 5));
	Ball b9 = new Ball(new Posn(500, 275), 10, new Posn(-5, 20));
	Ball b10 = new Ball(new Posn(5, 226), 10, new Posn(-1, 5));  // bouncing off left paddle
	Ball b11 = new Ball(new Posn(400, 595), 10, new Posn(4, 10));  // bouncing off bottom window
	Ball b12 = new Ball(new Posn(770, 226), 10, new Posn(5, 10));  // bouncing off right paddle
	Ball b13 = new Ball (new Posn (770, 370), 10, new Posn (5, 10));
	Ball b14 = new Ball (new Posn (770, 370), 10, new Posn (5, 0)); 

	// paddle examples
	Paddle paddleLeft = new Paddle(0, 225, 25, 150);
	Paddle paddleRight = new Paddle(775, 225, 25, 150);

	// paddle updated examples
	Paddle paddleRightUpdateDown = new Paddle(775, 225, 25, 150, 10);
	Paddle paddleLeftUpdateDown = new Paddle(0, 225, 25, 150, 10);
	Paddle paddleLeftUpdateUp = new Paddle(0, 225, 25, 150, -10);
	Paddle paddleRightUpdateUp = new Paddle(775, 225, 25, 150, -10);
	
	// score examples
	ScoreData leftScore = new ScoreData("Trevor", 0);
	ScoreData rightScore = new ScoreData("Jacob", 0);
	
	// score board example
	ScoreBoard sb = new ScoreBoard();

	// world examples
	PongWorld w1 = new PongWorld(paddleLeft, paddleRight, b1, leftScore, rightScore, sb);
	PongWorld w2 = new PongWorld(paddleLeft, paddleRight, b5, leftScore, rightScore, sb);
	PongWorld w3 = new PongWorld(paddleLeft, paddleRight, b3, leftScore, rightScore, sb);
	PongWorld w4 = new PongWorld(paddleLeft, paddleRight, b10, leftScore, rightScore, sb);
	PongWorld w5 = new PongWorld(paddleLeft, paddleRight, b12, leftScore, rightScore, sb);
	PongWorld w6 = new PongWorld(paddleLeft, paddleRight, b14, leftScore, rightScore, sb);

	// updated worlds examples
	PongWorld w1UpdatedPaddleRightUP = new PongWorld(paddleLeft, paddleRightUpdateUp, b1, leftScore, rightScore, sb);
	PongWorld w1UpdatedPaddleRightDown = new PongWorld(paddleLeft, paddleRightUpdateDown, b1, leftScore, rightScore, sb);
	PongWorld w1UpdatedPaddleLeftDown = new PongWorld(paddleLeftUpdateDown, paddleRight, b1, leftScore, rightScore, sb);
	PongWorld w1UpdatedPaddleLeftUp = new PongWorld(paddleLeftUpdateUp, paddleRight, b1, leftScore, rightScore, sb);
	PongWorld w3UpdatedBall = new PongWorld(paddleLeft, paddleRight, b4, leftScore, rightScore, sb);

	// Posn examples
	Posn p1 = new Posn(5, 5);
	Posn p2 = new Posn(10, 1);
	Posn p3 = new Posn(10, -10);
	Posn p4 = new Posn(25, -10);
	Posn p5 = new Posn(10, 5);
	Posn p6 = new Posn(4, -10);
	Posn p7 = new Posn(-25, 10);

	@Test
	void testMoveBall() {
		assertEquals(new Ball(new Posn(45, 55), 10, new Posn(5,5)),  b1.ballMove());
		assertEquals(new Ball(new Posn(55, 65), 10, new Posn(5,5)), b2.ballMove());
		assertEquals(new Ball(new Posn(105, 205), 10, new Posn(5,5)), b3.ballMove());
		assertEquals(new Ball(new Posn(110, 10), 10, new Posn(10, 5)), b8.ballMove());
		assertEquals(new Ball(new Posn(125, 390), 10, new Posn(25, -10)), b7.ballMove());
		assertEquals(new Ball(new Posn(360, 570), 10, new Posn(10, 10)), b6.ballMove());
		assertEquals(new Ball(new Posn(495, 295), 10, new Posn(-5, 20)), b9.ballMove());
	}

	@Test
	void testupdate() {
		assertEquals(new PongWorld(paddleLeft, paddleRight, new Ball(new Posn(105, 205), 10, new Posn(5,5)), leftScore, rightScore, sb), w3.update());
		assertEquals(new PongWorld(paddleLeft, paddleRight, new Ball(new Posn(5, 226), 10, new Posn(1, 5)), new ScoreData("Trevor", 1), new ScoreData("Jacob", 0), sb), w4.update());  // ball bouncing off left paddle
		assertEquals(new PongWorld(paddleLeft, paddleRight, new Ball(new Posn(770, 226), 10, new Posn(-5, 10)), new ScoreData("Trevor", 0), new ScoreData("Jacob", 1), sb), w5.update());  // ball bouncing off Right paddle
		assertEquals(new PongWorld(paddleLeft, paddleRight, new Ball(new Posn(770, 370), 10, new Posn(-5, 0)), new ScoreData("Trevor", 0), new ScoreData("Jacob", 1), sb), w6.update());
		
	}

	@Test
	void testkeyPressed() {
		assertEquals(w1UpdatedPaddleRightUP, w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.UP)));   // right paddle up
		assertEquals(w1UpdatedPaddleRightDown, w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.DOWN)));   // right paddle down
		assertEquals(w1UpdatedPaddleLeftUp, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 'w', 'w')));   // left paddle up
		assertEquals(w1UpdatedPaddleLeftDown, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 's', 's')));   // left paddle down
		
		assertEquals(w1, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 'x', 'x'))); // do nothing if x is pressed 
		assertEquals(w1, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.BACKSPACE))); // do nothing if backspace is pressed 
	}
	
	@Test
	void testKeyReleased() {
		assertEquals (w1UpdatedPaddleRightUP, w1.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, '\0', PApplet.DOWN))); // right paddle stops going down
		assertEquals (w1UpdatedPaddleRightDown, w1.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, '\0', PApplet.UP))); // right paddle stops going up 
		assertEquals (w1UpdatedPaddleLeftDown, w1.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, 'w', 'w'))); // left paddle stops going up
		assertEquals (w1UpdatedPaddleLeftUp, w1.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, 's', 's'))); // left paddle stops going down
	}
	@Test 
	void updateSpeedDirection() { 
		assertEquals(p1, b1.updateSpeedDirection(new Posn(5, 5)));
		assertEquals(p2, b5.updateSpeedDirection(new Posn(10, -1)));  // y flips sign after bouncing off the top window
		assertEquals(p3, b6.updateSpeedDirection(new Posn(10, -10)));
		assertEquals(p4, b7.updateSpeedDirection(new Posn(25, -10)));
		assertEquals(p5, b8.updateSpeedDirection(new Posn(10, 5)));
		assertEquals(p6, b11.updateSpeedDirection(new Posn(4, 10)));  // y slips sign after bouncing off the bottom window
	}

	@Test
	void ballBounce() {

		assertEquals( true, b14.hitPaddleRight(paddleRight));
		assertEquals( true, b10.hitPaddleLeft(paddleLeft));
		assertEquals (false, b14.hitPaddleLeft(paddleLeft)); 
		assertEquals (false, b1.hitPaddleRight(paddleRight)); 

		assertEquals(new Ball(new Posn(40, 50), 10, new Posn(-5, 5)), b1.ballBounce());
		assertEquals(new Ball(new Posn(100, 5), 10, new Posn(-10, -1)), b5.ballBounce());  // x flips sign
		assertEquals(new Ball(new Posn(500, 275), 10, new Posn(5, 20)), b9.ballBounce());
		assertEquals(new Ball(new Posn(100, 5), 10, new Posn(-10, 5)), b8.ballBounce());

	}
	
	@Test
	void TestPosnClass() {
		// getX
		assertEquals(5, p1.getX());
		assertEquals(-25, p7.getX());
		
		// getY
		assertEquals(1, p2.getY());
		assertEquals(-10, p3.getY());	
	
		// translate
		assertEquals(new Posn(15, 6), p1.translate(p2));
		assertEquals(new Posn(20, -9), p2.translate(p3));
		assertEquals(new Posn(-21, 0), p6.translate(p7));
	}
	
	@Test
	void TestScoreData() {
		assertEquals(new ScoreData("Jacob", 1), rightScore.addToRight(b12, paddleRight));
		assertEquals(new ScoreData("Trevor", 1), leftScore.addToLeft(b10, paddleLeft));
		assertEquals(new ScoreData("Jacob", 0), rightScore.addToLeft(b1, paddleLeft));
		assertEquals(new ScoreData("Trevor", 0), leftScore.addToRight(b2, paddleRight)); 
	}

}
