package Model;

public class Child extends NPC implements Fun{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double fun = 100.0;

	public Child(int x, int y, String s) {
		super (x, y, 1, 1);
		this.menu = new Menu(this);
		switch (s) {
		case "male" : 
			sprite = Sprite.boy; 
			this.menu.addItem(new MenuItem("play with cars"));
			break;
		case "female" : 
			sprite = Sprite.girl; 
			this.menu.addItem(new MenuItem("play with doll"));
			break;
		}
		this.menu.addItem(new MenuItem("cancel"));
		
		
	}
	
	@Override 
	public void menuAction(String action){
		switch(action){
		case "play with cars" :
		case "play with doll": 
			closeMenu();
			play();
			break;
		case "cancel":
			closeMenu();
			break;
		}
	}
	
	public void tic(Game game) {
    	this.changeBladder(game);
		this.changeHygiene(game);
		this.changeHunger(game);
		this.changeEnergy(game);
		this.changeFun(game);
    }
	
	public void play(){
		needState = PLAYING;
	}
	
	public void changeEnergy(Game g) {
    	if (needState == SLEEPING) {
    		energy = clamp(energy, 3, 100, 0);
    		if(energy>=100) {
    			stopSleeping();
    		}
		}
		else if (energy > 20){
			energy = clamp(energy, -0.1, 100, 0); 
		}
		else {
			energy = clamp(energy, -0.1, 100, 0); 
			if (currentAction == null&& !isOpen()){ 
				currentAction = new SleepAction(this, g);
			}
		}	
    }
    public void changeHunger(Game g) {
    	if (needState == EATING) {
			hunger = clamp(hunger, 4, 100, 0);
			if (hunger>=100) {
				stopEating();
			}
		}
    	
		else if (hunger > 20){
			hunger = clamp(hunger, -0.2, 100, 0);  
		}
		else {
			hunger = clamp(hunger, -0.2, 100, 0);  
			if (currentAction == null && !isOpen()){ 
				currentAction = new EatAction(this, g);
			}	
		}
    }
    public void changeHygiene(Game g) {
    	if (needState == WASHING) {
			hygiene = clamp(hygiene, 5, 100, 0);
			if(hygiene>=100) {
				stopWashing();			}
		}
		else if (hygiene > 20){
			hygiene = clamp(hygiene, -0.1, 100, 0);
		}
		else {
			hygiene = clamp(hygiene, -0.1, 100, 0);
			if (currentAction == null && !isOpen()){ 
				currentAction = new WashAction(this, g);
			}	
		}
    }
    public void changeBladder(Game g) {
    	if (needState == PEEING) {
			bladder = clamp(bladder, 5, 100, 0);
			if(bladder>=100) {
				stopPeeing();
			}
		}
		else if (bladder > 20){
			bladder = clamp(bladder, -0.2, 100, 0); 
		}
		else {
			bladder = clamp(bladder, -0.2, 100, 0); 
			if (currentAction == null && !isOpen()){ 
				currentAction = new PeeAction(this, g);
			}	
		}
    }
    
	
	public void changeFun(Game g) {
    	if (needState ==  PLAYING && fun < 100) {
			fun = clamp(fun, 1, 100, 0);
			if (fun >= 100) {
				stopPlaying();
			}
		}
		else{
			fun -= 0.05; 
		}
    }
	
	public void stopPlaying() {
		needState = NOTHING;
	}

	@Override
	public void setFun(double fun) {
		this.fun = fun;
		
	}

	@Override
	public double getFun() {
		return fun/100;
	}

	

}