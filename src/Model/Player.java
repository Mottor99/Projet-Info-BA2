package Model;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import View.Animation;

public class Player extends Entity implements Animation, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int energy = 100;
	private int hunger = 100;
	private int bladder = 100;
	private int hygiene = 100;
	private int money = 500;
	private ArrayList<GameObject> inventory = new ArrayList<GameObject>();
    

	private Thread animation;
	public boolean isWorking;
    

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1);
        sprite = Sprite.player;
        animation = new Thread(this);
        animation.start();
        //inventory.add(new Couch(1, 1));
        //inventory.add(new Couch(5,5));
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

    
    public double getHygiene() {
		return hygiene/100.0;
	}

	public void setHygiene(int hygiene) {
		this.hygiene = hygiene;
	}


	public double getBladder() {
		return bladder/100.0;
	}

	public void setBladder(int bladder) {
		this.bladder = bladder;
	}





	public double getHunger() {
		return hunger/100.0;
	}





	public void setHunger(int hunger) {
		this.hunger = hunger;
	}





	public double getEnergy() {
    	return energy/100.0;
    }
    

	public void setEnergy(int energy) {
		this.energy = energy;
	}





	public void tire(Game g) {

		if (energy > 10)
			energy -= 0.1;
		else {
			g.sendPlayerToObject("Bed");
			//g.sendPlayerToObject(Bed.class);
		}
	}
	public void growHunger(Game g) {
		if (hunger > 20) {
			hunger -= 0.1;
		}
		else {
			g.sendPlayerToObject("Fridge");
			//g.sendPlayerToObject(Fridge.class);
		}
	}
	public void growBladder(Game g) {
		if (bladder > 20) {
			bladder -= 0.1;
		}
		else {
			g.sendPlayerToObject("Toilet");
			//g.sendPlayerToObject(Toilet.class);
		}
	}
	public void growHygiene(Game g) {
		if (hygiene > 20) {
			hygiene -= 0.1;
		}
		else {
			g.sendPlayerToObject("Shower");
			//g.sendPlayerToObject(Shower.class);
		}
	}
	
	
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
		
    		
    	
	
	
}
	
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





	
	
}
	

