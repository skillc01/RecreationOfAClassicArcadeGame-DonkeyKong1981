package donkeykong;
import java.awt.*;
import java.util.*;
//class used to update the states in the game
public class Manager {
	public static boolean restarted;
	//whichever state is on top of stack is updated
	public Stack<AbstractState> stages;
	public Manager() { // constructor
		stages = new Stack<AbstractState>();//creates a stack of game states
		stages.push(new MainMenu(this));//adds start menu
	}
	// methods here implemented into the GamePanel class
	//looks at top of stack and updates one at top of stack
	//peek used to look at methods on top of stack 
	public void keyPressed(int P) {
		stages.peek().keyPressed(P); //return value on top
	}
	public void keyReleased(int R) {
		stages.peek().keyPressed(R);
	}
	public void tick() {
		stages.peek().tick();
	}
	public void draw(Graphics g) {
		stages.peek().draw(g);
	}
}
