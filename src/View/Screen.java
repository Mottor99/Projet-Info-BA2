package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.Mouse;
import Model.GameObject;
import Model.Player;


public class Screen extends JPanel{
	private Mouse mouseController = null;
	private Level level;
	public final int MAP_SIZE = 25;
    private Window window;
	
	public Screen(Window window){
		this.window = window;
		level = new Map(this);
		
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/getBLOC_SIZE() + level.getViewPosX();
				int y = e.getY()/getBLOC_SIZE() + level.getViewPosY();
				mouseController.mapEvent(x, y);
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
        
       
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		level.render(g);
		//HUD.render(g);
	}
	public void redraw(){
		this.repaint();
	}
	public void setGameObjects(ArrayList<GameObject> objects) {
        this.level.setObjects(objects);
        this.redraw();
    }
	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
	public int getViewPosX() {
		return this.level.getViewPosX();
	}

	public int getViewPosY() {
		return this.level.getViewPosY();
	}


	public int getBLOC_SIZE() {
		return this.level.getBLOC_SIZE();
	}

	public void setBLOC_SIZE(int bLOC_SIZE) {
		this.level.setBLOC_SIZE(bLOC_SIZE);
	}
	public void moveCamera(int x, int y){
		this.level.moveCamera(x, y);
		
	}
	public void centerCamera(Player p, int width, int height){
		this.level.centerCamera(p, width, height);
	}
	public void zoom(int zoomAmount){
		this.level.zoom(zoomAmount);
	}
	public int getWidth(){
		return window.getWidth();
	}
	public int getHeight(){
		return window.getHeight();
	}
}
