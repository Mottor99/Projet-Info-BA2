package Model;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import View.Animation;


public class Player extends Entity implements Animation, GUIModifier{

    /**
	 * 
	 */
	
	private double energy = 100.0;
	private double hunger = 100.0;
	private double bladder = 100.0;
	private double hygiene = 100.0;
	private int money = 500;
	private int needState = NOTHING;
	private ArrayList<GameObject> inventory = new ArrayList<GameObject>();
    private Action currentAction = null;

	private transient Thread animation;

	private boolean isWorking = false;
    

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1);
        sprite = Sprite.player;
        animation = new Thread(this);
        animation.start();
        //inventory.add(new Couch(1, 1));
        //inventory.add(new Couch(5,5));
    }

   public void start() {
	   animation = new Thread(this);
       animation.start();
   }

    

   // //////////////////////////////////////////////////////////////////////////////////////

    public int getMoney() {
		return money;
	}
    public void setMoney(int money) {
		this.money = money;
	}
    public void pay(int amount) throws RuntimeException{
    	if(this.getMoney()>= amount){
    		this.money -= amount;
    	}else throw new RuntimeException("You don't have enough money !");
    }

	public ArrayList<GameObject> getInventory() {
    	
		return inventory;
	}
	public void addToInventory(GameObject item){
		this.inventory.add(item);
	}   

	public void tic(Game g) {
		this.growBladder(g);
		this.growDirt(g);
		this.growHunger(g);
		this.growTire(g);
		this.moreMoney(g);
	}

	/*public void growTire(Game g) {

		if (needState == SLEEPING && energy < 100) {
			energy += 0.1;
		}
		else if (energy > 10) {
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
		if (hunger > 20) {
			hunger -= 0.05;
		}
		else {
			hunger -= 0.05;
			if (currentAction == null && needState == NOTHING){ 
				currentAction = new EatAction(this, g);
			}
		}
	}
	public void growBladder(Game g) {
		if (bladder > 90) {
			bladder -= 0.1;
		}
		else {
			bladder -= 0.1;
			if (currentAction == null && needState == NOTHING){ 	
				currentAction = new PeeAction(this, g);
			
			}}
	}
	public void growDirt(Game g) {
		if (hygiene > 20) {
			hygiene -= 0.03;
		}
		else {
			hygiene -= 0.03;
			if (currentAction == null && needState == NOTHING){ 
				currentAction = new WashAction(this, g);
			}
			//g.sendPlayerToObject("Shower");
			//g.sendPlayerToObject(Shower.class);
		}
	}
	 
	*/
	/*
	public void move(int X, int Y, ArrayList<GameObject> objects) {
		//if (this.isWorking){
			//isWorking = false;
		//}
		this.objects = objects;
    	if((X!= 0 || Y != 0) && !this.isWorking){

	    	state=MOVING;
    		movX = X;
    		movY = Y;
    		if(!movement.isAlive()){
    			movement = new Thread(new EntityMovement());
    			movement.start();
    		}
    	}
    	else state = IDLE;
		
    		
    	
	
	
}*/
	
	@Override
	public void run() {
		//System.out.println("Player animated");
				while(state>-1){

					if(state==IDLE){
						this.sprite = Sprite.player;
						
					}
					else if(state==MOVING){
						animate();
					}
					try {
						Thread.sleep(2);
						//System.out.println("Player animated");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
	}

	@Override
	public void animate() {
		
		for(int i = 0; i<4; i++){
			if(state == MOVING){
			this.sprite = Sprite.walking[i];
			
			try {
				
				Thread.sleep(150);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		
		
	}


	public void startWorking() {
		isWorking = true;
	}
	public void stopWorking() {
		isWorking = false;
	}
	
	public void moreMoney(Game game) {
		if (this.isWorking) {
			this.money += 1;
		}
	}

	@Override
	public void attachGUIObserver(GUIObserver go) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyGUIObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}



	
	public boolean isWorking() {

		return isWorking;
	}

	
	
	
}
	

