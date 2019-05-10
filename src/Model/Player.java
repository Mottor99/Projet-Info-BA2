package Model;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import View.Animation;


public class Player extends Entity implements Animation, GUIModifier{

    /**
	 * 
	 */
	
	private int money = 500;
	private ArrayList<GameObject> inventory = new ArrayList<GameObject>();

	private transient Thread animation;

	private boolean isWorking = false;
    

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1);
        setFocused(true);
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

	@Override
	public void setHunger(int hunger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHygiene(int hygiene) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBladder(int bladder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnergy(int energy) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
	

