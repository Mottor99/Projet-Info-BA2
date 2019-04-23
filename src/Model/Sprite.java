package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {
	private BufferedImage img = null;
	private String path;
	
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
