/* @author Trevor Childers and Jacob Bridges */


import java.util.Objects;

import processing.core.PApplet;

/*
 * represents the state of a pong game.
 */
public class PongWorld implements IWorld {

	IWorld paddleLeft;
	IWorld paddleRight;
	IWorld ball;
	
	public PongWorld(IWorld paddleLeft, IWorld paddleRight, IWorld ball) {
		super();
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
		this.ball = ball;
	}
	
	public PApplet draw(PApplet w) { 
		w.background(42);
		w.fill(255);
		w.circle(ballX, ballY, 20);
		w.rect(0, 225, 25, 150);
		w.rect(775, 225, 25, 150);
		return w; 
		
}
	
//	/*
//	 * represents an updated version of the pongWorld
//	 */
//	public IWorld update() { 
//		return new PongWorld(this.paddleLeft, this.paddleRight, this.ballMove(ballX + 1, ballY + 1, 20, 2)); }

}

/*
 * represents the paddle on the left side of the screen
 */
class paddleLeft implements IWorld {
	int x;
	int y;
	int height;
	int width;
	
	public paddleLeft(int x, int y, int height, int width) {
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
		paddleLeft other = (paddleLeft) obj;
		return height == other.height && width == other.width && x == other.x && y == other.y;
	}
}

/*
 * represents the paddle on the right side of the screen
 */
class paddleRight implements IWorld {
	int x;
	int y;
	int height;
	int width;
	
	public paddleRight(int x, int y, int height, int width) {
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
		paddleRight other = (paddleRight) obj;
		return height == other.height && width == other.width && x == other.x && y == other.y;
	}
	
}

class ball implements IWorld {
	int x;
	int y;
	int diameter;
	int speed;
	
	public ball(int x, int y, int diameter, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		// this.speed = speed;
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
		ball other = (ball) obj;
		return diameter == other.diameter && speed == other.speed && x == other.x && y == other.y;
	}
	
	IWorld ballMove(ball ball) {
		return new ball(x + 1, y + 1, 20, 2);
	}
	
}
