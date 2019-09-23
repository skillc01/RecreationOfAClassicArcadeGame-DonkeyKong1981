package donkeykong;
import java.awt.*;
import java.awt.event.*;

public class Player {
	public static boolean jump; 
	public static boolean fall; 
	protected int direction = 1;
	protected int leftspriteint = 0;
	public boolean onLadder; 
	protected static double x;
	protected static double y;
	protected static int w;
	protected int h;
	protected double ms = 5; // movement speed
	protected double xvel = ms;
	protected double js = 6; // jumping speed
	protected double yvel = js; // current jumping speed
	protected double downvelocity = 5; // variable controlling falling
	protected double currentfallvel = 1; // current downvelocity 
	
	public void keyPressed(int k) { //make flow more smoothly
		if (k == KeyEvent.VK_LEFT) {
			if (jump) {
				x = x - 30;
			}
			x = x - 6;
			direction = -1;
			leftspriteint = 1;
		}
		
		if (k == KeyEvent.VK_RIGHT) {
			if (jump) {
				x = x + 30;
			}
			x = x + 6; // speed of right movement
			direction = 1; 
			leftspriteint = 0;
		}

		if (k == KeyEvent.VK_UP) {
			jump = true;
		}
	}

	public void keyReleased(int k) {	}

	public Player(int w, int h) {  //POSITION OF MARIO
		x = 100; // where horizontally the player character will start
		y = 700; // where vertically the player character will start
		this.w = w;
		this.h = h;
	}

	public void draw(Graphics g) { //draws graphics opposite way if player going left
		g.setColor(Color.blue);//shoes of Player character
		g.fillRect((int) x+(leftspriteint*2), (int) y+13, w/3, h/2); 
		g.fillRect((int) x+20-(leftspriteint*2), (int) y+13, w/3, h/2); 
		g.setColor(Color.red);//red of Player character
		g.fillRect((int) x-2+(leftspriteint*4), (int) y+10, w/4, h/2); 
		g.fillRect((int) x+10+(leftspriteint*4), (int) y+10, w/4, h/2); 
		g.fillRect((int) x+20+(leftspriteint*4), (int) y+10, w/4, h/2); 
		g.fillRect((int) x, (int) y+5, w, h/2); 
		g.setColor(Color.pink);//head of Player character
		g.fillRect((int) x+8-(leftspriteint*2), (int) y-15, w-10, h-10);
		g.setColor(Color.red);//hat of Player character
		g.fillRect((int) x+6-(leftspriteint*5), (int) y-15, w-2, h/2-12);
		g.fillRect((int) x+6+(leftspriteint*10), (int) y-17, w/2, h/2-10);
		g.setColor(Color.blue);//arm of Player character
		g.fillRect((int) x+(8*direction)+(leftspriteint*22), (int) y+10, w/4, h/4);
		g.fillRect((int) x+(6*direction)+(leftspriteint*22), (int) y+8, w/4, h/4);
		g.fillRect((int) x+(4*direction)+(leftspriteint*22), (int) y+6, w/4, h/4);
		g.fillRect((int) x+(2*direction)+(leftspriteint*22), (int) y+4, w/4, h/4);
		g.fillRect((int) x, (int) y, w, h/4);
		g.setColor(Color.pink);//hand of Player character
		g.fillRect((int) x+(12*direction)+(leftspriteint*24), (int) y+12, w/4, h/4);
		g.setColor(Color.black);//eyes of Player character
		g.fillRect((int) x+(12*direction)+(leftspriteint*29), (int) y-10, w/10, h/10);
		g.fillRect((int) x+(20*direction)+(leftspriteint*29), (int) y-10, w/10, h/10);

	}

	public void tick(Platform[] platforms) {
		int intx = (int) x; // changes x to an int
		int inty = (int) y; // changes y to an int
		for (int j = 0; platforms.length > j; j++) {
			// bottom, left and right of player collide with platform
			if ((Collide.playerPlatformCollide(
					new Point(intx + (int) AbstractState.yOffset, inty + h + (int) AbstractState.yOffset), platforms[j])
					|| Collide.playerPlatformCollide(
							new Point(intx + w + (int) AbstractState.yOffset, inty + (int) AbstractState.yOffset),
							platforms[j])
					|| Collide.playerPlatformCollide(
							new Point(intx + w + (int) AbstractState.yOffset, inty + h + (int) AbstractState.yOffset),
							platforms[j]))) { 
				fall = false;
				onLadder = false;
				break;
			} else {
				fall = true;	
			}
		}

		if (jump) {
			y = y - yvel / 1.3; // jump height
			yvel = yvel - 0.15;
			if (0 >= yvel) { // if the current jumping speed is 0 or less than 0 , jumping no longer occurs
				jump = false;
				fall = true;
				yvel = js;
			}
		}
				
		if (fall == true) {
			y = y + currentfallvel / 2; // fall speed
			if (downvelocity > currentfallvel) {
				currentfallvel = currentfallvel + 0.1; // the longer the player falls the faster their speed becomes									
			}
		}
		if (fall == false) {
			currentfallvel = 0.2;
		}
	}
	
	//allows when player goes near Pauline to win
	public void tick(Characters[] charas) {
		int inty2 = (int) y; 
		int intx2 = (int) x; 
		for (int i = 0; charas.length > i; i++) {
			// bottom of player collide with Pauline
			if ( Collide.playerPaulineCollide(new Point(intx2 + (int) AbstractState.yOffset + w, 
						inty2 + h + (int) AbstractState.yOffset),
							charas[i])) {
				Stage1.win = 1;
				break;
			} else {
			}
		}
	}
	
	public void tick(Ladder[] ladders) {
		int inty2 = (int) y; 
		int intx2 = (int) x; 
		for (int i = 0; ladders.length > i; i++) {
			// bottom of player collide
			if ((Collide.playerLadderCollide(
					new Point(intx2 + (int) AbstractState.yOffset, inty2 + (int) AbstractState.yOffset), ladders[i])
					|| Collide.playerLadderCollide(
							new Point(intx2 + (int) AbstractState.yOffset + w, inty2 + h + (int) AbstractState.yOffset),
							ladders[i]))) { // right = true;//use for conveyer belt levels
											// yvel = yvel/2+50;
				y -= 1; //allows player to climb ladder
				onLadder = true;
				break;

			} else {
				fall = true;
				AbstractState.yOffset = 0; // may need to change
			}
		}
	}
	
	Rectangle playerRect = new Rectangle((int)Player.x, (int)Player.y, 30, 30);
	Rectangle r2 = new Rectangle(700, 700, 30, 30);
	
	public void tick(EnemyBarrel[] barrels) {
		for (int f = 0; barrels.length > f; f++) {
			// when barrel hits, prints it
			if ((Collide.playerBarrelCollide(new Point(  ((int)Player.x+30), ((int)Player.y+30) ), barrels[f])
					|| (Collide.playerBarrelCollide(new Point(  ((int)Player.x+30), ((int)Player.y) ), barrels[f]))))	
					{
				Stage1.lives = Stage1.lives - 1;
				System.out.print("Barrel Hit");
				break;
			} else {
			}
		}
	}
}
