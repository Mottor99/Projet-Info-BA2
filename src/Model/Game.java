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




public class Game implements DeletableObserver, LevelSwitchObserver, Serializable, GUIObserver {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private Player active_player = null;
    private transient AStarThread t = null;
    private transient Loop gameLoop;
    private Level currentLevel;
    private transient Window window;
    private Time time;


    public Game(Window window){

    	this.window = window;
        Player p = new Player(6, 3, 3);
        p.attachGUIObserver(this);
        Adult w = new Adult(2, 2, "female");
        Baby b = new Baby(10, 10);
        Child c = new Child(15, 14, "male");
        w.attachGUIObserver(this);
        
        if(currentLevel == null){
        	currentLevel = new Map(this);
        }
        currentLevel.load();
        
        
        objects.add(p);
        objects.add(w);
        objects.add(b);
        objects.add(c);
        entities.add(p);
        entities.add(w);
        entities.add(b);
        entities.add(c);
        
        active_player = p;

        
        Camera.center(active_player, window.getWidth(), window.getHeight());

        
        time = new Time(this, 1, 0, 7, 100);

        start(window);
        //window.setNPC(w);
        
       
        
       
    }
    
    public void start(Window window) {
    	
    	this.window = window;
    	active_player.start();
    	attachObservers();
    	Camera.center(active_player, window.getWidth(), window.getHeight());
    	window.setPlayer(this.getActivePlayer());
        window.setGameObjects(this.getGameObjects());  //draws GameObjects
        window.setTime(this.time);
    	gameLoop = new Loop(this);
    }
    public void attachObservers(){
    	for(GameObject o : objects){
			if(o instanceof GUIModifier){
				((GUIModifier) o).attachGUIObserver(this);
			}
			if(o instanceof LevelSwitch){
				((LevelSwitch) o).attachLevelSwitch(this);
			}
			if(o instanceof Bed){
				((Bed) o).attachGame(this);
			}
		}
    }



	synchronized public void movePlayer(int x, int y)  {
        active_player.rotate(x, y);
        active_player.move(x, y, objects);
    }
	synchronized public void moveEntity(int x, int y, Entity e) {
		e.rotate(x, y);
		e.move(x, y, objects);
	}
    
    
    
    public void inventory(int x, int y) {
    	if (active_player.isAtPosition(x, y)) {
    		active_player.inventoryEvent();
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

    public synchronized void render() {
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
		System.out.println("[Game] objects size : "+ objects.size());
		this.objects = objects;
		
	}
	
	public synchronized void updateTime() {
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
		objects.remove(active_player);
		entities.remove(active_player);
		currentLevel.save(objects, entities);
		
		switch(destination){
			case "map" : currentLevel = new Map(this); break;
			case "home" : currentLevel = new Home(this); break;
			case "store" : currentLevel = new Store(this); break;
		}
		currentLevel.load();
		objects.add(active_player);
		entities.add(active_player);
		attachObservers();
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

	@Override
	public void notifyGUI(GUIModifier gm) {
		window.notifyGUI(gm);
		
	}

	public void setEntities(ArrayList<Entity> entities) {
		System.out.println("[Game] entities size : " + entities.size());
		this.entities = entities;
		
	}

}