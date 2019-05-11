package Model;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import View.Screen;

public abstract class Level implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Screen screen;
	public final int MAP_SIZE = 25;
    public transient static int BLOC_SIZE = 40;
    protected ArrayList<GameObject> objects = new ArrayList<GameObject>();
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    protected int spawnX, spawnY;
    protected String fileName = "src/file";
    protected Game game; 
    private boolean fileCharged = false;
	
	
	public Level(Game game, String fileName){
		this.game = game;
		this.fileName = fileName;
	}
	
	public void load() throws Exception {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName + ".ser"));
			objects.addAll((CopyOnWriteArrayList<GameObject>) ois.readObject());
			entities.addAll((CopyOnWriteArrayList<Entity>) ois.readObject());
			ois.close();
			fileCharged = true;
			System.out.println("loaded from serial");
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found !");;
		} catch (FileNotFoundException e1) {
			System.out.println("File not found !");;
		} catch (IOException e) {
			System.out.println("IOException !!!");;
		}
		if (!fileCharged) {
	    	FileReader file = new FileReader(this.fileName + ".txt");
	    	BufferedReader reader = new BufferedReader(file);
	    	System.out.println("loaded from text");
	    	String line = reader.readLine();
	    	int x = 0;
	    	int y = 0;
	    	while (line != null) {
	    		for (int i=0; i<line.length(); i++) {
	    			switch (line.charAt(i)) {
					case 'W' : objects.add(new Wall(x, y)); break;
					case 'C' : objects.add(new Couch(x, y)); break;
					case 'B' : objects.add(new Bed(x, y, game)); break;
					case 'T' : objects.add(new Table(x, y)); break;
					case 'P' : objects.add(new Toilet(x, y)); break;
					case 'S' : objects.add(new Shower(x,y)); break;
					case 'F' : objects.add(new Fridge(x, y)); break;
					case '$' : objects.add(new ShopCounter(x, y, this.game)); break;
					case 'O' : objects.add(new Computer(x, y, game)); break;
					case 'E' : 
						Entrance home_entrance = new Entrance(x, y, "home"); 
						System.out.println("Entrance added to home");
						home_entrance.attachLevelSwitch(game);
						objects.add(home_entrance);
						
						break;
					case 'M' : 
						Entrance map_entrance = new Entrance(x, y, "map"); 
						System.out.println("Entrance added to map");
						map_entrance.attachLevelSwitch(game);
						objects.add(map_entrance);
						
						break;
					}
	    			x++;
	    		}
	    		
	    		line = reader.readLine();
	    		 
	        	x = 0;
	        	y++;
	    		
	    	}
	    	reader.close();
		}	
		System.out.println(objects.size());
	    game.setGameObjects(objects);

		
    }
	
	public void save(ArrayList<GameObject> objects, ArrayList<Entity> entities) {
		try {

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName + ".ser"));
			CopyOnWriteArrayList<GameObject> copyObjects = new CopyOnWriteArrayList<GameObject>();
		    CopyOnWriteArrayList<Entity> copyEntities = new CopyOnWriteArrayList<Entity>();
		    copyObjects.addAll(objects);
		    copyEntities.addAll(entities);
			oos.writeObject(copyObjects);
			oos.writeObject(copyEntities);
			oos.flush();
			oos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public void render(Graphics g){
		
	}

	public void setObjects(ArrayList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}
	

	public int getBLOC_SIZE() {
		return BLOC_SIZE;
	}

	public void setBLOC_SIZE(int bLOC_SIZE) {
		BLOC_SIZE = bLOC_SIZE;
	}
	
	
	public void zoom(int zoomAmount){
		BLOC_SIZE += zoomAmount;
	}
	
	
}
