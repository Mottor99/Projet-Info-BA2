package Model;

import java.awt.Graphics;

public abstract class GameObject implements Comparable<GameObject>{
    protected int posX;
    protected int posY;
    protected int width = 1;
    protected int height = 1;
    protected int color;
    protected Sprite sprite = Sprite.brick;

    public GameObject(int X, int Y, int width, int height, int color) {
        this.posX = X;
        this.posY = Y;
        this.width = width;
        this.height = height;
        
        this.color = color;
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

    public int getPosY() {
        return this.posY;
    }

    public int getColor() {
        return this.color;
    }

    public boolean isAtPosition(int x, int y) {
        return (this.posX <= x && x <= (this.posX + width - 1))  && (this.posY <= y && y <= (this.posY + height - 1));
    }
    @Override
    public int compareTo(GameObject compareObj){
		int comparePos = compareObj.getPosY();
		return this.posY - comparePos;
    	
    }

    public abstract boolean isObstacle();
}
