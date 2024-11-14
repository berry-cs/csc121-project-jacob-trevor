import java.util.Objects;

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

