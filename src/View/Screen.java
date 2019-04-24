package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import Controller.Mouse;
import Model.Camera;
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
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/getBLOC_SIZE() + (int)Math.round(Camera.getViewPosX());
				int y = e.getY()/getBLOC_SIZE() + (int)Math.round(Camera.getViewPosY());
				mouseController.mapEvent(x, y);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
        
       
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		level.render(g);
		HUD.render(g);
	}
	public void redraw(){
		this.repaint();
	}
	public void setGameObjects(CopyOnWriteArrayList<GameObject> copyOnWriteArrayList) {
        this.level.setObjects(copyOnWriteArrayList);
        this.redraw();
    }
	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
	public double getViewPosX() {
		return this.level.getViewPosX();
	}

	public double getViewPosY() {
		return this.level.getViewPosY();
	}
	public int getCameraState(){
		return this.level.getCameraState();
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
	@Override
	public int getWidth(){
		return window.getWidth();
	}
	@Override
	public int getHeight(){
		return window.getHeight();
	}
}
