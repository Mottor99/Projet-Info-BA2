package Model;

public class Baby extends NPC {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Baby(int x, int y, String s) {
		super (x, y, 1, 1);
		sprite = Sprite.baby;
		
		
	}
}