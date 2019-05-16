package Model;

import java.awt.image.BufferedImage;

public class Home extends Level {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Home(Game game) {
		super(game, "src/home");
	}

	@Override
	public BufferedImage getBackground() {
		System.out.println("[Home] Background "+ Sprite.home_background.getImage());
		return Sprite.home_background.getImage();
	}

	@Override
	public int getMapSize() {
		// TODO Auto-generated method stub
		return 25;
	}

}
