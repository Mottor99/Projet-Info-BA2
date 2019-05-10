package Model;

public class Adult extends NPC {
	
	private int energy = 100;
	private int hunger = 100;
	private int bladder = 100;
	private int hygiene = 100;


	public Adult(int x, int y, String s) {
		super (x, y, 1, 1);
		switch (s) {
		case "male" : sprite = Sprite.man; break;
		case "female" : sprite = Sprite.woman; break;
		}
		
		
	}


	
}