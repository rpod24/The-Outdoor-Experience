import java.awt.Dimension;

import javax.swing.JFrame;

public class TheOutdoorExperience {
	
	JFrame frame;
	GamePanel GamePanel;
	static final int width = 1000;// Sets the width of the game
	static final int height = 1000;// Sets the height of the game
	static final int screenWidth = 1200;// Sets the width of the game
	static final int screenHeight = 1000;// Sets the height of the game
	public static void main(String[] args) {
		new TheOutdoorExperience().setup();
		
	}
	
	TheOutdoorExperience(){
		frame = new JFrame();
		GamePanel = new GamePanel(); 
	}
	
	void setup() {//default setup for the game
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set the program to close when the window is closed
		frame.setVisible(true);//Shows the game
		frame.setSize(new Dimension(screenWidth, screenHeight));//Sets the size of the window
		frame.add(GamePanel);//Adds the panel that the game is in
		frame.getContentPane().setPreferredSize(new Dimension(screenWidth, screenHeight));//sets the preferred size of the window
		frame.addKeyListener(GamePanel);// Adds a key listener to the game panel
		frame.pack();// Packs the frame
		GamePanel.startGame();// Starts the entire game
	}
	
}
