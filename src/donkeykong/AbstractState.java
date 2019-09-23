package donkeykong;
import java.awt.*;
public abstract class AbstractState {// abstract class
	public Manager gamestates; 
	public static double yOffset, xOffset;
	public AbstractState(Manager gamestates) {//constructor
		this.gamestates = gamestates;
		initial();
	}
	// stages will extend these :
	public abstract void initial();
	public abstract void tick(); 
	protected abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
