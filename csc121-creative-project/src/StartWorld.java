import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {
	
	ArrayList<ScoreData> Scoreboard;
	
	public PApplet draw(PApplet w) { 
		w.background(0,190, 190);
		w.fill(255);
		w.textSize(100);
		w.text("Click to start!", w.width / 6, w.height / 4);
		this.Scoreboard = new ArrayList<ScoreData>();
		
		return w; 	
	}
	
	public IWorld mouseClicked(MouseEvent mev) {
		String leftName = JOptionPane.showInputDialog("Left player name: ");
		String rightName =  JOptionPane.showInputDialog("Right player name: ");
		return new PongWorld(new Paddle(0, 200, 25, 150), new Paddle(775, 200, 25, 150),
				new Ball(new Posn(400, 300), 20, new Posn(RandomExclude.randomInt(), 5)),
				new ScoreData(leftName, rightName, 0, 0)
				); 
	}
	
}


