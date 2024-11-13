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
	ScoreData score;

	PongWorld(Paddle paddleLeft, Paddle paddleRight, Ball ball, ScoreData score) {
		super();
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
		this.ball = ball;
		this.score = score;
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
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.score.addToLeft());
		} else if (this.ball.hitPaddleRight(this.paddleRight) && this.ball.speed.x > 0 ) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.score.addToRight());
		} else {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballMove(), this.score);
		}
	}

	/*
	 * returns a new PongWorld where the paddles have moved based on which key has been pressed
	 */
	public PongWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(-10), this.ball, this.score);
		}
		if (kev.getKeyCode() == PApplet.DOWN) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(+10), this.ball, this.score);
		}
		if (kev.getKey() == 'w') {
			return new PongWorld(this.paddleLeft.updateMove(-10), this.paddleRight, this.ball, this.score);
		}
		if (kev.getKey() == 's') {
			return new PongWorld(this.paddleLeft.updateMove(10), this.paddleRight, this.ball, this.score);
		}
		if (kev.getKey() == ' ') {
			
			return new PongWorld(this.paddleLeft, this.paddleRight, new Ball( new Posn(400, 300), 20, new Posn (-5, 5)), this.score);  // reset score ?? TODO
		}
		else return this;
	}
	
	public PongWorld keyReleased(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(10), this.ball, this.score);
		}
		if (kev.getKeyCode() == PApplet.DOWN) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(-10), this.ball, this.score);
		}
		if (kev.getKey() == 'w') {
			return new PongWorld(this.paddleLeft.updateMove(10), this.paddleRight, this.ball, this.score);
		}
		if (kev.getKey() == 's') {
			return new PongWorld(this.paddleLeft.updateMove(-10), this.paddleRight, this.ball, this.score);
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
