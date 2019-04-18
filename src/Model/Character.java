package Model;

public class Character extends Entity implements Directable{

	public Character(int X, int Y, int color) {
		super(X, Y, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
