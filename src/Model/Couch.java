package Model;

public class Couch extends BlockUnbreakable implements Sellable{

	public Couch(int X, int Y) {
		super(X, Y, 2, 1);
		sprite = Sprite.couch;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 200;
	}
	
}
