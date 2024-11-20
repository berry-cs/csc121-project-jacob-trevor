/* 
 * @author Trevor Childers and Jacob Bridges 
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import processing.core.PApplet;
import processing.event.KeyEvent;

/*
 * represents the state of a pong game.
 */
public class PongWorld implements IWorld {

	private Paddle paddleLeft;
	private Paddle paddleRight;
	private Ball ball;
	private ScoreDataPair score;
	
	//private ScoreData leftScore;
	//private ScoreData rightScore;
	
	//private  ScoreBoard sb;
	
	PongWorld(Paddle paddleLeft, Paddle paddleRight, Ball ball, ScoreDataPair score) {
		super();
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
		this.ball = ball;
		this.score = score;
	}

	public PApplet draw(PApplet w) { 
		w.background(42);
		w.fill(255);
		w.circle(ball.getLoc().getX(), ball.getLoc().getY(), 20);
		w.rect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
		w.rect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		w.textSize(25);
		w.text(score.getLeftName() + ":", 50, 50);
		w.text(score.getRightName() + ":", 700, 50);
		w.textSize(50);
		w.text(score.getLeftScore(), 45, 100);
		w.text(score.getRightScore(), 695, 100);
		return w; 	
	}

	/*
	 * represents an updated version of the PongWorld
	 */
	public IWorld update() { 
		if (this.ball.hitPaddleLeft(this.paddleLeft) && this.ball.getSpeed().getX() < 0) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.score.addToLeft(ball, paddleLeft));
		} else if (this.ball.hitPaddleRight(this.paddleRight) && this.ball.getSpeed().getX() > 0 ) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.score.addToRight(ball, paddleRight));
		} else if (this.ball.getLoc().getX() > 800 || this.ball.getLoc().getX() < 0) {
			
			//sb.recordAScore(leftScore);
			//sb.recordAScore(rightScore);
			//sb.saveToFile();
			
			// return new ResetWorld();
			return new ResetWorld(this);
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
			
			return new PongWorld(this.paddleLeft, this.paddleRight, new Ball( new Posn(400, 300), 20, new Posn (-5, 5)), this.score); 
		}
		else return this;
	}
	
	/*
	 * stops player movement once the keys are released
	 * this allows for smoother player movement
	 */
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
	
	/*
	 * writes the score data from this game to an output file
	 */
	public void saveScore() {

		try {
			PrintWriter pw = new PrintWriter(new File("output.txt"));

			this.score.writeToFile(pw);

			pw.close();
		} catch (IOException exp) {
			System.out.println("Problem Saving Score: " + exp.getMessage());
		}
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