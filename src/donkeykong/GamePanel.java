package donkeykong;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class GamePanel extends JPanel implements KeyListener, Runnable { //Panel objects drawn onto
	protected long tTime = 1000000 / 60;//60fps
	protected Thread t;             
	protected boolean running = false;
	protected static int TickCounter;
	protected Manager gamestates; 

	public GamePanel() {
		setPreferredSize(new Dimension(860, 800)); //size of the window of the game
		addKeyListener(this);
		setFocusable(true);
		start();    }        //calls start

	private void start() { // method that will start the game
		running = true;
		t = new Thread(this);
		t.start(); }            //calls the run method

	public void run() { // method that will run the gameloop
		gamestates = new Manager(); 
		long begin, wait;
		while (running = true) { // when the game is running, this ploop controls what happens in the gameloop
			begin = System.nanoTime(); //nano 10^-9
			TickCounter = TickCounter + 1;
			tick();
			repaint(); // responsible for the graphics in the gameloop
			wait = tTime - begin / 1000000;//puts into milliseconds 
			if (wait <= 0) {
				wait = 5;// was 5
			}
			try {
				Thread.sleep(wait);
			} catch (Exception excep) {
				System.out.println("Exception Caught");
			} } }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);  //draws the graphics
		g.clearRect(0, 0, 780, 680); // clears screen before next is drawn
		gamestates.draw(g);     }     //draws level
	
	public void tick() { //updates the logic
		gamestates.tick(); } 

	public void keyPressed(KeyEvent k) {
		gamestates.keyPressed(k.getKeyCode()); }

	public void keyReleased(KeyEvent k) {
		gamestates.keyReleased(k.getKeyCode()); }

	@Override
	public void keyTyped(KeyEvent e) {	}
}
