package donkeykong;
import java.awt.*;

public class Background extends Rectangle {
	public Background(int x2, int y2) { // constructor
		setBounds(x2, y2, 1200, 1200); //size of background (1200, 1200)
	}

	public void draw(Graphics g) {
		g.setColor(Color.black); //sets the background colour to black
		g.fillRect((int) x, (int) y, width, height); //a filled rectangle is drawn
	}
}
