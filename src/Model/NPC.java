package Model;

import View.Animation;

public abstract class NPC extends Entity implements Activable, Animation, Dialog, MenuActivable {
	
	protected transient Thread animation;
	
	protected int width; 
	protected int height;
	
	protected String[] sentences = {"ooga", "booga", "bye bye"};
	protected String currentSentence;
	protected int dialogStage = 0;
	protected boolean isTalking = false;
	
	protected Menu menu;
	protected boolean isInMenu = false;
	
    private Action currentAction = null;
    private int needState = NOTHING;
    private double energy = 100.0;
	private double hunger = 100.0;
	private double bladder = 100.0;
	private double hygiene = 100.0;
	

	public NPC(int x, int y, int width, int height) {
		super(x, y, 1, 1);
		animation = new Thread(this);
		animation.start();
		this.menu = new Menu(this);
		this.menu.addItem(new MenuItem("talk"));
		this.menu.addItem(new MenuItem("cancel"));
		
	}

	@Override
	public void run() {
		
		
	}

	@Override
	public void animate() {
		
		
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
		
	}
	@Override
	public void nextSentence(){
		if(this.dialogStage<this.sentences.length-1){
			this.dialogStage++;
			this.currentSentence = this.sentences[this.dialogStage];	
		}else this.isTalking = false;
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
	}
	public void closeMenu(){
		this.isInMenu = false;
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

	public void growTire(Game g) {
    	if (needState == SLEEPING && energy < 100) {
			energy += 0.1;
		}
		else if (energy > 20){
			energy -= 0.1; 
		}
		else {
			energy -= 0.1;
			if (currentAction == null){ 
				currentAction = new SleepAction(this, g);
			}
		}	
    }
    public void growHunger(Game g) {
    	if (needState == EATING && hunger < 100) {
			hunger += 1;
		}
		else if (hunger > 20){
			hunger -= 0.04; 
		}
		else {
			energy -= 0.04;
			if (currentAction == null){ 
				currentAction = new EatAction(this, g);
			}	
		}
    }
    public void growBladder(Game g) {
    	if (needState == PEEING && bladder < 100) {
			bladder += 2;
		}
		else if (bladder > 10){
			bladder -= 0.02; 
		}
		else {
			bladder -= 0.02;
			if (currentAction == null){ 
				currentAction = new PeeAction(this, g);
			}	
		}
    }
    public void growDirt(Game g) {
    	if (needState == WASHING && hygiene < 100) {
			hygiene += 1;
		}
		else if (hygiene > 10){
			hygiene -= 0.1; 
		}
		else {
			hygiene -= 0.1;
			if (currentAction == null){ 
				currentAction = new WashAction(this, g);
			}	
		}
    }
    public void stopSleeping() {
		if (currentAction instanceof SleepAction) {
		((SleepAction) currentAction).stop();
		}
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

}
