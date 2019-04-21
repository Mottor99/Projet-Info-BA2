package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
}
