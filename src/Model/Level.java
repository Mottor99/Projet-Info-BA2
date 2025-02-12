package Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    public transient static int BLOC_SIZE = 40;
    protected ArrayList<GameObject> objects = new ArrayList<GameObject>();
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    protected int spawnX, spawnY;
    protected String fileName = "src/file";
    protected Game game = null; 
    //protected static transient boolean gameSaved = false;
    //protected transient boolean fileSaved = false;
	private transient static ArrayList<String> levelsSavedSinceNewGame = new ArrayList<String>();
	
	public Level(Game game, String fileName){
		this.game = game;
		this.fileName = fileName;
	}
	
	public void load(){
		System.out.println("[Level] Number of levels saved : " + levelsSavedSinceNewGame.size());
		try {
			if(levelsSavedSinceNewGame.contains(fileName)){
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName + ".ser"));
				objects.addAll((CopyOnWriteArrayList<GameObject>) ois.readObject());
				entities.addAll((CopyOnWriteArrayList<Entity>) ois.readObject());
				ois.close();
				System.out.println("[Level] " +fileName + " loaded from serial");
			}else loadFromText();
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found !");;
			loadFromText();
		} catch (FileNotFoundException e1) {
			System.out.println("File not found !");;
			loadFromText();
		} catch (IOException e) {
			System.out.println("IOException !!!");
			loadFromText();
		}
		
			
		
	    game.setGameObjects(objects);
	    game.setEntities(entities);
	    

		
    }
	private void loadFromText(){
		System.out.println("[Level] Loading from text file...");
		objects.clear();
		entities.clear();
		FileReader file = null;
		BufferedReader reader = null;
		String line = "";
		try{
			file = new FileReader(this.fileName + ".txt");
			reader = new BufferedReader(file);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	int x = 0;
    	int y = 0;
    	while (line != null) {
    		for (int i=0; i<line.length(); i++) {
    			switch (line.charAt(i)) {
				case 'W' : objects.add(new Wall(x, y)); break;
				case 'C' : objects.add(new Couch(x, y)); break;
				case 'B' : objects.add(new Bed(x, y)); break;
				case 'T' : objects.add(new Table(x, y)); break;
				case 'P' : objects.add(new Toilet(x, y)); break;
				case 'S' : objects.add(new Shower(x,y)); break;
				case 'F' : objects.add(new Fridge(x, y)); break;
				case '$' : objects.add(new ShopCounter(x, y)); break;
				case 'O' : objects.add(new Computer(x, y)); break;
				case 'E' : 
					Entrance home_entrance = new Entrance(x, y, "home", 12, 23); 
					System.out.println("Entrance added to home");
					home_entrance.attachLevelSwitch(game);
					objects.add(home_entrance);
					
					break;
				case 'M' : 
					Entrance map_entrance = new Entrance(x, y, "map",15, 14); 
					System.out.println("Entrance added to map");
					map_entrance.attachLevelSwitch(game);
					objects.add(map_entrance);
					
					break;
				case 'H' : 
					Entrance store_entrance = new Entrance(x, y, "store", 12, 23); 
					System.out.println("Entrance added to store");
					store_entrance.attachLevelSwitch(game);
					objects.add(store_entrance);
				
					break;
				case 'm' : 
					Entrance m_entrance = new Entrance(x, y, "map", 25, 14); 
					System.out.println("Entrance added to map");
					m_entrance.attachLevelSwitch(game);
					objects.add(m_entrance);
				
					break;
    			
    			}
    			x++;
    		}
    		
    		try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
        	x = 0;
        	y++;
    	}
    	
    	try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("[Level]" + fileName + " loaded from text");
		
	}
	
	public void save(ArrayList<GameObject> objects, ArrayList<Entity> entities) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName + ".ser"));
			CopyOnWriteArrayList<GameObject> copyObjects = new CopyOnWriteArrayList<GameObject>();
		    CopyOnWriteArrayList<Entity> copyEntities = new CopyOnWriteArrayList<Entity>();
		    copyObjects.addAll(objects);
		    copyEntities.addAll(entities);
		    //fileSaved = true;
		    
		    //oos.writeBoolean(fileSaved);
			oos.writeObject(copyObjects);
			oos.writeObject(copyEntities);
			
			oos.flush();
			oos.close();
			if(!levelsSavedSinceNewGame.contains(fileName)){
		    	levelsSavedSinceNewGame.add(fileName);
		    }
			
			
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
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(levelsSavedSinceNewGame);
	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		System.out.println("[Level] Object read (from save)" + this);
		levelsSavedSinceNewGame = (ArrayList<String>) in.readObject();
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
	public abstract BufferedImage getBackground();
	public abstract int getMapSize();
	
	
}
