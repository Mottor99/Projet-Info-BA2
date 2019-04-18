package Model;

import java.awt.Graphics;

public class BlockUnbreakable extends Block {

    public BlockUnbreakable(int X, int Y, int width, int height) {
        super(X, Y, width, height, 0);
        sprite = Sprite.wall_cobblestone;
    }
    @Override
	public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(sprite.getImage(), (int)(x*BLOC_SIZE),(int)((y-1)*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*2, null);
    }
    @Override
    public boolean isObstacle() {
        return true;
    }
	
}
