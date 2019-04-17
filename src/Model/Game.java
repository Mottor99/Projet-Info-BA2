package Model;

import View.Window;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player active_player = null;
    private AStarThread t = null;

    private Window window;
    private int size;
    // private int bombTimer = 3000;
    private int numberOfBreakableBlocks = 40;

    public Game(Window window) {
    	Loop gameLoop = new Loop(this);
        this.window = window;
        size = window.getMapSize();
        // Creating one Player at position (1,1)
        Player p = new Player(12, 12, 3);
        objects.add(p);
        players.add(p);
        window.setPlayer(p);
        active_player = p;

        // Map building
        for (int i = 0; i < size; i++) {
            objects.add(new BlockUnbreakable(i, 0, 1, 1));
            objects.add(new BlockUnbreakable(0, i, 1, 1));
            objects.add(new BlockUnbreakable(i, size - 1, 1, 1));
            objects.add(new BlockUnbreakable(size - 1, i, 1, 1));
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfBreakableBlocks; i++) {  //puts breakable blocks at random places and give them random lifepoints
            int x = rand.nextInt(size-4) + 2;
            int y = rand.nextInt(size-4) + 2;
            int lifepoints = rand.nextInt(5) + 1;
            BlockBreakable block = new BlockBreakable(x, y, 1 , 1, lifepoints);
            block.attachDeletable(this); //game(this) notifi� que bloc a �t� cass�
            objects.add(block);
        }

        window.setGameObjects(this.getGameObjects());  //draws GameObjects
       
    }


    public void movePlayer(int x, int y) {
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
            if(active_player.isFocused()){
            	window.moveCamera(x,y);
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

    public void update() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
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