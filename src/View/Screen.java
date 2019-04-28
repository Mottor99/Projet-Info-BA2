package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.Mouse;
import Model.Camera;
import Model.Entrance;
import Model.GameObject;
import Model.Level;
import Model.Player;
import Model.Sprite;


public class Screen extends JPanel{
	private Mouse mouseController = null;
	private Level level;
	public static int BLOC_SIZE = 40;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public final int MAP_SIZE = 25;
    private Window window;

	private JButton button; 
	
	public Screen(Window window, BorderLayout bl){
		super(bl);
		this.window = window;
		
		

    	HUD hud = new HUD(this, window);
		this.setLayout(new BorderLayout());
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
        button = new JButton("Test");
        //button.setForeground(Color.DARK_GRAY);
        button.setOpaque(true);
        button.setBackground(Color.GRAY);
        //button.setFocusable(false);
        //button.setVisible(true);
        button.setPreferredSize(new Dimension(200,200));
        this.add(button, BorderLayout.NORTH);
       
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paintComponent(g);
		double viewPosX = Camera.getViewPosX();
    	double viewPosY = Camera.getViewPosY();
    	for(int i = -20; i<45; i++){
    		for(int j = -20; j<45;j++){
    			
    			double x = i-viewPosX;
    			double y = j-viewPosY;
    			g.drawImage(Sprite.grass.getImage(), (int)Math.round((x*BLOC_SIZE)), (int)Math.round((y*BLOC_SIZE)), BLOC_SIZE, BLOC_SIZE, null);
    		}
    	}
    	Collections.sort(objects);
    	for (GameObject object : this.objects) {
            double x = object.getPosX()-viewPosX;
            double y = object.getPosY()-viewPosY;
            object.render(x, y, g, BLOC_SIZE);
            
    	}
    	
		HUD.render(g);
	}
	public void redraw(){
		this.repaint();
	}
	public void setGameObjects(ArrayList<GameObject> arrayList) {
        this.objects = arrayList;
        this.redraw();
    }
	public void addMouse(Mouse m) {
		this.mouseController = m;
	}

	public int getBLOC_SIZE() {
		return this.BLOC_SIZE;
	}

	public void setBLOC_SIZE(int bLOC_SIZE) {
		this.BLOC_SIZE = bLOC_SIZE;
	}
	
	public void zoom(int zoomAmount){
		this.BLOC_SIZE += zoomAmount;
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
