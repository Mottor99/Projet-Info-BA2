package Model;

import View.Animation;

public class NPC extends Entity implements Activable, Animation {
	protected double hunger;
	protected double mood;
	protected Thread animation;
	protected int width; 
	protected int height;
	

	public NPC(int x, int y, int width, int height) {
		super(x, y, 1, 1);
		animation = new Thread(this);
		animation.start();
		
	}

	@Override
	public void run() {
		
		
	}

	@Override
	public void animate() {
		
		
	}

	@Override
	public void activate() {
		
		
	}

}
