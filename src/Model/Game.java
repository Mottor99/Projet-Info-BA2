package Model;

import View.InventoryBox;
import View.Window;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;




public class Game implements DeletableObserver, LevelSwitchObserver, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    public Player active_player = null;
    private transient AStarThread t = null;
    private transient Loop gameLoop;
    private Level currentLevel;
    private transient Window window;
    private int size;
    private Time time;


    public Game(Window window){

    	this.window = window;
        Player p = new Player(6, 3, 3);

        Adult w = new Adult(2, 2, "female");
        w.attachGUIObserver(window);
        currentLevel = new Map(this);
        objects.add(p);
        objects.add(w);
        entities.add(p);
        entities.add(w);
        active_player = p;

        
        Camera.center(active_player, window.getWidth(), window.getHeight());

        
        time = new Time(this, 1, 0, 7, 100);

        start(window);
        //window.setNPC(w);
        
       
        
       
    }
    
    public void start(Window window) {
    	this.window = window;
    	active_player.start();
    	System.out.println(this.objects.size());
    	
        size = window.getMapSize();
    	Camera.center(active_player, window.getWidth(), window.getHeight());
    	window.setPlayer(this.getActivePlayer());
        window.setGameObjects(this.getGameObjects());  //draws GameObjects
        window.setTime(this.time);
    	gameLoop = new Loop(this);
    }



	synchronized public void movePlayer(int x, int y)  {
        active_player.rotate(x, y);
        active_player.move(x, y, objects);
    }
	synchronized public void moveEntity(int x, int y, Entity e) {
		e.rotate(x, y);
		e.move(x,y, objects);
	}
    
    public void sendPlayerToObject(String s) {
    	CopyOnWriteArrayList<GameObject> copy = new CopyOnWriteArrayList<GameObject>();
    	copy.addAll(objects);
    	switch (s) {
		case "Bed" : 
			for(GameObject object : copy) {
				if (object instanceof Bed){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					((Bed) object).activate(active_player);
					break;
				}
			}; break;
			
		case "Fridge" : 
			for(GameObject object : copy) {
				if (object instanceof Fridge){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					((Fridge) object).activate(active_player);
					break;
			}
		}; break;
		case "Toilet" : 
			for(GameObject object : copy) {
				if (object instanceof Toilet){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					((Toilet) object).activate(active_player);
					break;
				}
			}; break;
		case "Shower" : 
			for(GameObject object : objects) {
				if (object instanceof Shower){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					((Shower) object).activate(active_player); break;
				}
			}; break;
		}
    }
    /*public void sendPlayerToObject(Class o) {
    	CopyOnWriteArrayList<GameObject> copy = new CopyOnWriteArrayList<GameObject>();
    	copy.addAll(objects);
    	for(GameObject object : copy) {
    		this.sendPlayer(object.getPosX(), object.getPosY());
    		(Class object).activate(active_player);
    	}
    }*/
    public void openShop(Shop shop) {
    	this.window.openShop(shop);
    	
    }
    public void inventory(int x, int y) {
    	if (active_player.isAtPosition(x, y)) {
    		window.showInventory();
    	}
    }
    
    public void moveCamera(int x, int y){
    	Camera.move(x, y);
    }
    public void centerCamera(){
    	window.centerCamera(active_player);
    	
    }
    public void zoomCamera(int zoom) throws IOException{
    	window.zoomCamera(zoom);
    }
    public void lockCamera(){
    	active_player.setFocused(!active_player.isFocused());
    }

    /*public void tirePlayer() {
    	active_player.growTire(this);
    	
    }
    
    public void growHunger() {
    	active_player.growHunger(this);
    }
    public void growBladder() {
    	active_player.growBladder(this);
    }
    public void growHygiene() {
    	active_player.growDirt(this);
    }*/
    public void action() {
        Activable aimedObject = null;
		for(GameObject object : objects){
			if(object.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){
			    if(object instanceof Activable){
			        aimedObject = (Activable) object;
			    }
			}
		}
		if(aimedObject != null){
		    aimedObject.activate(active_player);
            
		}
        
    }

    public void render() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    synchronized public void delete(Deletable ps, CopyOnWriteArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        
    }


    public void playerPos() {
        System.out.println(active_player.getPosX() + ":" + active_player.getPosY());
        
    }

	public void stop() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}


	public void sendPlayer(int x, int y) {
			if(t == null){
				t = new AStarThread(this, active_player, x,  y);
			}
			else{
				t.stop();
				t = new AStarThread(this, active_player, x,  y);
			}
		
	}
	public Window getWindow(){
		return window;
	}
	public Player getActivePlayer(){
		return active_player;
	}


	public void setGameObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
		
	}
	
	public void updateTime() {
		time.update();
		
	}


	@Override
	public synchronized void switchLevel(LevelSwitch s, String destination) {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(destination){
		case "map" : currentLevel = new Map(this); break;
		case "home" : currentLevel = new Home(this); break;
		}
		objects.add(active_player);
		window.setGameObjects(this.getGameObjects()); 
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void placeObject(GameObject o) {
		currentLevel.addObject(o);
	}

	
	public void moreMoney() {
		active_player.moreMoney(this);		
	}


	public void timeAccelerates() {
		time.accelerate();
		
	}


	public void timeDecelerates() {
		time.decelerates();
		
	}

	public void tic() {
		for (Entity entity : entities) {
			entity.tic(this);
		}
		
	}

}