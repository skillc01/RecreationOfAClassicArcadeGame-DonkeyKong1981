package donkeykong;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends AbstractState {
	protected Characters chara; //Donkey Kong character only
	public MainMenu(Manager gamestates) {
		super(gamestates);
	}
	String[] choices = { "Yes", " ", "No" }; //the option names
	protected int select = 0;

	public void initial() {
		chara = new Characters(655, 360); //draws Donkey Kong
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
		//option selection/confirming - actions
		if (kp == KeyEvent.VK_ENTER) { //if enter key pressed
			if (select == 0) { // play
				gamestates.stages.push(new Stage1(gamestates));//taken to level
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
		chara.draw(g); // draws the characters
		g.setColor(Color.black);
		g.fillRect(610, 250, 100, 200); //hides Pauline
		g.setFont(new Font("Helvetica", Font.BOLD, 120));
		g.setColor(Color.BLUE);
		g.drawString("DONKEY KONG", 100, 150);
		g.drawString("", 40, 250);
		g.setColor(Color.black);
		g.fillRect(0, 800, 2500, 2500); //fullscreen
		g.fillRect(900, 0, 2500, 2500); //fullscreen
		
		// menu choices and colours
		for (int j = 0; 3 > j; j++) {
			int xpoint[] = {525,545,500,545}; //x co-ords of both selection arrows
		    int ypoint[] = {430,420,430,440};//y co-ords of arrow by "Play"
			int ypoint2[] = {570,560,570,580};//y co-ords of arrow by "Exit"
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
			g.drawString(choices[j], 340, j * 70 + 450);
		}	}
	@Override
	public void tick() { }
}