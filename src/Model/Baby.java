package Model;

public class Baby extends NPC {
	

	/**
	 * 
	 */
	protected String[] babySentences = {"gaga googoo", "*happy baby noises*"};
	private static final long serialVersionUID = 1L;

	public Baby(int x, int y) {
		super (x, y, 1, 1);
		//sprite = Sprite.baby;
		this.menu = new Menu(this);
		this.menu.addItem(new MenuItem("change baby's diaper"));
		this.menu.addItem(new MenuItem("cradle the baby to sleep"));
		this.menu.addItem(new MenuItem("give fruit puree"));
		this.menu.addItem(new MenuItem("clean the baby"));
		this.menu.addItem(new MenuItem("cancel"));
		this.sentences = babySentences;
		
		
	}
	
	@Override 
	public void menuAction(String action){
		switch(action){
		case "change baby's diaper": 
			closeMenu();
			pee();
			break;
		
		case "cradle the baby to sleep": 
			closeMenu();
			sleep();
			break;
		case "give fruit puree": 
			closeMenu();
			eat();
			break;
		case "clean the baby": 
			closeMenu();
			wash();
			break;
		case "cancel":
			closeMenu();
			break;
		
		}
		talk();
	}
	public void changeEnergy(Game g) {
    	if (needState == SLEEPING && energy < 100) {
			energy = clamp(energy, 0.2, 100, 0);
		}
		else{
			energy = clamp(energy, -0.1, 100, 0);
		}
    }
    public void changeHunger(Game g) {
    	if (needState == EATING && hunger < 100) {
			hunger = clamp(hunger, 1, 100, 0);
			if (hunger >= 100) {
				stopEating();
			}
		}
		else{
			hunger = clamp(hunger, -0.04, 100, 0); 
		}
    }
    public void changeBladder(Game g) {
    	if (needState == PEEING && bladder <= 100) {
			bladder = clamp(bladder, 2, 100, 0);
			if (bladder >= 100) {
				stopPeeing();
			}
		}
		else{
			bladder = clamp(bladder, -0.02, 100, 0);
		}
    }
    public void changeHygiene(Game g) {
    	if (needState == WASHING && hygiene < 100) {
			hygiene = clamp(hygiene, 3, 100, 0);
			if (hygiene >= 100) {
				stopWashing();
			}
		}
		else{
			hygiene = clamp(hygiene, -0.01, 100, 0);
		}
    }
}