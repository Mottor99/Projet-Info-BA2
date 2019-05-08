package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.Mouse;
import Model.Camera;
import Model.Dialog;
import Model.Draggable;
import Model.DraggableObserver;
import Model.Entrance;
import Model.GameObject;
import Model.Level;
import Model.MenuActivable;
import Model.NPC;
import Model.Player;
import Model.Shop;
import Model.ShopCounter;
import Model.Sprite;
import Model.Time;


public class Screen extends JPanel implements DraggableObserver {
	private Mouse mouseController = null;
	public static int BLOC_SIZE = 40;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public final int MAP_SIZE = 25;
    private Window window;
    private HUD hud;
    private DialogBox db;

    private MenuPanel menu;
    private JPanel bottom = new JPanel(new BorderLayout());

    private InventoryBox ibox;
    private InventoryItem draggedItem = null;
    
    private ShopPanel shop;

	
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
				//mouseController.mapEvent(x, y);
				mouseController.inventory(x, y);
				if (draggedItem != null){
				mouseController.placeObject(draggedItem.getObject());
					draggedItem.notifyDeletableObserver();
					draggedItem = null;
				}
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
        this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if(draggedItem!=null){
					draggedItem.getObject().setPosX(e.getX()/getBLOC_SIZE() + (int)Math.round(Camera.getViewPosX()));
					draggedItem.getObject().setPosY(e.getY()/getBLOC_SIZE() + (int)Math.round(Camera.getViewPosY()));
				}
			}

			
        });
    	hud = new HUD(this, window);
    	db = new DialogBox();
    	menu = new MenuPanel();
    	shop = new ShopPanel();
    	ibox = new InventoryBox();

    	bottom.add(db, BorderLayout.LINE_START);
    	bottom.add(menu, BorderLayout.LINE_END);
    	bottom.setOpaque(false);
    	this.add(bottom, BorderLayout.SOUTH);
    	this.add(ibox, BorderLayout.EAST);
        this.add(shop, BorderLayout.WEST);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Dialog talkingObj = null;
		MenuActivable menuObj = null;
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
            if(object instanceof MenuActivable){
            	if(((MenuActivable)object).isInMenu()){
            		menuObj = (MenuActivable)object;
            	}
            }
    	} 

        if(draggedItem != null) {
        	draggedItem.getObject().render(draggedItem.getObject().getPosX() - viewPosX, draggedItem.getObject().getPosY() - viewPosY, g, BLOC_SIZE);
        }
            
    	//ibox.render(inventoryOpen);
    	db.render(talkingObj);
    	menu.render(menuObj);
    	if(menu.hasFocus()){
    		menu.requestFocusInWindow();
    	}
    	else if(db.hasFocus()){
    		db.requestFocusInWindow();
    	}else this.requestFocusInWindow();
    	hud.render(g);
		
		
		
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
	public void openShop(Shop shop) {
		this.shop.switchVisibility(shop);
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

	public int getX(MouseEvent e) {
		return e.getX()/getBLOC_SIZE() + (int)Math.round(Camera.getViewPosX());
	}
	public int getY(MouseEvent e) {
		return e.getY()/getBLOC_SIZE() + (int)Math.round(Camera.getViewPosY());
	}
	public void setDraggedObject(InventoryItem ii) {
		draggedItem = ii;
	}
	public void showInventory() {
		ibox.switchVisibility();
	}

	public void setPlayer(Player p) {
		ibox.setPlayer(p, this);
		hud.setPlayer(p);
		
	}

	@Override
	public void setDraggedItem(Draggable d) {
		this.draggedItem = (InventoryItem) d;
		
	}
	public void setTime(Time time) {
		hud.setTime(time);
	}
}
