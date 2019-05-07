package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class Sprite {
	private BufferedImage img = null;
	private String path;
	public static Sprite brick = new Sprite("src/tile.png");
	public static Sprite grass = new Sprite("src/grass.png");
	public static Sprite player = new Sprite("src/homme.png");
	public static Sprite wall = new Sprite("src/wall.png");
	public static Sprite wall_cobblestone = new Sprite("src/wall_cobblestone.png");
	public static Sprite table = new Sprite("src/table.png");
	public static Sprite couch = new Sprite("src/couch.png");
	public static Sprite unknown = new Sprite("src/tile.png");
	public static Sprite woman = new Sprite("src/femme.png");
	public static Sprite shop_counter = new Sprite("src/shop_counter.png");
	public static Sprite toilet = new Sprite("src/toilettes.png");
	
	public static Sprite bed = new Sprite("src/bed.png");
	public static Sprite fridge = new Sprite("src/fridge.png");
	//public static Sprite baby = new Sprite("src/bebe.png");
	//public static Sprite wardrobe = new Sprite("src/armoire.png");
	public static Sprite computer = new Sprite("src/computer.png");
	
	public static Sprite[] walking = {new Sprite("src/walking_1.png"), new Sprite("src/walking_2.png"),new Sprite("src/walking_3.png"),new Sprite("src/walking_4.png")};
	
	
	public Sprite(String path) {
		this.path = path;
		try {
	    	   img = ImageIO.read(new File(this.path));
	       }catch(IOException e) {
	    	   e.printStackTrace();
	    	   }
	}
	
	public BufferedImage getImage() {
		return this.img;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}
	
}
