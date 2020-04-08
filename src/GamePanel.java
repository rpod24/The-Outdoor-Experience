import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Timer;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener {

	final int Menu_State = 0;
	final int Game_State = 1;
	final int End_State = 2;// Add more if needed
	final int TotalNumberOfStates = 3;// Set based on the amount of states
	int currentState = 0;// Sets the game to start on the first state, also is the variable to tell the
							// game what state it is in
	int frameRate = 60;// Sets the frame rate of the game
	int keyToChangeState = 10;// The key code value of the key required to change states, currently set to the
								// enter key
	static int blockSize = 50;
	int MainPlayerStartX = 0;// starting location of the main object, x value
	int MainPlayerStartY = 0;// starting location of the main object, y value
	int MainPlayerWidth = blockSize;// Width of the main object
	int MainPlayerHeight = blockSize;// Height of the main object

	ObjectManager objectManager;
	Timer timer;
	String txtFileLocation;
	int mapWidth = 30;
	int mapHeight = 30;
	int mapX = 0;
	int mapY = 0;
	Map map = new Map(mapWidth, mapHeight);
	boolean rightkeyPressed = false;
	boolean leftkeyPressed = false;
	boolean upkeyPressed = false;
	boolean downkeyPressed = false;
	int cyclex = 0;
	int cycley = 0;
	int cycleup = 0;
	int cycledown = 0;
	int CurrentMap = 4;
	double ammountToSubtract = -1;
	int cycle = 1;
	int strlen;
	int center;
	int time = 1;
	int seconds;
	int dayTime = 6;
	int prevTime = -1;
	int days = 0;
	int seletedBiome = 1;
	boolean win = false;
	int TreeCount = 0;
	
	String str;
	String Info;
	MainPlayer mainPlayer = new MainPlayer(MainPlayerStartX, MainPlayerStartY, MainPlayerWidth, MainPlayerHeight,
			mapWidth, mapHeight, map);// Create the main object, there can only be one of these
	Font subtitleFont = new Font("Arial", Font.PLAIN, 24);
	Font titleFont = new Font("Arial", Font.BOLD, 56);

	public void startGame() {
		// actions that start when the game starts
		timer.start();
		map.BuildMap(CurrentMap);
	}

	GamePanel() {
		// Initializes the Game
		objectManager = new ObjectManager(mainPlayer);// Initializes the object manger
		timer = new Timer(1000 / frameRate, this);// Sets the frame rate to 60 fps
	}

	void updateMenuState() {

	}

	void updateGameState() {
		// updates everything that will happen in the second state
		objectManager.update();
		if (cycle % 180 == 0) {
			mainPlayer.ChangeWater((int) ammountToSubtract);
		}
		if (cycle % 360 == 0) {
			mainPlayer.ChangeHunger((int) ammountToSubtract);
		}
		if (cycle % 60 == 0) {
			seconds++;
			System.out.println(seconds);
		}
		if (seconds % 10 == 0 && prevTime != seconds) {
			time++;
			System.out.println("Time: ");
			System.out.println(time);
			prevTime = seconds;
		}
		if (time >= 7) {
			time = 1;
			days++;
		}
		if (time == 1 || time == 2) {
			map.ChangeTime(1);
		} else if (time == 3 || time == 6) {
			map.ChangeTime(2);
		} else if (time == 4 || time == 5) {
			map.ChangeTime(3);
		}
		cycle++;
		if (mainPlayer.isAlive == false) {
			win = false;
			currentState = End_State;
		}
		if (days > 21) {
			win = true;
			currentState = End_State;
		}
		TreeCount = mainPlayer.Inventory.size();
	}

	void updateEndState() {
		// updates everything that will happen in the third state
	}

	void drawMenuState(Graphics g) {
		// draws everything that needs to be drawn in the first state
		g.setColor(Color.white);
		g.fillRect(0, 0, TheOutdoorExperience.width + 200, TheOutdoorExperience.height);
		g.setColor(Color.black);
		g.setFont(titleFont);
		str = "The Outdoor Experience";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 600 - strlen, 150);
		g.setFont(subtitleFont);
		str = "Survive for 21 days to win!";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 600 - strlen, 200);
		str = "Select a biome: ";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		// grasslands
		g.drawString(str, 600 - strlen, 250);
		g.setColor(Color.green);
		g.fillRect(600 - 100, 275, 200, 35);
		g.setColor(Color.black);
		str = "Grass Lands";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 600 - strlen, 300);
		// dry desert
		g.setColor(Color.yellow);
		g.fillRect(600 - 100, 325, 200, 35);
		g.setColor(Color.black);
		str = "Dry Desert";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 600 - strlen, 350);
		// water lands
		g.setColor(Color.blue);
		g.fillRect(600 - 100, 375, 200, 35);
		g.setColor(Color.black);
		str = "Water Lands";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 600 - strlen, 400);
		// forest
		g.setColor(Color.gray);
		g.fillRect(600 - 100, 425, 200, 35);
		g.setColor(Color.black);
		str = "Forest";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 600 - strlen, 450);
		g.drawRect(500, 225 + 50 * seletedBiome, 200, 35);
	}

	void drawGameState(Graphics g) {
		// draws everything that needs to be drawn in the second state
		g.setColor(Color.white);
		g.fillRect(0, 0, TheOutdoorExperience.width + 200, TheOutdoorExperience.height);
//		map.drawMap(CurrentMap, mapX, mapY, blockSize, g);
		objectManager.draw(g);
		g.setColor(Color.black);
		g.setFont(subtitleFont);
		str = "" + mainPlayer.WaterLevel();
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 1100 - strlen, 150);
		str = "Water Level: ";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 1100 - strlen, 125);
		str = "Days: " + days;
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 1100 - strlen, 25);
		str = "Hunger: ";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 1100 - strlen, 200);
		str = "" + mainPlayer.HungerLevel();
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 1100 - strlen, 250);
		str = "Inventory:";
		strlen = g.getFontMetrics().stringWidth(str) / 2;
		g.drawString(str, 1100 - strlen, 350);
		str = "Trees: "+TreeCount;
		g.drawString(str, 1050, 400);
//		center = TheOutdoorExperience.width/2;
//		g.drawString(str, center - strlen, 25);
	}

	void drawEndState(Graphics g) {
		// draws everything that needs to be drawn in the third state
		g.setFont(subtitleFont);
		if (win) {
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, TheOutdoorExperience.width + 200, TheOutdoorExperience.height);
			str = "You Win! Nice job!";
			strlen = g.getFontMetrics().stringWidth(str) / 2;
			g.drawString(str, 600 - strlen, 400);
		} else {
			g.setColor(Color.red);
			g.fillRect(0, 0, TheOutdoorExperience.width + 200, TheOutdoorExperience.height);
			g.setColor(Color.black);
			if (days == 1) {
				str = "You died after: " + days + " day";
			} else {
				str = "You died after: " + days + " days";
			}
			strlen = g.getFontMetrics().stringWidth(str) / 2;
			g.drawString(str, 600 - strlen, 300);
			str = "Better luck next time!";
			strlen = g.getFontMetrics().stringWidth(str) / 2;
			g.drawString(str, 600 - strlen, 400);
			str = "Press enter to go back to menu screen";
			strlen = g.getFontMetrics().stringWidth(str) / 2;
			g.drawString(str, 600 - strlen, 500);
		}
	}

	void resetGame() {
		mainPlayer.isAlive = true;
		mainPlayer.hungerLevel = 100;
		mainPlayer.waterLevel = 100;
		mainPlayer.Inventory.clear();
		mainPlayer.x = 0;
		mainPlayer.trueX = 0;
		mainPlayer.y = 0;
		mainPlayer.trueY = 0;
		days = 0;
		cycle = 1;
		seconds = 0;
		win = false;
	}

	public void paintComponent(Graphics g) {

		if (currentState == Menu_State) {// checks if it is the first state and if so draws the first state
			drawMenuState(g);

		} else if (currentState == Game_State) {// checks if it is the second state and if so draws the second state

			drawGameState(g);

		} else if (currentState == End_State) {// checks if it is the third state and if so draws the third state

			drawEndState(g);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (currentState == Menu_State) {// checks if it is the first state and if so updates the first state
			updateMenuState();
		} else if (currentState == Game_State) {// checks if it is the second state and if so updates the second state
			updateGameState();
		} else if (currentState == End_State) {// checks if it is the third state and if so updates the third state
			updateEndState();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (currentState == Menu_State) {
			mainPlayer.updateMap(CurrentMap);
			CurrentMap = seletedBiome;
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (seletedBiome < 4) {
					seletedBiome++;
				}
				else {
					seletedBiome = 1;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (seletedBiome > 1) {
					seletedBiome--;
				}
				else {
					seletedBiome = 4;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			mainPlayer.rightPressed = true;
			rightkeyPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			mainPlayer.leftPressed = true;
			leftkeyPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			mainPlayer.upPressed = true;
			upkeyPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			mainPlayer.downPressed = true;
			downkeyPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_F || e.getKeyCode() == KeyEvent.VK_SPACE) {
			Info = map.CheckInteraction(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize);
			if (Info == "Water") {
				mainPlayer.ChangeWater(33);
			}
			if (Info == "Tree") {
				mainPlayer.addItems("Tree");
				map.EditMap(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize, 0);
			}
			if (Info == "Shelter") {
				if (time >= 3) {
					time = 1;
					days++;
				}
			}
			if (Info == "Animal") {
				mainPlayer.ChangeHunger(40);
				map.EditMap(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize, 4);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_E) {
			if (mainPlayer.checkItems("Tree")
					&& map.CheckInteraction(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize) == "") {
				map.EditMap(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize, 1);
				mainPlayer.removeItems("Tree");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			String info = map.CheckInteraction(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize);
			if (info == "Shelter") {
				map.EditMap(mainPlayer.trueX / blockSize, mainPlayer.trueY / blockSize, 2);
				mainPlayer.addItems("Tree");
			} else if (info == "Water") {

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == keyToChangeState) {// checks if the key released is the key to change the state
			if (currentState < TotalNumberOfStates - 1) {// checks if the state is not the third and if so increases the
															// state to the next one
				currentState++;
				if (currentState == Game_State) {
					map.BuildMap(CurrentMap);
					resetGame();
				}
			} else {// changes from the last state to the first
				currentState = Menu_State;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			mainPlayer.rightPressed = false;
			rightkeyPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			mainPlayer.leftPressed = false;
			leftkeyPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			mainPlayer.upPressed = false;
			upkeyPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			mainPlayer.downPressed = false;
			downkeyPressed = false;
		}
	}

}
