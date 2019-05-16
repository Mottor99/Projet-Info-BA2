package Model;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Player extends Entity implements Animation, GUIModifier{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private int money = 500;
	private ArrayList<GameObject> inventory = new ArrayList<GameObject>();
	private transient GUIObserver go;
	private boolean isInInventory;
	private boolean isWorking = false;
	protected transient Thread animation;
    

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1);
        setFocused(true);
        sprite = Sprite.player[1];
        animation = new Thread(this);
        animation.start();
        direction = SOUTH;
        //inventory.add(new Couch(1, 1));
        //inventory.add(new Couch(5,5));
    }

   public void start() {
	   animation = new Thread(this);
       animation.start();
   }
   public void rotate(int x, int y) {
       if(x == 0 && y == -1)
           direction = NORTH;
       else if(x == 0 && y == 1)
           direction = SOUTH;
       else if(x == 1 && y == 0)
           direction = EAST;
       else if(x == -1 && y == 0)
           direction = WEST;
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
		this.changeBladder(g);
		this.changeHygiene(g);
		this.changeHunger(g);
		this.changeEnergy(g);
		this.moreMoney(g);
	}

	
	@Override
	public void run() {
		//System.out.println("Player animated");
				while(state>-1){

					if(state==IDLE){
						this.sprite = Sprite.player[direction];
						
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
			this.sprite = Sprite.walking[direction][i];
			
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

	public boolean isWorking() {

		return isWorking;
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
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return this.isInInventory;
	}

	public void inventoryEvent() {
		this.isInInventory = !this.isInInventory;
		notifyGUIObserver();
		
	}

	public boolean isInInventory() {
		// TODO Auto-generated method stub
		return isInInventory;
	}
	
	
	
}
	

