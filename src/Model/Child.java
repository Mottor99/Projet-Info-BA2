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
	
	public void changeFun(Game g) {
    	if (needState ==  PLAYING && fun < 100) {
			fun = clamp(fun, 1, 100, 0);
			if (fun >= 100) {
				stopPlaying();
			}
		}
		else{
			fun -= 0.04; 
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