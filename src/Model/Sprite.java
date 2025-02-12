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
	public transient static Sprite entrance = new Sprite("src/entrance.png");
	public transient static Sprite[] player = {new Sprite("src/homme_right.png"), new Sprite("src/homme_dos.png"), new Sprite("src/walking_l1.png"), new Sprite("src/homme.png")};
	
	public transient static Sprite wall = new Sprite("src/wall.png");
	public transient static Sprite wall_cobblestone = new Sprite("src/wall_cobblestone.png");
	public transient static Sprite unknown = new Sprite("src/tile.png");
	
	public transient static Sprite man = new Sprite("src/homme.png");
	public transient static Sprite woman = new Sprite("src/femme.png");
	
	public transient static Sprite shop_counter = new Sprite("src/shop_counter.png");
	
	public transient static Sprite toilet = new Sprite("src/toilette.png");
	public transient static Sprite bed = new Sprite("src/bed.png");
	public transient static Sprite fridge = new Sprite("src/fridge.png");

	public transient static Sprite shower = new Sprite("src/shower.png");

	public transient static Sprite table = new Sprite("src/table.png");
	public transient static Sprite couch = new Sprite("src/couch.png");

	
	public transient static Sprite baby = new Sprite("src/baby.png");
	public transient static Sprite boy = new Sprite("src/garcon.png");
	public transient static Sprite girl = new Sprite("src/fille.png");
	public transient static Sprite computer = new Sprite("src/computer.png");

	public transient static Sprite[][] walking = {{new Sprite("src/walking_r0.png"), new Sprite("src/walking_r1.png"),new Sprite("src/walking_r2.png"),new Sprite("src/walking_r3.png")},
			{new Sprite("src/walking_up0.png"), new Sprite("src/walking_up1.png"),new Sprite("src/walking_up2.png"),new Sprite("src/walking_up3.png")},
			{new Sprite("src/walking_l0.png"), new Sprite("src/walking_l1.png"),new Sprite("src/walking_l2.png"),new Sprite("src/walking_l3.png")},
			{new Sprite("src/walking_1.png"), new Sprite("src/walking_2.png"),new Sprite("src/walking_3.png"),new Sprite("src/walking_4.png")}};
	
	public transient static Sprite home_background = new Sprite("src/home3.png");
	public transient static Sprite map_background = new Sprite("src/map.png");
	
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
			System.out.println("[Sprite] "+ this.path);
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
