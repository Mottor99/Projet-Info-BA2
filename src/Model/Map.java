package Model;

import java.awt.image.BufferedImage;

public class Map extends Level {
    
	private static final long serialVersionUID = 1L;

	public Map(Game game){
    	super(game, "src/map");
    }

	@Override
	public BufferedImage getBackground() {
		// TODO Auto-generated method stub
		return Sprite.map_background.getImage();
	}

	@Override
	public int getMapSize() {
		// TODO Auto-generated method stub
		return 75;
	}
	
    

	
	
}
