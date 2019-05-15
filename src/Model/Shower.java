package Model;

public class Shower extends BlockUnbreakable implements Activable, Deletable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Shower(int x, int y) {
		super(x, y, 1, 2);
		//sprite = Sprite.shower;
		
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyDeletableObserver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(Entity p) {
		p.wash();

	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}

}
