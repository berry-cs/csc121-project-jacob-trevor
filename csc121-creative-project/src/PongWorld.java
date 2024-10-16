/* 
 * @author Trevor Childers and Jacob Bridges 
 */


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
		w.circle(ball.loc.getX(), ball.loc.getY(), 20);
		w.rect(paddleLeft.x, paddleLeft.y, paddleLeft.width, paddleLeft.height);
		w.rect(paddleRight.x, paddleRight.y, paddleRight.width, paddleRight.height);
		return w; 	
	}

	/*
	 * represents an updated version of the PongWorld
	 */
	public IWorld update() { 
		if (this.ball.hitPaddleLeft(this.paddleLeft) && this.ball.speed.x < 0) {
			return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballBounce());
		} else if (this.ball.hitPaddleRight(this.paddleRight) && this.ball.speed.x > 0 ) {
			return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballBounce());
		} else {
			return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballMove());
		}
	}

	/*
	 * returns a new PongWorld where the paddles have moved based on which key has been pressed
	 */
	public PongWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP && paddleRight.y > 0) {
			return new PongWorld(this.paddleLeft, new Paddle(this.paddleRight.x, this.paddleRight.y - 10, this.paddleRight.width, this.paddleRight.height), this.ball);
		}
		if (kev.getKeyCode() == PApplet.DOWN && paddleRight.y < 600 - paddleRight.height) {
			return new PongWorld(this.paddleLeft, new Paddle(this.paddleRight.x, this.paddleRight.y + 10, this.paddleRight.width, this.paddleRight.height), this.ball);
		}
		if (kev.getKey() == 'w'&& paddleLeft.y > 0) {
			return new PongWorld(new Paddle(this.paddleLeft.x, this.paddleLeft.y - 10, this.paddleLeft.width, this.paddleLeft.height), this.paddleRight, this.ball);
		}
		if (kev.getKey() == 's' && paddleLeft.y < 600 - paddleLeft.height) {
			return new PongWorld(new Paddle(this.paddleLeft.x, this.paddleLeft.y + 10, this.paddleLeft.width, this.paddleLeft.height), this.paddleRight, this.ball);
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

	public Paddle(int x, int y, int width, int height ) {
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

/*
 * represents the ball within a Pong game
 */
class Ball {
	Posn loc;
	int diameter;
	Posn speed;

	Ball(Posn loc, int diameter, Posn speed) {
		super();
		this.loc = loc;
		this.diameter = diameter;
		this.speed = speed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diameter, loc, speed);
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
		return diameter == other.diameter && Objects.equals(loc, other.loc) && Objects.equals(speed, other.speed);
	}

	/** tell if this ball has hit the left paddle */
	public Boolean hitPaddleLeft(Paddle paddle) {

		if (paddle.y < loc.y && 
				paddle.y + paddle.height > loc.y && 
				Math.abs(loc.x - paddle.x) < 40)
		{
			
			return true;
		}

		else return false;
	}
	
	/** tell if this ball has hit the Right paddle */
	public Boolean hitPaddleRight(Paddle paddle) {

		if (paddle.y < loc.y && 
				paddle.y + paddle.height > loc.y && 
				Math.abs(loc.x - paddle.x) < 11)
		{
			
			return true;
		}

		else return false;
	}

	/** updates the location of the ball according to its current speed direction
	 * and also updates the speed to bounce off the top and bottom edges
	 */
	public Ball ballMove() {
		return new Ball(this.loc.translate(this.speed) , this.diameter, updateSpeedDirection(this.speed));
	}

	/*
	 * updates the x value of the balls speed to bounce off of the paddles
	 */
	public Ball ballBounce( ) {
		return new Ball(this.loc, this.diameter, bounceX(this.speed));
	}

	/**
	 * flip the x value of the speed if hitPaddle returns true
	 * 
	 */
	public Posn bounceX(Posn speed) { 
		return new Posn(-speed.x,  speed.y); 
	}

	/** 
	 * produces a flipped y value for the speed if it's at a top or bottom boundary
	 */
	public Posn updateSpeedDirection(Posn speed) {
		if (this.loc.y <= 0 + this.diameter && speed.y < 0) {  
			return new Posn(speed.x,  -speed.y); 
		}

		else if (this.loc.y >= 600 - this.diameter && speed.y > 0) {
			return new Posn(speed.x,  -speed.y); 
		}

		else return speed;
	}

	@Override
	public String toString() {
		return "Ball [loc=" + loc + ", diameter=" + diameter + ", speed=" + speed + "]";
	}

}

/** 
 * represents a Posn with an x and y value 
 */
class Posn {
	int x;
	int y;

	Posn(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/** moves this posn by the given offsets */
	public Posn translate(Posn offset) {
		return new Posn( this.x + offset.x, this.y + offset.y );
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posn other = (Posn) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Posn [x=" + x + ", y=" + y + "]";
	}

} 
