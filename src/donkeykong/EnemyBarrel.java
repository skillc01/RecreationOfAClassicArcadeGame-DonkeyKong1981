package donkeykong;
import java.awt.*;
import java.awt.event.*;

public class EnemyBarrel {
	protected int platformlevel = 0;//variable for when barrel direction
	protected boolean left = false; 
	protected boolean right = false;
	protected boolean barrelfall;
	protected double x, y;
	protected static int w;
	protected static int h;
	protected double downvelocity = 6; // variable controlling falling
	protected double currentfallvel = 0.2; // current downvelocity
	Rectangle r1 = new Rectangle((int) x, (int) y, w, h); 
	
    public void draw(Graphics g) {
    	g.setColor(Color.orange);//colour of barrel
		g.fillOval((int) x, (int) y, w, h); 
		g.setColor(Color.DARK_GRAY);//colour of barrel ring
		g.fillOval((int) x+8, (int) y+8, w-12, h-12); 
		g.setColor(Color.orange);//colour of barrel
		g.fillOval((int) x+12, (int) y+12, w-25, h-25); 
		g.setColor(Color.white);//colour of barrel shine
	}

	public EnemyBarrel(int w, int h) {
		x = 240;//where horizontally the enemy will start
		y = 120; //where vertically the barrels will start
		this.w = w;
		this.h = h;
	}

	public void tick(Platform[] platforms) {
		Rectangle r1 = new Rectangle((int) x, (int) y, w, h); 
		int intx = (int) x; // change x to an int
		int inty = (int) y; // change y to an int
		
		for (int j = 0; platforms.length > j; j++) {
        //barrel falls same way as player
			if ((Collide.playerPlatformCollide(
				new Point(intx + (int) AbstractState.yOffset, inty + h + (int) AbstractState.yOffset),platforms[j])
				|| Collide.playerPlatformCollide(new Point(intx + w + (int) AbstractState.yOffset, inty + (int) AbstractState.yOffset),platforms[j])
				|| Collide.playerPlatformCollide(new Point(intx + w + (int) AbstractState.yOffset, inty + h + (int) AbstractState.yOffset),
				platforms[j]))) { // right = true;//use for conveyer belt levels
				barrelfall = false;
				//System.out.println("Pl = " + platformlevel);
				platformlevel = platformlevel +1;
				//System.out.println("Pl = " + platformlevel);
				break;

			} else {
				barrelfall = true;
			}
		}
		
		//cause barrel to go right when it gets to the platform below
		if ((platformlevel > 260) && (1240 > platformlevel)) {
			x = x + 2; //speed of barrel (right)
		}		
		if ((2238 < platformlevel) && (3185 > platformlevel)) {
			x = x + 2;
		}
		if (platformlevel == 4050) { //so when barrels get to the oil drum they are put back to the spawn point
			x = 240;
			y = 90;
			platformlevel = 0;
		}
		
		//causes barrel to go left 
		if ((Player.jump == true) || (Player.fall == true)) {
			x = x - 1; //speed of barrel (1) left
		}

		//causes barrel to fall
		if (barrelfall == true) {
			y = y + currentfallvel / 2; // fall speed
			if (downvelocity > currentfallvel) {
				currentfallvel = currentfallvel + 0.1;
		}
		}
		if (barrelfall == false) {
			currentfallvel = 0.2;
		}
	}

	public boolean contains(Point playerPoint) {
		return false;
	}

}
