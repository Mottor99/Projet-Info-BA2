package Model;

public class Table extends BlockUnbreakable implements Sellable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Table(int X, int Y) {
		super(X, Y, 2, 2);
		// TODO Auto-generated constructor stub
		sprite = Sprite.table;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 100;
	}

}
