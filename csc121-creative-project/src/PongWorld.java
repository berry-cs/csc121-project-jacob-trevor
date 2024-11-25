/* 
 * @author Trevor Childers and Jacob Bridges 
 */

import java.util.ArrayList;
import java.util.Objects;
import processing.core.PApplet;
import processing.event.KeyEvent;

/*
 * represents a world in which a pong game is played
 */
public class PongWorld implements IWorld {

	private Paddle paddleLeft;
	private Paddle paddleRight;
	private Ball ball;
	
	private ScoreData leftScore;
	private ScoreData rightScore;
	
	private ScoreBoard sb;

	PongWorld(Paddle paddleLeft, Paddle paddleRight, Ball ball, ScoreData leftScore, ScoreData rightScore, ScoreBoard sb) {
		super();
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
		this.ball = ball;
		this.leftScore = leftScore;
		this.rightScore = rightScore;
		this.sb = new ScoreBoard(new ArrayList<ScoreData>());
	}

	/*
	 * draws the main game screen 
	 */
	public PApplet draw(PApplet w) { 
		w.background(42);
		w.fill(255);
		w.circle(ball.getLoc().getX(), ball.getLoc().getY(), 20);
		w.rect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
		w.rect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		w.textSize(25);
		w.text(leftScore.getName() + ":", 50, 50);
		w.text(rightScore.getName() + ":", 700, 50);
		w.textSize(50);
		w.text(leftScore.getScore(), 45, 100);
		w.text(rightScore.getScore(), 695, 100);
		return w; 	
	}

	/*
	 * Updates the state of this pong world based on different features of the game
	 * such as player or ball movement, or the game ending
	 */
	public IWorld update() { 
		// sets the value of the width of the window
		int width = 800;
		if (this.ball.hitPaddleLeft(this.paddleLeft) && this.ball.getSpeed().getX() < 0) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.leftScore.addToLeft(ball, paddleLeft), this.rightScore, this.sb);
		} else if (this.ball.hitPaddleRight(this.paddleRight) && this.ball.getSpeed().getX() > 0 ) {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballBounce(), this.leftScore, this.rightScore.addToRight(ball, paddleRight), this.sb);
		} else if (this.ball.getLoc().getX() > width || this.ball.getLoc().getX() < 0) {
			
			sb.recordAScore(leftScore);
			sb.recordAScore(rightScore);
			sb.saveToFile();
			
			// return new ResetWorld();
			return new ResetWorld();
		} else {
			return new PongWorld(this.paddleLeft.move(), this.paddleRight.move(), this.ball.ballMove(), this.leftScore, this.rightScore, this.sb);
		}
	}

	/*
	 * returns a new PongWorld where the paddles have moved based on which key has been pressed
	 */
	public PongWorld keyPressed(KeyEvent kev) {
		
		int paddlespeeddown = -10;
		int paddlespeedup = 10; 
		int diameter = 20; 
		if (kev.getKeyCode() == PApplet.UP) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(paddlespeeddown), this.ball, this.leftScore, this.rightScore, this.sb);
		}
		if (kev.getKeyCode() == PApplet.DOWN) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(paddlespeedup), this.ball, this.leftScore, this.rightScore, this.sb);
		}
		if (kev.getKey() == 'w') {
			return new PongWorld(this.paddleLeft.updateMove(paddlespeeddown), this.paddleRight, this.ball, this.leftScore, this.rightScore, this.sb);
		}
		if (kev.getKey() == 's') {
			return new PongWorld(this.paddleLeft.updateMove(paddlespeedup), this.paddleRight, this.ball, this.leftScore, this.rightScore, this.sb);
		}
		if (kev.getKey() == ' ') {
			
			return new PongWorld(this.paddleLeft, this.paddleRight, new Ball( new Posn(400, 300), diameter, new Posn (-5, 5)), this.leftScore, this.rightScore, this.sb); 
		}
		else return this;
	}
	
	/*
	 * stops player movement once the keys are released
	 * this allows for smoother player movement
	 */
	public PongWorld keyReleased(KeyEvent kev) {
		int paddlespeeddown = -10;
		int paddlespeedup = 10; 
		if (kev.getKeyCode() == PApplet.UP) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(paddlespeedup), this.ball, this.leftScore, this.rightScore, this.sb);
		}
		if (kev.getKeyCode() == PApplet.DOWN) {
			return new PongWorld(this.paddleLeft, this.paddleRight.updateMove(paddlespeeddown), this.ball, this.leftScore, this.rightScore,this.sb);
		}
		if (kev.getKey() == 'w') {
			return new PongWorld(this.paddleLeft.updateMove(paddlespeedup), this.paddleRight, this.ball, this.leftScore, this.rightScore, this.sb);
		}
		if (kev.getKey() == 's') {
			return new PongWorld(this.paddleLeft.updateMove(paddlespeeddown), this.paddleRight, this.ball, this.leftScore, this.rightScore, this.sb);
		}
		else return this;
	}
	
//	/*
//	 * writes the score data from this game to an output file
//	 */
//	public void saveScore() {
//
//		try {
//			PrintWriter pw = new PrintWriter(new File("output.txt"));
//
//			this.leftScore.writeToFile(pw);
//			this.rightScore.writeToFile(pw);
//
//			pw.close();
//		} catch (IOException exp) {
//			System.out.println("Problem Saving Score: " + exp.getMessage());
//		}
//	}

	@Override
	public int hashCode() {
		return Objects.hash(ball, leftScore, paddleLeft, paddleRight, rightScore, sb);
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
		return Objects.equals(ball, other.ball) && Objects.equals(leftScore, other.leftScore)
				&& Objects.equals(paddleLeft, other.paddleLeft) && Objects.equals(paddleRight, other.paddleRight)
				&& Objects.equals(rightScore, other.rightScore) && Objects.equals(sb, other.sb);
	}

	@Override
	public String toString() {
		return "PongWorld [paddleLeft=" + paddleLeft + ", paddleRight=" + paddleRight + ", ball=" + ball
				+ ", leftScore=" + leftScore + ", rightScore=" + rightScore + ", sb=" + sb + "]";
	}

}