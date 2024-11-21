
import java.util.Objects;

/*
 * represents a ball object within a pong world
 */
class Ball {
	private Posn loc;
	private int diameter;
	private Posn speed;

	Ball(Posn loc, int diameter, Posn speed) {
		super();
		this.loc = loc;
		this.diameter = diameter;
		this.speed = speed;
	}

	/*
	 * determine if this ball has hit the left paddle 
	 */
	public Boolean hitPaddleLeft(Paddle paddle) {

		if (paddle.getY() < loc.getY() && 
				paddle.getY() + paddle.getHeight() > loc.getY() && 
				Math.abs(loc.getX() - paddle.getX()) < 40)
		{
			
			return true;
		}

		else return false;
	}
	
	/*
	 *  determines if this ball has collided with the right paddle
	 */
	public Boolean hitPaddleRight(Paddle paddle) {

		if (paddle.getY() < loc.getY() && 
				paddle.getY() + paddle.getHeight() > loc.getY() && 
				Math.abs(loc.getX() - paddle.getX()) < 11)
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
		return new Posn(-speed.getX(),  speed.getY()); 
	}

	/*
	 * produces a flipped y value for the speed if it's at a top or bottom boundary
	 */
	public Posn updateSpeedDirection(Posn speed) {
		if (this.loc.getY() <= 0 + this.diameter && speed.getY() < 0) {  
			return new Posn(speed.getX(),  -speed.getY()); 
		}

		else if (this.loc.getY() >= 600 - this.diameter && speed.getY() > 0) {
			return new Posn(speed.getX(),  -speed.getY()); 
		}

		else return speed;
	}
	
	/*
	 * returns the location of this ball
	 */
	public Posn getLoc() {
		return this.loc;
	}
	
	/*
	 * returns the diameter value of this ball
	 */
	public int getDiameter() {
		return this.diameter;
	}
	
	/*
	 * returns the speed value of this ball
	 */
	public Posn getSpeed() {
		return this.speed;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(diameter, loc, speed);
	}

	/*
	 * hash code and equals methods
	 */
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

	/*
	 * to string method 
	 */
	@Override
	public String toString() {
		return "Ball [loc=" + loc + ", diameter=" + diameter + ", speed=" + speed + "]";
	}
}
