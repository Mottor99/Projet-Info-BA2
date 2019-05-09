package Model;

import View.Animation;

public abstract class NPC extends Entity implements Activable, Animation, Dialog, MenuActivable, GUIModifier {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double hunger;
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
	public void activate(Player p) {
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

	

}
