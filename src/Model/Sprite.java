package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class Sprite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient BufferedImage img = null;
	private String path;
	public transient static Sprite brick = new Sprite("src/tile.png");
	public transient static Sprite grass = new Sprite("src/grass.png");
	public transient static Sprite player = new Sprite("src/homme.png");
	
	public transient static Sprite wall = new Sprite("src/wall.png");
	public transient static Sprite wall_cobblestone = new Sprite("src/wall_cobblestone.png");
	public transient static Sprite unknown = new Sprite("src/tile.png");
	
	public transient static Sprite man = new Sprite("src/homme.png");
	public transient static Sprite woman = new Sprite("src/femme.png");
	
	public transient static Sprite shop_counter = new Sprite("src/shop_counter.png");
	
	public transient static Sprite toilet = new Sprite("src/toilettes.png");
	public transient static Sprite bed = new Sprite("src/bed.png");
	public transient static Sprite fridge = new Sprite("src/fridge.png");
	public transient static Sprite shower = new Sprite("src/shower.png");
	public transient static Sprite table = new Sprite("src/table.png");
	public transient static Sprite couch = new Sprite("src/couch.png");
	
	public transient static Sprite baby = new Sprite("src/bebe.png");
	public transient static Sprite boy = new Sprite("src/garcon.png");
	public transient static Sprite girl = new Sprite("src/fille.png");

	public transient static Sprite computer = new Sprite("src/computer.png");

	public transient static Sprite[] walking = {new Sprite("src/walking_1.png"), new Sprite("src/walking_2.png"),new Sprite("src/walking_3.png"),new Sprite("src/walking_4.png")};
	
	
	
	public Sprite(String path) {
		this.path = path;
		try {
	    	   img = ImageIO.read(new File(this.path));
	       }catch(IOException e) {
	    	   //e.printStackTrace();
	    	   }
	}
	
	public void load() {
		try {
			this.img = ImageIO.read(new File(this.path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
