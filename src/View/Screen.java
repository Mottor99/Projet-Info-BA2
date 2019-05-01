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
import Model.Dialog;
import Model.Entrance;
import Model.GameObject;
import Model.Level;
import Model.NPC;
import Model.Player;
import Model.Sprite;


public class Screen extends JPanel{
	private Mouse mouseController = null;
	private Level level;
	public static int BLOC_SIZE = 40;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public final int MAP_SIZE = 25;
    private Window window;
    private HUD hud;
    private DialogBox db;

	private JButton button; 
	
	public Screen(Window window, BorderLayout bl){
		super(bl);
		this.window = window;
		
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        this.addMouseListener(new MouseListener() {
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

    	hud = new HUD(this, window);
    	db = new DialogBox();
        /*
        button = new JButton("Test");
        //button.setForeground(Color.DARK_GRAY);
        button.setFocusable(false);
        button.setOpaque(true);
        button.setBackground(Color.GRAY);
        button.setVisible(true);
        button.setPreferredSize(new Dimension(200,200));
        this.add(button, BorderLayout.NORTH);
        */
        //window.setVisible(true);
    	this.add(db, BorderLayout.SOUTH);
       
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Dialog talkingObj = null;
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
            if(object instanceof Dialog){
            	if(((Dialog)object).isTalking()){
            		talkingObj = (Dialog)object;
            	}
            }
            
    	}
    	db.render(talkingObj);
    	if(db.hasFocus()){
    		db.requestFocusInWindow();
    	}else this.requestFocusInWindow();
    	
		this.hud.render(g);
		
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
