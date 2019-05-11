package Model;

import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.Icon;

public abstract class GameObject implements Comparable<GameObject>, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int posX;
    protected int posY;
    protected int width = 1;
    protected int height = 1;
    protected transient Sprite sprite = Sprite.brick;

    public GameObject(int X, int Y, int width, int height) {
        this.posX = X;
        this.posY = Y;
        this.width = width;
        this.height = height;
        
    }
   
    private void writeObject(ObjectOutputStream out) throws IOException {
    	      out.defaultWriteObject();
    	      out.writeObject(sprite);
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    	      in.defaultReadObject();
    	      this.sprite = (Sprite) in.readObject();
    	      this.sprite.load();
    }
    public double clamp(double x, double dx, double max, double min) {
    	x+= dx;
    	if(x>max)x = max;
    	if(x<min)x = min;
    	return x;
    }
    public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(sprite.getImage(), (int)(x*BLOC_SIZE),(int)(y*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*height, null);
    }

    public int getWidth() { 
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getPosX() {
        return this.posX;
    }
	
    public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
        return this.posY;
    }

	public void setPosY(int posY) {
		this.posY = posY;
	}
    public boolean isAtPosition(int x, int y) {
        return (this.posX <= x && x <= (this.posX + width - 1)) && (this.posY <= y && y <= (this.posY + height - 1));
    }
    @Override
    public int compareTo(GameObject compareObj){
    	if(compareObj instanceof Entrance) {
    		return 1;
    	}else {
		int comparePos = compareObj.getPosY();
		return this.posY - comparePos;
    	}
    	
    }

    public abstract boolean isObstacle();
	//public String getSprite() {
		//return this.sprite;
	//}
	public Sprite getSprite() {
		
		return this.sprite;
	}
	
}
