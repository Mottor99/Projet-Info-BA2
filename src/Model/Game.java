package Model;

import View.Level;
import View.Window;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver {
    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player active_player = null;
    public final static Object lock = new Object();
    private AStarThread t = null;
    private Loop gameLoop;
    private Window window;
    private int size;
    // private int bombTimer = 3000;
    private int numberOfBreakableBlocks = 40;


    public Game(Window window) throws Exception {

    	gameLoop = new Loop(this);
        this.window = window;
        size = window.getMapSize();
        // Creating one Player at position (1,1)
        Player p = new Player(12, 12, 3);
        objects.add(p);
        players.add(p);
        window.setPlayer(p);
        active_player = p;

        // Map building

        this.drawMap();
        /*for (int i = 0; i < size; i++) {
=======
        for (int i = 0; i < size; i++) {
>>>>>>> refs/remotes/origin/master
            objects.add(new Wall(i, 0));
            objects.add(new Wall(0, i));
            objects.add(new Wall(i, size - 1));
            objects.add(new Wall(size - 1, i));
        }
        objects.add(new Couch(5, 3));
        objects.add(new Table(5, 5));
<<<<<<< HEAD
        Random rand = new Random();
=======
        /*Random rand = new Random();
>>>>>>> refs/remotes/origin/master
        for (int i = 0; i < numberOfBreakableBlocks; i++) {  //puts breakable blocks at random places and give them random lifepoints
            int x = rand.nextInt(size-4) + 2;
            int y = rand.nextInt(size-4) + 2;
            int lifepoints = rand.nextInt(5) + 1;
            BlockBreakable block = new BlockBreakable(x, y, 1 , 1, lifepoints);
            block.attachDeletable(this); //game(this) notifié que bloc a été cassé
            objects.add(block);
        }
        */

        window.setGameObjects(this.getGameObjects());  //draws GameObjects
       
    }

    public void drawMap() throws Exception {
    	FileReader file = new FileReader("src/file.txt");
    	BufferedReader reader = new BufferedReader(file);
    	
    	String line = reader.readLine();
    	int x = 0;
    	int y = 0;
    	while (line != null) {
    		for (int i=0; i<line.length(); i++) {
    			switch (line.charAt(i)) {
				case 'W' : objects.add(new Wall(x, y)); break;
				case 'C' : objects.add(new Couch(x, y)); break;
				case 'T' : objects.add(new Table(x, y)); break;
				}
    			x++;
    		}
    		
    		line = reader.readLine();
    		 
        	x = 0;
        	y++;
    		
    	}
    	reader.close();
    	
    }

    public synchronized void movePlayer(int x, int y) {
    	//System.out.println(objects.size());
    	if(active_player.getState() == Player.IDLE && Level.getCameraState() == Level.IDLE){
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
	            
	            	
		        
	            if(active_player.isFocused()){
	            	window.moveCamera(x,y);
	            }
	            active_player.move(x, y);
	        }
    	}
        
    }
        
    
    public void moveCamera(int x, int y){
    	window.moveCamera(x, y);
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

    public CopyOnWriteArrayList<GameObject> getGameObjects() {
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

}