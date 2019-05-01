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
	public void activate() {
		
		talk();
		
		System.out.println(isTalking);
	}

	@Override
	public void talk() {
		this.dialogStage = 0;
		this.currentSentence = this.sentences[this.dialogStage];
		this.isTalking = true;
		
	}
	public void nextSentence(){
		if(this.dialogStage<this.sentences.length-1){
			this.dialogStage++;
			this.currentSentence = this.sentences[this.dialogStage];	
		}else this.isTalking = false;
	}

	public boolean isTalking() {
		return isTalking;
	}
	public String getCurrentSentence() {
		return currentSentence;
	}

	@Override
	public void activate(Player p) {
		// TODO Auto-generated method stub
		
	}

}
