package donkeykong;

import java.awt.*;
import java.awt.event.KeyEvent;
//also add you win
public class GameOverScreen extends AbstractState {
	boolean showscore = false;
	public GameOverScreen(Manager gamestates) {
		super(gamestates);
	}

	String[] choices2 = { "Restart", " ", "Exit Game" }; 
	protected int select = 0;
	protected Characters chara; //characters (Pauline)
	protected Characters chara2; //characters (DK)
	
	public void initial() {
		chara = new Characters(120, 700); //draws characters in Characters Class onto end menu
		chara2 = new Characters(680, 700); //draws characters in Characters Class onto end menu	
	}
	
	public void keyPressed(int kp) {
		if (kp == KeyEvent.VK_DOWN) { //if down arrow key pressed
			select = select + 1; //goes through the options
			if (select >= 3) {
				select = 2;
			}
		} else if (kp == KeyEvent.VK_UP) { //if up arrow key pressed
			select = select - 1; //goes through the options 
			if (2 > select) {
				select = 0;
			}
		}
		if (kp == KeyEvent.VK_ENTER) { 
			if (select == 0) { //restart
				gamestates.stages.push(new Stage1(gamestates));
				Stage1.lives = 3; 
				Stage1.win = 0; 
				Stage1.score = 0; 
			} else if (select == 2) { // exit choice on menu
				System.exit(0);
			}
		}
	}

	public void keyReleased(int kr) {	}

	protected void draw(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, 1900, 800); //size of black background
					
	    g.setFont(new Font("Helvetica", Font.BOLD, 55));
		g.setColor(Color.blue);
		g.drawString("FINAL SCORE:", 85, 220);
		g.setColor(Color.white);
		g.drawString("" + Stage1.score, 505, 220);
		
		
		if (Stage1.win == 1) {
			g.setFont(new Font("Helvetica", Font.BOLD, 120));
			g.setColor(Color.yellow);
		    g.drawString("  YOU WIN!", 85, 150);
		    chara.draw(g); // draws the characters
		    
		    int xpoint4[] = {(400/2)-110, (360/2)-110, (375/2)-110, (400/2)-110, (425/2)-110, (440/2)-110}; // ..  ..  far left point, bttom point
		    int xpoint3[] = {400/2, 360/2, 375/2, 400/2, 425/2, 440/2}; // ..  ..  far left point, bttom point
		    int ypoint3[] = {680, 650, 630, 650, 630, 650};
		    g.setColor(Color.pink); 
		    g.fillPolygon(xpoint3, ypoint3, 6);
		    g.fillPolygon(xpoint4, ypoint3, 6);
		    g.setColor(Color.white);
		    g.setFont(new Font("Helvetica", Font.BOLD, 30));
		    g.drawString("  Thank you for saving me!", 230, 700);
		    g.setColor(Color.black);
			g.fillRect(0, 800, 2500, 2500); //fullscreen
			g.fillRect(900, 0, 2500, 2500); //fullscreen    
		}
		if (Stage1.lives == 0) {
			g.setFont(new Font("Helvetica", Font.BOLD, 120));
			g.setColor(Color.yellow);
			g.drawString("GAME OVER", 80, 150);
			chara2.draw(g);
			//makes DK smile on GAME OVER screen
			g.setColor(Color.pink);
			g.fillRect(438, 640, 4, 5);
			g.setColor(Color.red);
			g.fillRect(437, 638, 4, 3);
			g.fillRect(440, 636, 4, 3);
			//Pauline tear on GAME OVER screen
			g.setColor(Color.cyan);
			g.fillRect(700, 638, 2, 4);
			g.setColor(Color.black);
			g.fillRect(0, 800, 2500, 2500); //fullscreen
			g.fillRect(900, 0, 2500, 2500); //fullscreen		
		}
		// menu choices and colours
		for (int j = 0; 3 > j; j++) {
			int xpoint[] = {605, 625, 580, 625}; ////x co-ords for both arrows
		    int ypoint[] = {300, 290, 300, 310};//y co-ords of arrow by "Restart"
			int ypoint2[] = {470, 460, 470, 480};//y co-ords of arrow by "Exit Game"
					
			if (select == j) {
				g.setColor(Color.white); 
                g.fillPolygon(xpoint, ypoint2, 4);
				g.setColor(Color.black); 
			    g.fillPolygon(xpoint, ypoint, 4);
				g.setColor(Color.cyan);
				
			} else {
				g.setColor(Color.white); 
			    g.fillPolygon(xpoint, ypoint, 4);
				g.setColor(Color.black); 
                g.fillPolygon(xpoint, ypoint2, 4);
				g.setColor(Color.red);
			}
			g.setFont(new Font("Helvetica", Font.BOLD, 60));

			g.drawString(choices2[j], 768 / 3, j * 80 + 320);
			
		}
		
	}
	
	@Override
	public void tick() {	}

}
