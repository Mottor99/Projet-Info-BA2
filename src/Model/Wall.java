package Model;

import java.awt.Graphics;

public class Wall extends BlockUnbreakable{

	public Wall(int X, int Y) {
		super(X, Y, 1, 1);
		sprite = Sprite.wall_cobblestone;
	}
	@Override
	public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(sprite.getImage(), (int)Math.ceil(x*BLOC_SIZE),(int)Math.ceil((y-1)*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*2, null);
    }

}
