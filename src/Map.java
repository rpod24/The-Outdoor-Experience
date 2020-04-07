import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

public class Map {
	HashMap<String, Integer> bottomMap = new HashMap<>();
	HashMap<String, Integer> topMap = new HashMap<>();
	Random r = new Random();
	String buildString = "";
	int x;
	int y;
	int RangeX = 30;
	int RangeY = 30;
	int totalSize;
	int mapNum = -1;
	// map type values for objects
	// grassLands
	int grassMin1 = 0;
	int grassMax1 = 70;
	int animalMin1 = 0;
	int animalMax1 = 2;
	int treeMin1 = 60;
	int treeMax1 = 70;
	int waterMin1 = 45;
	int waterMax1 = 60;
	int sandMin1 = 70;
	int sandMax1 = 100;
	// Dry Desert
	int grassMin2 = -1;
	int grassMax2 = -1;
	int treeMin2 = 4;
	int treeMax2 = 30;
	int waterMin2 = 0;
	int waterMax2 = 4;
	int sandMin2 = 0;
	int sandMax2 = 100;
	int animalMin2 = 30;
	int animalMax2 = 34;
	// Water Lands
	int grassMin3 = 0;
	int grassMax3 = 30;
	int treeMin3 = 0;
	int treeMax3 = 1;
	int animalMin3 = 1;
	int animalMax3 = 3;
	int waterMin3 = 30;
	int waterMax3 = 90;
	int sandMin3 = 30;
	int sandMax3 = 100;
	// Forest
	int grassMin4 = 0;
	int grassMax4 = 80;
	int animalMin4 = 0;
	int animalMax4 = 3;
	int treeMin4 = 20;
	int treeMax4 = 70;
	int waterMin4 = 80;
	int waterMax4 = 95;
	int sandMin4 = 80;
	int sandMax4 = 100;
	// values for objects
	int grass = 1;
	int sand = 2;
	int water = 3;
	int tree = 4;
	int animal = 5;
	int empty = 0;
	// Colors

	public static BufferedImage GrassGL;

	public static BufferedImage SandGL;

	public static BufferedImage WaterGL;

	public static BufferedImage TreeGL;

	public static BufferedImage GrassWL;

	public static BufferedImage SandWL;

	public static BufferedImage WaterWL;

	public static BufferedImage TreeWL;

	public static BufferedImage GrassDD;

	public static BufferedImage SandDD;

	public static BufferedImage WaterDD;

	public static BufferedImage TreeDD;

	public static BufferedImage GrassF;

	public static BufferedImage SandF;

	public static BufferedImage WaterF;

	public static BufferedImage TreeF;

	public static BufferedImage AnimalF;

	public static BufferedImage AnimalGL;

	public static BufferedImage AnimalDD;

	public static BufferedImage AnimalWL;
	
	public static BufferedImage Shelter;

	// Sand

	int colorTime = 0;
	ArrayList<Integer> animalLocations = new ArrayList<Integer>();
	ArrayList<Integer> treeLocations = new ArrayList<Integer>();
	ArrayList<Integer> waterLocations = new ArrayList<Integer>();
	ArrayList<Integer> farmmingLocations = new ArrayList<Integer>();
	ArrayList<Integer> shelterLocations = new ArrayList<Integer>();

	Map(int sizeOfXRange, int sizeOfYRange) {
		// RangeX = sizeOfXRange;
		// RangeY = sizeOfYRange;
		totalSize = RangeX * RangeY;
		try {

			GrassF = ImageIO.read(this.getClass().getResourceAsStream("Forest-Grass.png"));

			SandF = ImageIO.read(this.getClass().getResourceAsStream("Forest-Sand.png"));

			WaterF = ImageIO.read(this.getClass().getResourceAsStream("Forest-Water.jpg"));

			TreeF = ImageIO.read(this.getClass().getResourceAsStream("Forest-Tree.png"));

			AnimalF = ImageIO.read(this.getClass().getResourceAsStream("Forest-Animal.png"));

			GrassGL = ImageIO.read(this.getClass().getResourceAsStream("GrassLand-Grass.png"));

			SandGL = ImageIO.read(this.getClass().getResourceAsStream("GrassLand-Sand.png"));

			WaterGL = ImageIO.read(this.getClass().getResourceAsStream("GrassLand-Water.png"));

			TreeGL = ImageIO.read(this.getClass().getResourceAsStream("GrassLand-Tree.png"));

			AnimalGL = ImageIO.read(this.getClass().getResourceAsStream("GrassLand-Animal.png"));

			GrassDD = ImageIO.read(this.getClass().getResourceAsStream("GrassLand-Grass.png"));

			SandDD = ImageIO.read(this.getClass().getResourceAsStream("DryDesert-Sand.png"));

			WaterDD = ImageIO.read(this.getClass().getResourceAsStream("DryDesert-Water.png"));

			TreeDD = ImageIO.read(this.getClass().getResourceAsStream("DryDesert-Tree.png"));

			AnimalDD = ImageIO.read(this.getClass().getResourceAsStream("DryDesert-Animal.png"));

			GrassWL = ImageIO.read(this.getClass().getResourceAsStream("WaterLand-Grass.png"));

			SandWL = ImageIO.read(this.getClass().getResourceAsStream("WaterLand-Sand.png"));

			WaterWL = ImageIO.read(this.getClass().getResourceAsStream("WaterLand-Water.png"));

			TreeWL = ImageIO.read(this.getClass().getResourceAsStream("WaterLand-Tree.png"));

			AnimalWL = ImageIO.read(this.getClass().getResourceAsStream("WaterLand-Animal.png"));
			
			Shelter = ImageIO.read(this.getClass().getResourceAsStream("Shelter.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	void BuildMap(int mapType) {
		mapNum = mapType;
		if (animalLocations.size() > 0) {
			animalLocations.clear();
		}
		if (treeLocations.size() > 0) {
			treeLocations.clear();
		}
		if (waterLocations.size() > 0) {
			waterLocations.clear();
		}
		if (farmmingLocations.size() > 0) {
			farmmingLocations.clear();
		}
		if (shelterLocations.size() > 0) {
			shelterLocations.clear();
		}
		bottomMap.clear();
		int randomRange = 100;
		for (int x = 0; x < totalSize + 1; x++) {
			String key = "" + x;
			int putVal = -1;
			int putVal2 = -1;
			// map.put(key, r.nextInt(randomRange));
			int Type = r.nextInt(100);
			if (mapType == 1) {// Grasslands
				if (Type >= grassMin1 && Type < grassMax1) {
					putVal = grass;
				} else if (Type >= sandMin1 && Type < sandMax1) {
					putVal = sand;
				} else {
					putVal = empty;
				}
				if (Type >= waterMin1 && Type < waterMax1) {
					waterLocations.add(x);
					putVal2 = water;
				} else if (Type >= treeMin1 && Type < treeMax1) {
					treeLocations.add(x);
					putVal2 = tree;
				} else if (Type >= animalMin1 && Type < animalMax1) {
					animalLocations.add(x);
					putVal2 = animal;
				} else {
					putVal2 = empty;
				}
			}
			if (mapType == 2) {// Dry Desert
				if (Type >= waterMin2 && Type < waterMax2) {
					putVal2 = water;
					waterLocations.add(x);
				} else if (Type >= treeMin2 && Type < treeMax2) {
					treeLocations.add(x);
					putVal2 = tree;
				} else if (Type >= animalMin2 && Type < animalMax2) {
					animalLocations.add(x);
					putVal2 = animal;
				} else {
					putVal2 = empty;
				}
				if (Type >= sandMin2 && Type < sandMax2) {
					putVal = sand;
				}
			}
			if (mapType == 3) {// Water Lands
				if (Type >= grassMin3 && Type < grassMax3) {
					putVal = grass;
				} else if (Type >= sandMin3 && Type < sandMax3) {
					putVal = sand;
				} else {
					putVal = empty;
				}
				if (Type >= waterMin3 && Type < waterMax3) {
					putVal2 = water;
					waterLocations.add(x);
				} else if (Type >= treeMin3 && Type < treeMax3) {
					putVal2 = tree;
					treeLocations.add(x);
				} else if (Type >= animalMin3 && Type < animalMax3) {
					animalLocations.add(x);
					putVal2 = animal;
				} else {
					putVal2 = empty;
				}

			}
			if (mapType == 4) {// Forest
				if (Type >= grassMin4 && Type < grassMax4) {
					putVal = grass;
				} else if (Type >= sandMin4 && Type < sandMax4) {
					putVal = sand;
				} else {
					putVal = -1;
				}
				if (Type >= waterMin4 && Type < waterMax4) {
					putVal2 = water;
					waterLocations.add(x);
				} else if (Type >= treeMin4 && Type < treeMax4) {
					putVal2 = tree;
					treeLocations.add(x);
				} else if (Type >= animalMin4 && Type < animalMax4) {
					animalLocations.add(x);
					putVal2 = animal;
				} else {
					putVal2 = empty;
				}
			}
			bottomMap.put(key, putVal);
			// topMap.put(key, putVal2);
		}
		// System.out.println(topMap.size());
	}

	int ReadMap(int location, int layer) {
		String locationToRead = "" + location;

		if (layer == 1) {

			if (location < 900) {
				return topMap.get(locationToRead);
			} else {
				return -1;
			}
		} else if (layer == 2) {
			if (location < 900) {
				return bottomMap.get(locationToRead);
			} else {

				return 0;
			}
		} else {
			return -1;

		}
	}

	void drawMap(int mapType, int TopCornerX, int TopCornerY, int blockSize, Graphics g) {
		int readLocation = 0;
		for (int i = TopCornerY; i < TopCornerY + 20; i++) {
			for (int j = TopCornerX; j < TopCornerX + 20; j++) {
				readLocation = i * RangeX + j;
				boolean Object = false;
				boolean Water = false;
				int Type = ReadMap(readLocation, 2);
				if (Type == grass) {
					if (mapNum == 1) {
						g.drawImage(GrassGL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
					if (mapNum == 2) {
						g.drawImage(GrassDD, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
					if (mapNum == 3) {
						g.drawImage(GrassWL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
					if (mapNum == 4) {
						g.drawImage(GrassF, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}

				} else if (Type == sand) {
					if (mapNum == 1) {
						g.drawImage(SandGL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
					if (mapNum == 2) {
						g.drawImage(SandDD, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
					if (mapNum == 3) {
						g.drawImage(SandWL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
					if (mapNum == 4) {
						g.drawImage(SandF, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
								blockSize, null);
					}
				} else {
//					g.setColor(Color.BLACK);
//					g.fillRect((j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize, blockSize);
				}
				for (int k = 0; k < treeLocations.size(); k++) {
					if (treeLocations.get(k) == readLocation) {
						if (mapNum == 1) {
							g.drawImage(TreeGL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 2) {
							g.drawImage(TreeDD, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 3) {
							g.drawImage(TreeWL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 4) {
							g.drawImage(TreeF, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
					}

				}
				for (int k = 0; k < waterLocations.size(); k++) {
					if (waterLocations.get(k) == readLocation) {
						if (mapNum == 1) {
							g.drawImage(WaterGL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 2) {
							g.drawImage(WaterDD, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 3) {
							g.drawImage(WaterWL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 4) {
							g.drawImage(WaterF, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
					}
				}
				for (int k = 0; k < animalLocations.size(); k++) {
					if (animalLocations.get(k) == readLocation) {
						if (mapNum == 1) {
							g.drawImage(AnimalGL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 2) {
							g.drawImage(AnimalDD, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 3) {
							g.drawImage(AnimalWL, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
						if (mapNum == 4) {
							g.drawImage(AnimalF, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
					}
				}
				if (shelterLocations.size() > 0) {
					for (int k = 0; k < shelterLocations.size(); k++) {
						if (shelterLocations.get(k) == readLocation) {
							// System.out.println(readLocation);
							g.drawImage(Shelter, (j - TopCornerX) * blockSize, (i - TopCornerY) * blockSize, blockSize,
									blockSize, null);
						}
					}
				}
			}
		}
	}

	void EditMap(int xCord, int yCord, int EditType) {
		int cV = RangeX * yCord + xCord;
		boolean found;
		if (EditType == 0) {

			found = false;
			// System.out.println("Cord:");
			// System.out.println(cV);
			// System.out.println("Removed");
			for (int i = 0; i < treeLocations.size(); i++) {
				// System.out.println(treeLocations.get(i));
				if (treeLocations.get(i) == cV) {
					treeLocations.remove(i);
					found = true;
					break;
				}
			}
			if (found == false) {
				// System.out.println("Error!!");
			}

			for (int i = 0; i < treeLocations.size(); i++) {
				// System.out.println(treeLocations.get(i));
			}
			String mess = "" + cV;
			topMap.replace(mess, 0);
		}
		if (EditType == 1) {
			System.out.println("Adding CV: ");
			System.out.println(cV);
			shelterLocations.add(cV);
		}
		if (EditType == 2) {
			for (int i = 0; i < shelterLocations.size(); i++) {
				System.out.println("Checking");
				if (shelterLocations.get(i) == cV) {
					shelterLocations.remove(i);
					System.out.println("Removed");
					break;
				}
			}
		}
		if (EditType == 3) {
			for (int i = 0; i < waterLocations.size(); i++) {
				System.out.println("Checking");
				if (waterLocations.get(i) == cV) {
					waterLocations.remove(i);
					System.out.println("Removed");
					break;
				}
			}
		}
		if (EditType == 4) {
			for (int i = 0; i < animalLocations.size(); i++) {
				System.out.println("Checking");
				if (animalLocations.get(i) == cV) {
					animalLocations.remove(i);
					System.out.println("Removed");
					break;
				}
			}
		}
	}

	String CheckInteraction(int xCord, int yCord) {
		int cordValue = RangeX * yCord + xCord;
		System.out.println("Check CV: ");
		System.out.println(cordValue);
		for (int i = 0; i < treeLocations.size(); i++) {
			// System.out.println(treeLocations.get(i));
			if (treeLocations.get(i) == cordValue) {
				return "Tree";
			}
		}
		for (int i = 0; i < waterLocations.size(); i++) {
			if (waterLocations.get(i) == cordValue) {
				return "Water";
			}
		}
		for (int i = 0; i < shelterLocations.size(); i++) {
			// System.out.println(cordValue);
			if (shelterLocations.get(i) == cordValue) {
				return "Shelter";
			}
		}
		for (int i = 0; i < animalLocations.size(); i++) {
			if (animalLocations.get(i) == cordValue) {
				return "Animal";
			}
		}
		return "";
	}

	void ChangeTime(int time) {
		if (time == 1) {
			colorTime = 0;
		}
		if (time == 2) {
			colorTime = 1;
		}
		if (time == 3) {
			colorTime = 2;
		}
//		if(time == 4) {
//			colorMult = 2;
//		}
	}

}
