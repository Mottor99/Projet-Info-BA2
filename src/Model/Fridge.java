package Model;

import java.awt.Graphics;

public class Fridge extends BlockUnbreakable implements Activable{

	public Fridge(int x, int y) {
		super(x, y, 1, 1);
		sprite = Sprite.fridge;
		
	}

	@Override
	public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(sprite.getImage(), (int)Math.ceil(x*BLOC_SIZE),(int)Math.ceil((y-1)*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*2, null);
    }

	@Override
	public void activate(Entity p) {
		p.eat();

	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}
	

	

}
