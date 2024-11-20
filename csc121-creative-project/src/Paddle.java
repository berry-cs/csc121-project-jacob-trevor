
/*
 * represents the paddle on the left side of the screen
 */

import java.util.Objects;

public class Paddle  {
	private int x;
	private int y;
	private int height;
	private int width;
	
	int move;  // 0 , +10, -10
	
	public static final int MAX_MOVEMENT = 10;

	public Paddle(int x, int y, int width, int height ) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	Paddle(int x, int y, int width, int height, int move) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.move = move;
	}
	
	// updates the move amount for this paddle to produce a new one
	public Paddle updateMove(int amt) {
		int newamt = this.move + amt;
		if (newamt > MAX_MOVEMENT) {
			newamt = MAX_MOVEMENT;
		} else if (newamt < - MAX_MOVEMENT) {
			newamt = - MAX_MOVEMENT;
		}
		
		return new Paddle(this.x, this.y, this.width, this.height, newamt);
	}
	
	// produce an updated paddle by moving y in the move amount 
	public Paddle move() {
		if (this.y + this.move < 0 
				|| this.y + this.move > 600 - this.height) {
			return this;
		} else {
			return new Paddle(this.x, this.y + this.move, this.width, this.height, this.move);
		}
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
	
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
