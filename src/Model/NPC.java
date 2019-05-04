package Model;

import View.Animation;

public abstract class NPC extends Entity implements Activable, Animation, Dialog {
	protected double hunger;
	protected double mood;
	
	protected Thread animation;
	
	protected int width; 
	protected int height;
	
	protected String[] sentences = {"ooga", "booga", "bye bye"};
	protected String currentSentence;
	protected int dialogStage = 0;
	protected boolean isTalking = false;
	

	public NPC(int x, int y, int width, int height) {
		super(x, y, 1, 1);
		animation = new Thread(this);
		animation.start();
		
	}

	@Override
	public void run() {
		
		
	}

	@Override
	public void animate() {
		
		
	}

	@Override
	public void activate(Player p) {
		talk();
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

	

}
