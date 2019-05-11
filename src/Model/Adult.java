package Model;

public class Adult extends NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Adult(int x, int y, String s) {
		super (x, y, 1, 1);
		switch (s) {
		case "male" : sprite = Sprite.man; break;
		case "female" : sprite = Sprite.woman; break;
		}
		
		
	}


	
}