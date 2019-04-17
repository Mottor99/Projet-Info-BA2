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
    public void render(int x, int y, Graphics g, int BLOC_SIZE){
    	g.drawImage(sprite.getImage(), x*BLOC_SIZE,y*BLOC_SIZE, BLOC_SIZE*width, BLOC_SIZE*height, null);
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
		int comparePos = compareObj.getPosX() + compareObj.getPosY();
		return this.posX + this.posY - comparePos;
    	
    }

    public abstract boolean isObstacle();
}
