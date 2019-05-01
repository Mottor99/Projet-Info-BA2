package Model;

public class Bed extends BlockUnbreakable implements Activable, Deletable {

	public Bed(int x, int y) {
		super(x, y, 2, 2);
		sprite = Sprite.couch;
		
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
	public void activate(Player active_player) {
		active_player.setEnergy(100);

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

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

}
