package View;

import java.awt.Graphics;
import java.util.ArrayList;

import Model.GameObject;
import Model.Player;

public abstract class Level {
	public final int MAP_SIZE = 25;
    protected int BLOC_SIZE = 60;
    protected int viewPosX;
    protected int viewPosY;
	
	
	public Level(){
		
	}
	public void render(Graphics g){
		
	}

	public void setObjects(ArrayList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}
	public int getViewPosX() {
		return viewPosX;
	}

	public int getViewPosY() {
		return viewPosY;
	}

	public void setViewPosX(int viewPosX) {
		this.viewPosX = viewPosX;
	}

	public void setViewPosY(int viewPosY) {
		this.viewPosY = viewPosY;
	}

	public int getBLOC_SIZE() {
		return BLOC_SIZE;
	}

	public void setBLOC_SIZE(int bLOC_SIZE) {
		this.BLOC_SIZE = bLOC_SIZE;
	}
	public void moveCamera(int x, int y){
		this.viewPosX += x;
		this.viewPosY += y;
		
	}
	public void centerCamera(Player p, int width, int height){
		this.viewPosX = p.getPosX()-(int)(width/(2*BLOC_SIZE));
		this.viewPosY = p.getPosY()-(int)(height/(2*BLOC_SIZE));
	}
	public void zoom(int zoomAmount){
		this.BLOC_SIZE += zoomAmount;
	}
}
