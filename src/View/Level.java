package View;

import java.awt.Graphics;
import java.util.ArrayList;

import Model.GameObject;
import Model.Movement;
import Model.Player;

public abstract class Level implements Movement{
	protected Screen screen;
	public final int MAP_SIZE = 25;
    public static int BLOC_SIZE = 40;
    protected static int cameraState = IDLE;
    protected Thread cameraThread;
    protected double viewPosX;
    protected double viewPosY;
    protected double viewMovX, viewMovY;
    public static GameObject KEY;
	
	
	public Level(){
		
	}
	public void render(Graphics g){
		
	}

	public void setObjects(ArrayList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}
	public static int getCameraState(){
		return cameraState;
	}
	public double getViewPosX() {
		return viewPosX;
	}

	public double getViewPosY() {
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
		BLOC_SIZE = bLOC_SIZE;
	}
	public void moveCamera(int x, int y){
		//viewMovX = x;
		//viewMovY = y;
		//if(cameraState == IDLE){
			//cameraThread = new Thread(new CameraMovement());
			//cameraThread.start();
		//}
		
		
	}
	public void centerCamera(Player p, int width, int height){
		this.viewPosX = p.getPosX()-width/(2*BLOC_SIZE);
		this.viewPosY = p.getPosY()-height/(2*BLOC_SIZE);
	}
	public void zoom(int zoomAmount){
		BLOC_SIZE += zoomAmount;
	}
	class CameraMovement implements Runnable{

		@Override
		public void run() {
			cameraState = MOVING;
			for(int i = 0; i<40;i++){
				synchronized(screen){
				viewPosX += viewMovX/40;
				viewPosY += viewMovY/40;
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cameraState = IDLE;
			viewMovX = viewMovY = 0;
			
			System.out.println(viewPosX+ ", " + viewPosY);
			
		}
		
	}
	
}
