import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class MainPlayer extends GameObject {
	boolean rightPressed = false;
	boolean leftPressed = false;
	boolean upPressed = false;
	boolean downPressed = false;
	int movementSize = 5;// values that can divide 50 into two whole numbers
	int lastXDirection = 0;
	int lastYDirection = 0;
	int RightVal = 1;
	int LeftVal = 2;
	int UpVal = 3;
	int DownVal = 4;
	int DrawX;
	int DrawY;
	boolean screenMoving = false;
	int screenMovingDirection = 0;
	int MaxMapX;
	int MaxMapY;
	int MaxX;
	int MaxY;
	double mapX;
	double mapY;
	int wholeMapX = 0;
	int wholeMapY = 0;
	Map map;
	int trueX;
	int trueY;
	int waterLevel = 100;
	int hungerLevel = 100;
	boolean isAlive = true;
	int currMap = 1;
	ArrayList<String> Inventory = new ArrayList<String>();

	MainPlayer(int x, int y, int width, int height, int MaxPlayerX, int MaxPlayerY, Map object) {
		super(x, y, width, height, x, y, width, height);
		MaxX = MaxPlayerX;
		MaxY = MaxPlayerY;
		map = object;
		trueX = x;
		trueY = y;
	}
	void addItems(String Item) {
		items().add(Item);
	}
	void removeItems(String Item) {
		for (int i = 0; i < items().size(); i++) {
			if(items().get(i) == Item) {
				items().remove(i);
			}
		}
	}
	ArrayList<String> items() {
		return Inventory;
	}
	boolean checkItems(String itemLookingFor) {
		for (int i = 0; i < items().size(); i++) {
			if(items().get(i) == itemLookingFor) {
				return true;
			}
		}
		return false;
	}
	void ChangeWater(int increaseLevel) {
		waterLevel+=increaseLevel;
		if(waterLevel>=100) {
			waterLevel=100;
		}
		if(waterLevel <= 0) {
			isAlive = false;
		}
	}
	
	void ChangeHunger(int increaseLevel) {
		hungerLevel+=increaseLevel;
		if(hungerLevel <= 0||hungerLevel >= 200) {
			isAlive = false;
		}
	}
	
	int WaterLevel() {
		return waterLevel;
	}
	
	int HungerLevel() {
		return hungerLevel;
	}
	void updateMap(int map) {
		currMap = map;
	}
	void update() {
		if (rightPressed) {
			if (x + GamePanel.blockSize < TheOutdoorExperience.width && trueX / 50 >= 10 && trueX / 50 <= MaxX - 11) {
				trueX += movementSize;
				lastXDirection = RightVal;

			} else {
				if (x + GamePanel.blockSize < TheOutdoorExperience.width) {
					x += movementSize;
					trueX += movementSize;
					lastXDirection = RightVal;
				}
			}
		}

		if (leftPressed) {
			if (x - movementSize >= 0 && trueX / 50 >= 10 && trueX / 50 <= MaxX - 11) {
				trueX -= movementSize;
				lastXDirection = LeftVal;

			} else {
				if (trueX - movementSize >= 0) {
					x -= movementSize;
					trueX -= movementSize;
					lastXDirection = LeftVal;

				}
			}
		}

		if (downPressed) {
			if (y + GamePanel.blockSize < TheOutdoorExperience.height && trueY / 50 >= 10 && trueY / 50 <= MaxY - 11) {
				trueY += movementSize;
				lastYDirection = DownVal;

			} else {
				if (y + GamePanel.blockSize < TheOutdoorExperience.height) {
					y += movementSize;
					trueY += movementSize;
					lastYDirection = DownVal;

				}
			}
		}

		if (upPressed) {
			if (y - movementSize >= 0 && trueY / 50 >= 10 && trueY / 50 <= MaxY - 11) {
				trueY -= movementSize;
				lastYDirection = UpVal;

			} else {
				if (trueY - movementSize >= 0) {
					y -= movementSize;
					trueY -= movementSize;
					lastYDirection = UpVal;

				}
			}
		}

		if (x % GamePanel.blockSize != 0 && lastXDirection == RightVal && rightPressed == false) {
			x += movementSize;

		}
		if (x % GamePanel.blockSize != 0 && lastXDirection == LeftVal && leftPressed == false) {
			x -= movementSize;

		}
		if (y % GamePanel.blockSize != 0 && lastYDirection == UpVal && upPressed == false) {
			y -= movementSize;
		}
		if (y % GamePanel.blockSize != 0 && lastYDirection == DownVal && downPressed == false) {
			y += movementSize;
		}
		
		
		
		
		if (trueX % GamePanel.blockSize != 0 && lastXDirection == RightVal && rightPressed == false) {
			trueX += movementSize;

		}
		if (trueX % GamePanel.blockSize != 0 && lastXDirection == LeftVal && leftPressed == false) {
			trueX -= movementSize;

		}
		if (trueY % GamePanel.blockSize != 0 && lastYDirection == UpVal && upPressed == false) {
			trueY -= movementSize;
		}
		if (trueY % GamePanel.blockSize != 0 && lastYDirection == DownVal && downPressed == false) {
			trueY += movementSize;
		}
		if(trueX / 50 >= 10 && trueX / 50 <= MaxX - 11) {
			mapX = trueX/50-10;	
		}
		if(trueY / 50 >= 10 && trueY / 50 <= MaxY - 11) {
			mapY = trueY/50-10;	
		}

	}

	void draw(Graphics g) {
			wholeMapX = (int) mapX;
			wholeMapY = (int) mapY;
		map.drawMap(currMap, wholeMapX, wholeMapY, GamePanel.blockSize, g);
//		g.setColor(Color.red);
//		DrawX = x;
//		DrawY = y;
//		g.fillRect(x, y, width, height);
		g.setColor(Color.RED);
		if(trueX<0) {
			trueX=0;
		}
		if(trueY<0) {
			trueY=0;
		}
		DrawX=trueX-(wholeMapX*50);
		if(DrawX>950) {
			DrawX=950;
		}
		DrawY=trueY-(wholeMapY*50);
		if(DrawY>950) {
			DrawY=950;
		}
		g.fillRect(DrawX, DrawY, width, height);
	}
	
	

}
