package Model;

import View.Window;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;




public class Game implements DeletableObserver, LevelSwitchObserver, Serializable {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    public Player active_player = null;
    private AStarThread t = null;
    private Loop gameLoop;
    private Level currentLevel;
    private Window window;
    private int size;
    private Time time;

    public Game(Window window){

    	
        this.window = window;
        size = window.getMapSize();
        // Creating one Player at position (1,1)
        Player p = new Player(6, 3, 3);
        //File fichier =  new File("src/sauvegarde.ser") ;
        //ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
        //Player p = (Player)ois.readObject() ;
        //ois.close();
        
        Woman w = new Woman(2, 2);
        currentLevel = new Map(this);
        objects.add(p);
        objects.add(w);
        players.add(p);
        

        window.setPlayer(p);
        window.setNPC(w);

        active_player = p;
        Camera.center(active_player, window.getWidth(), window.getHeight());
        

        // Map building

        //this.drawMap();
        
        window.setPlayer(p);
        window.setGameObjects(this.getGameObjects());  //draws GameObjects

        time = new Time(this, 1, 0, 7, 100);

        gameLoop = new Loop(this);
        
        
       
        
       
    }


    synchronized public void movePlayer(int x, int y)  {
    	//System.out.println(objects.size());
    	
	        
        active_player.rotate(x, y);
        active_player.move(x, y, objects);
         
    	
    }
    
    public void sendPlayerToObject(String s) {
    	boolean moved = false;
    	switch (s) {
		case "Bed" : 
			for(GameObject object : objects) {
				if (object instanceof Bed && !moved){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					moved = true;
					((Bed) object).activate(active_player);
				}
			}; break;
			
		case "Fridge" : 
			for(GameObject object : objects) {
				if (object instanceof Fridge && !moved){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					moved = true;
					((Fridge) object).activate(active_player);
			}
		}; break;
		case "Toilet" : 
			for(GameObject object : objects) {
				if (object instanceof Toilet && !moved){
					this.sendPlayer(object.getPosX(), object.getPosY()-1);
					moved = true;
					((Toilet) object).activate(active_player);
				}
			}; break;
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
    	//File fichier =  new File("src/sauvegarde.ser") ;
        //ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier));
        //oos.writeObject(active_player) ;
        //oos.close();
    }
    public void lockCamera(){
    	active_player.setFocused(!active_player.isFocused());
    }

    public void tirePlayer() {
    	active_player.tire(this);
    	
    }
    
    public void growHunger() {
    	active_player.growHunger(this);
    }
    public void growBladder() {
    	active_player.growBladder(this);
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

}