/* @author Trevor Childers and Jacob Bridges */


import java.util.Objects;
import processing.core.PApplet;
import processing.event.KeyEvent;

/*
 * represents the state of a pong game.
 */
public class PongWorld implements IWorld {

	Paddle paddleLeft;
	Paddle paddleRight;
	Ball ball;
	
	public PongWorld(Paddle paddleLeft, Paddle paddleRight, Ball ball) {
		super();
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
		this.ball = ball;
	}

	public PApplet draw(PApplet w) { 
		w.background(42);
		w.fill(255);
		w.circle(ball.x, ball.y, 20);
		w.rect(0, paddleLeft.y, 25, 150);
		w.rect(775, paddleRight.y, 25, 150);
		return w; 	
}
	/*
	 * represents an updated version of the pongWorld
	 */
	public IWorld update() { 
		return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballMove());
	}
	
	public PongWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new PongWorld(this.paddleLeft, new Paddle(this.paddleRight.x, this.paddleRight.y - 10, this.paddleRight.height, this.paddleRight.width), this.ball);
		}
		else if (kev.getKeyCode() == PApplet.DOWN) {
			return new PongWorld(this.paddleLeft, new Paddle(this.paddleRight.x, this.paddleRight.y + 10, this.paddleRight.height, this.paddleRight.width), this.ball);
		}
		else if (kev.getKey() == 'w') {
			return new PongWorld(new Paddle(this.paddleLeft.x, this.paddleLeft.y - 10, this.paddleLeft.height, this.paddleLeft.width), this.paddleRight, this.ball);
		}
		else if (kev.getKey() == 's') {
			return new PongWorld(new Paddle(this.paddleLeft.x, this.paddleLeft.y + 10, this.paddleLeft.height, this.paddleLeft.width), this.paddleRight, this.ball);
		}
		else return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ball, paddleLeft, paddleRight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PongWorld other = (PongWorld) obj;
		return Objects.equals(ball, other.ball) && Objects.equals(paddleLeft, other.paddleLeft)
				&& Objects.equals(paddleRight, other.paddleRight);
	}

	@Override
	public String toString() {
		return "PongWorld [paddleLeft=" + paddleLeft + ", paddleRight=" + paddleRight + ", ball=" + ball + "]";
	}
	
}

/*
 * represents the paddle on the left side of the screen
 */
class Paddle  {
	int x;
	int y;
	int height;
	int width;
	
	public Paddle(int x, int y, int height, int width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	@Override
	public int hashCode() {
		return Objects.hash(height, width, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paddle other = (Paddle) obj;
		return height == other.height && width == other.width && x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Paddle [x=" + x + ", y=" + y + ", height=" + height + ", width=" + width + "]";
	}
	
}

class Ball {
	int x;
	int y;
	int diameter;
	int speed;
	
	public Ball(int x, int y, int diameter, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.speed = speed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diameter, speed, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ball other = (Ball) obj;
		return diameter == other.diameter && speed == other.speed && x == other.x && y == other.y;
	}
	
	Ball ballMove() {
		return new Ball(this.x + speed, this.y + speed, this.diameter, this.speed);
	}

	@Override
	public String toString() {
		return "Ball [x=" + x + ", y=" + y + ", diameter=" + diameter + ", speed=" + speed + "]";
	}
	
}
