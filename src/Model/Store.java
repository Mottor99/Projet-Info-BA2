package Model;

import java.awt.image.BufferedImage;

public class Store extends Level {
	private static final long serialVersionUID = 1L;

	public Store(Game game){
    	super(game, "src/store");
    }

	@Override
	public BufferedImage getBackground() {
		// TODO Auto-generated method stub
		return Sprite.home_background.getImage();
	}

	@Override
	public int getMapSize() {
		// TODO Auto-generated method stub
		return 25;
	}
	

}
