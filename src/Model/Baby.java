package Model;

public class Baby extends NPC {
	

	/**
	 * 
	 */
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
	}
	public void changeEnergy(Game g) {
    	if (needState == SLEEPING && energy < 100) {
			energy += 0.1;
		}
		else{
			energy -= 0.1; 
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
			hunger -= 0.04; 
		}
    }
    public void changeBladder(Game g) {
    	if (needState == PEEING && bladder <= 100) {
			bladder += 2;
			if (bladder >= 100) {
				stopPeeing();
			}
		}
		else{
			bladder -= 0.02; 
		}
    }
    public void changeHygiene(Game g) {
    	if (needState == WASHING && hygiene < 100) {
			hygiene += 1;
			if (hygiene >= 100) {
				stopWashing();
			}
		}
		else{
			hygiene -= 0.1; 
		}
    }
}