package Model;

public class Toilet extends BlockUnbreakable implements Deletable, Activable {

	public Toilet(int x, int y) {
		super(x, y, 1, 1);
		sprite = Sprite.toilet;
		
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
		active_player.setBladder(100);

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
