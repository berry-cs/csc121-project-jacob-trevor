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
		w.textSize(50);
		w.text(score.leftName + ":", 50, 50);
		w.text(score.rightName + ":", 700, 50);
		w.text(score.leftScore, 90, 50);
		w.text(score.rightScore, 740, 50);
		return w; 	
	}

	/*
	 * represents an updated version of the PongWorld
	 */
	public IWorld update() { 
		if (this.ball.hitPaddleLeft(this.paddleLeft) && this.ball.speed.x < 0) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.score.addToLeft(ball, paddleLeft));
		} else if (this.ball.hitPaddleRight(this.paddleRight) && this.ball.speed.x > 0 ) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.score.addToRight(ball, paddleRight));
		} else if (this.ball.loc.x > 800 || this.ball.loc.x < 0) {
			return new StartWorld();
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
		return Objects.hash(paddleLeft, paddleRight, score);
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
		return Objects.equals(paddleLeft, other.paddleLeft) && Objects.equals(paddleRight, other.paddleRight)
				&& Objects.equals(score, other.score);
	}

	@Override
	public String toString() {
		return "PongWorld [paddleLeft=" + paddleLeft + ", paddleRight=" + paddleRight + ", score=" + score + "]";
	}

}