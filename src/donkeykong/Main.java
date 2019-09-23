package donkeykong;

import javax.swing.*;
//https://cseegit.essex.ac.uk/ce301/skillman_c/capstone_project.git
//git@cseegit.essex.ac.uk:ce301/skillman_c/capstone_project.git
import java.awt.BorderLayout;
//class for creating the main JFrame
public class Main {

	public static void main(String[] args) {
		JFrame gameframe;

		gameframe = new JFrame("Donkey Kong 1981");//name on the game window
		//terminates program on closed
		gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameframe.setLayout(new BorderLayout());
		gameframe.add(new GamePanel(), (BorderLayout.CENTER));
		gameframe.setVisible(true);//makes frame visible
		gameframe.pack();//resizes the frame to the panel size
		gameframe.setLocationRelativeTo(null);
		gameframe.setResizable(true);//cannot resize game

	}

}
