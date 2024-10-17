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
		w.circle(ball.loc.getX(), ball.loc.getY(), 20);
		w.rect(0, paddleLeft.y, 25, 150);
		w.rect(775, paddleRight.y, 25, 150);
		return w; 	
	}
	
	/*
	 * represents an updated version of the pongWorld
	 */
	public IWorld update() { 
		if (this.ball.hitPaddle(this.paddleLeft) && this.ball.speed.x < 0) {
			return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballBounce());
		} else if (this.ball.hitPaddle(this.paddleRight) && this.ball.speed.x > 0 ) {
			return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballBounce());
		} else {
			return new PongWorld(this.paddleLeft, this.paddleRight, this.ball.ballMove());
		}
	}

	public PongWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP && paddleRight.y > 0 + paddleRight.height/10) {
			return new PongWorld(this.paddleLeft, new Paddle(this.paddleRight.x, this.paddleRight.y - 10, this.paddleRight.height, this.paddleRight.width), this.ball);
		}
		 if (kev.getKeyCode() == PApplet.DOWN && paddleRight.y < 600 - paddleRight.height*3) {
			return new PongWorld(this.paddleLeft, new Paddle(this.paddleRight.x, this.paddleRight.y + 10, this.paddleRight.height, this.paddleRight.width), this.ball);
		}
		 if (kev.getKey() == 'w'&& paddleLeft.y > 0 + paddleLeft.height/10) {
			return new PongWorld(new Paddle(this.paddleLeft.x, this.paddleLeft.y - 10, this.paddleLeft.height, this.paddleLeft.width), this.paddleRight, this.ball);
		}
		 if (kev.getKey() == 's' && paddleLeft.y < 600 - paddleLeft.height*6) {
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
	Posn loc;
	int diameter;
	Posn speed;
	

	@Override
	public int hashCode() {
		return Objects.hash(diameter, loc, speed);
	}

	
	/** tell if this ball has hit the given paddle */
	public Boolean hitPaddle(Paddle paddle) {
		
		if (paddle.y < loc.y && 
				 paddle.y + paddle.height > loc.y && 
					Math.abs(loc.x - paddle.x) < 25) 
					{
		
			return true;
		}
	
		else return false;
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

	Ball(Posn loc, int diameter, Posn speed) {
		super();
		this.loc = loc;
		this.diameter = diameter;
		this.speed = speed;
	}

	
	/** updates the location of the ball according to its current speed direction
	 * and also updates the speed to bounce off the top and bottom edges
	 */
	Ball ballMove() {
		return new Ball(this.loc.translate(this.speed) , this.diameter, updateSpeedDirection(this.speed));
	}
	
	Ball ballBounce( ) {
		return new Ball(this.loc.translate(this.speed), this.diameter, bounceX(this.speed));
	}
	
	/** 
	 * produces a flipped y value for the speed if it's at a top or bottom boundary
	 */
	Posn updateSpeedDirection(Posn speed) {
		if (this.loc.y <= 0 + this.diameter && speed.y < 0) {  
			return new Posn(speed.x,  -speed.y); 
		}
		
		else if (this.loc.y >= 600 - this.diameter && speed.y > 0) {
			return new Posn(speed.x,  -speed.y); 
		}
		
		else return speed;
	}
	
	/**
	 * flip the x value of the speed if hitPaddle returns true
	 * 
	 */
	public Posn bounceX(Posn speed) { 
		return new Posn(-speed.x,  speed.y); 
	}

	@Override
	public String toString() {
		return "Ball [loc=" + loc + ", diameter=" + diameter + ", speed=" + speed + "]";
	}

}

class Posn {
	int x;
	int y;

	public Posn(int x, int y) {
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
