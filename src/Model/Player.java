package Model;

import java.awt.Graphics;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import View.Animation;
import View.Screen;

public class Player extends Entity implements Animation, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int energy = 100;
	private int hunger = 100;
	private int bladder = 100;
	private int hygiene = 100;
	private Couch c;
	private ArrayList<GameObject> inventory = new ArrayList<GameObject>();
    

	private transient Thread animation;
    

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1);
        sprite = Sprite.player;
        animation = new Thread(this);
        animation.start();
    }

   

    

   // //////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<GameObject> getInventory() {
    	inventory.add(c);
		return inventory;
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
		}
	}
	public void growHunger(Game g) {
		if (hunger > 20) {
			hunger -= 0.1;
		}
		else {
			g.sendPlayerToObject("Fridge");
		}
	}
	public void growBladder(Game g) {
		if (bladder > 20) {
			bladder -= 0.1;
		}
		else {
			g.sendPlayerToObject("Toilet");
		}
	}
	public void growHygiene(Game g) {
		if (hygiene > 20) {
			hygiene -= 0.1;
		}
		else {
			g.sendPlayerToObject("Shower");
		}
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
	
	}
	

