
/*
 * represents the ball within a Pong game
 */

import java.util.Objects;

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
