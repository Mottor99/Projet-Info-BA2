package Model;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import View.Screen;

public abstract class Level{
	protected Screen screen;
	public final int MAP_SIZE = 25;
    public static int BLOC_SIZE = 40;
    protected ArrayList<GameObject> objects = new ArrayList<GameObject>();
    protected int spawnX, spawnY;
    protected String fileName = "src/file.txt";
    protected Game game;
	
	
	public Level(Game game, String fileName){
		this.game = game;
		this.fileName = fileName;
	}
	public void load() throws Exception {
    	FileReader file = new FileReader(this.fileName);
    	BufferedReader reader = new BufferedReader(file);
    	
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
				case '$' : objects.add(new ShopCounter(x, y)); break;
				case 'O' : objects.add(new Computer(x, y)); break;
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
    	game.setGameObjects(objects);
    	
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
