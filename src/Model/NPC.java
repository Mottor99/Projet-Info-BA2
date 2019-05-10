package Model;

import View.Animation;

public abstract class NPC extends Entity implements Activable, Animation, Dialog, MenuActivable, GUIModifier {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected double mood;
	
	protected transient Thread animation;
	
	protected int width; 
	protected int height;
	
	protected String[] sentences = {"ooga", "booga", "bye bye"};
	protected String currentSentence;
	protected int dialogStage = 0;
	protected boolean isTalking = false;
	
	protected Menu menu;
	protected boolean isInMenu = false;
	protected GUIObserver go;
	
    private Action currentAction = null;
	

	public NPC(int x, int y, int width, int height) {
		super(x, y, 1, 1);
		animation = new Thread(this);
		animation.start();
		this.menu = new Menu(this);
		this.menu.addItem(new MenuItem("talk"));
		this.menu.addItem(new MenuItem("cancel"));
		
	}
	@Override
	public void attachGUIObserver(GUIObserver go){
		this.go = go;
	}
	@Override
	public void notifyGUIObserver(){
		go.notifyGUI(this);
	}
	@Override
	public boolean isOpen(){
		return isInMenu||isTalking;
	}

	@Override
	public void activate(Entity p) {
		this.rotate(this.posX-p.getPosX(), this.posY-p.getPosY());
		openMenu();
	}

	@Override
	public void talk() {
		
		this.dialogStage = 0;
		this.currentSentence = this.sentences[this.dialogStage];
		this.isTalking = true;
		notifyGUIObserver();
		
	}
	@Override
	public void nextSentence(){
		if(this.dialogStage<this.sentences.length-1){
			this.dialogStage++;
			this.currentSentence = this.sentences[this.dialogStage];	
		}else{
			this.isTalking = false;
			notifyGUIObserver();
		}
	}
	@Override
	public boolean isTalking() {
		return isTalking;
	}
	@Override
	public String getCurrentSentence() {
		return currentSentence;
	}
	public void openMenu(){
		this.isInMenu = true;
		this.notifyGUIObserver();
	}
	public void closeMenu(){
		this.isInMenu = false;

		this.notifyGUIObserver();
	}
	
	@Override 
	public void menuAction(String action){
		switch(action){
		case "talk": 
			closeMenu();
			talk();
			break;
		
		case "cancel":
			closeMenu();
			break;
		}
	}
	public boolean isInMenu(){
		return this.isInMenu;
	}
	public Menu getMenu(){
		return this.menu;
	}
	public void setCurrentAction(Action action) {
		this.currentAction = null;
			
	}
	public boolean isFocused() {
		return false;
	}

	
	public void growTire(Game g) {
    	if (needState == SLEEPING) {
    		energy = clamp(energy, 1, 100, 0);
    		if(energy>=100) {
    			stopSleeping();
    		}
		}
		else if (energy > 80){
			energy -= 2; 
		}
		else {
			energy -= 0.1;
			if (currentAction == null&& !isOpen()){ 
				currentAction = new SleepAction(this, g);
			}
		}	
    }
    public void growHunger(Game g) {
    	if (needState == EATING) {
			hunger = clamp(hunger, 4, 100, 0);
			if (hunger>=100) {
				stopEating();
			}
		}
    	
		else if (hunger > 20){
			hunger -= 0.04; 
		}
		else {
			hunger -= 0.04;
			if (currentAction == null && !isOpen()){ 
				currentAction = new EatAction(this, g);
			}	
		}
    }
    public void growBladder(Game g) {
    	if (needState == PEEING) {
			bladder = clamp(bladder, 5, 100, 0);
			if(bladder>=100) {
				stopPeeing();
			}
		}
		else if (bladder > 30){
			bladder -= 0.4; 
		}
		else {
			bladder -= 0.4;
			if (currentAction == null && !isOpen()){ 
				currentAction = new PeeAction(this, g);
			}	
		}
    }
    public void growDirt(Game g) {
    	if (needState == WASHING) {
			hygiene = clamp(hygiene, 3, 100, 0);
			if(hygiene>=100) {
				stopWashing();			}
		}
		else if (hygiene > 10){
			hygiene -= 0.1; 
		}
		else {
			hygiene -= 0.1;
			if (currentAction == null && !isOpen()){ 
				currentAction = new WashAction(this, g);
			}	
		}
    }
    
    public void stopSleeping() {
		if (currentAction instanceof SleepAction) {
		((SleepAction) currentAction).stop();
		}
		this.setPosX(getPosX()+1);
		needState = NOTHING;
	}
    public void stopEating() {
		if (currentAction instanceof EatAction) {
		((EatAction) currentAction).stop();
		}
		
		needState = NOTHING;
		
	}
    public void stopPeeing() {
		if (currentAction instanceof PeeAction) {
		((PeeAction) currentAction).stop();
		}
		needState = NOTHING;
	}
    public void stopWashing() {
		if (currentAction instanceof WashAction) {
		((WashAction) currentAction).stop();
		}
		needState = NOTHING;
	}
    @Override
    public void run(){
    	
    }
    @Override 
    public void animate(){
    	
    }
    @Override
	public void setBladder(int bladder) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setHygiene(int hygiene) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setHunger(int hunger) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setEnergy(int energy) {
		// TODO Auto-generated method stub
		
	} 

}
