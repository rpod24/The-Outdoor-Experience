import java.awt.Rectangle;

public class GameObject {
	
	int x;//X location of the object
    int y;//Y location of the object
    int width;//Width of the object
    int height; //Height of the object  
    boolean isAlive = true;// State of whether the object is alive or not
    
    Rectangle collisionBox;
    
    GameObject(int x, int y, int width, int height, int CollisionX, int CollisionY, int CollisionWidth, int CollisionHeight){
    	this.x = x;//Setting up all of the values
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	collisionBox = new Rectangle(CollisionX,CollisionY,CollisionWidth, CollisionHeight);//Creating a rectangle collision box
    }
    void update(int CollisionX, int ColisY, int ColisWidth, int ColisHeight) {//Updating the collision box to the location of the object
    	collisionBox.setBounds(CollisionX, ColisY, ColisWidth, ColisHeight);
    }
}
