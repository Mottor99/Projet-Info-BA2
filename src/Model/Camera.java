package Model;

import View.Level;

public class Camera{
	private static double viewPosX, viewPosY;
	public Camera(){
		
	}
	public static synchronized void move(double x, double y){
		
		viewPosX += x;
		viewPosY += y;
		
	}
	public static void center(Player p, int width, int height){
		viewPosX = p.getPosX()-width/(2*Level.BLOC_SIZE);
		viewPosY = p.getPosY()-height/(2*Level.BLOC_SIZE);
	}
	public static double getViewPosX() {
		return viewPosX;
	}
	
	public static void setViewPosX(double viewPosX) {
		Camera.viewPosX = viewPosX;
	}
	public static double getViewPosY() {
		return viewPosY;
	}
	public static void setViewPosY(double viewPosY) {
		Camera.viewPosY = viewPosY;
	}
}
