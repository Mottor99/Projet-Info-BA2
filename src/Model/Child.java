package Model;

public class Child extends NPC {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Child(int x, int y, String s) {
		super (x, y, 1, 1);
		switch (s) {
		case "male" : sprite = Sprite.boy; break;
		case "female" : sprite = Sprite.girl; break;
		}
		
		
		
	}
	

}