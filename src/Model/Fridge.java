package Model;

public class Fridge extends BlockUnbreakable implements Activable, Deletable {

	public Fridge(int x, int y) {
		super(x, y, 1, 2);
		sprite = Sprite.table;
		
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
		active_player.setHunger(100);

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
