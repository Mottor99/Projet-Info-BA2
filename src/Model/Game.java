package Model;

import View.Window;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;




public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player active_player = null;
    private AStarThread t = null;
    private Loop gameLoop;
    private Level currentLevel;
    private Window window;
    private int size;


    public Game(Window window) throws Exception {

    	gameLoop = new Loop(this);
        this.window = window;
        size = window.getMapSize();
        // Creating one Player at position (1,1)
        Player p = new Player(12, 12, 3);
        currentLevel = new Map(this);
        objects.add(p);
        players.add(p);
        
        active_player = p;
        Camera.center(active_player, window.getWidth(), window.getHeight());
        

        // Map building

        //this.drawMap();
        
        window.setPlayer(p);
        window.setGameObjects(this.getGameObjects());  //draws GameObjects
       
    }


    public synchronized void movePlayer(int x, int y) {
    	//System.out.println(objects.size());
    	if(active_player.getState() == Player.IDLE){
	        int nextX = active_player.getPosX() + x;
	        int nextY = active_player.getPosY() + y;
	
	        boolean obstacle = false;
	        for (GameObject object : objects) {
	            if (object.isAtPosition(nextX, nextY)) {
	                obstacle = object.isObstacle();
	            }
	            if (obstacle == true) {
	                break;
	            }
	        }
	        active_player.rotate(x, y);
	        if (obstacle == false) {
	        	
	            active_player.move(x, y);
	            
	        }
    	}
        
    }
        
    
    public void moveCamera(int x, int y){
    	Camera.move(x, y);
    }
    public void centerCamera(){
    	window.centerCamera(active_player);
    	
    }
    public void zoomCamera(int zoom){
    	window.zoomCamera(zoom);
    }
    public void lockCamera(){
    	active_player.setFocused(!active_player.isFocused());
    }

    public void tirePlayer() {
    	active_player.tire();
    	
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
		    aimedObject.activate();
            
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

}