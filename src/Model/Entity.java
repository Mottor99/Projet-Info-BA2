package Model;

import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class Entity extends GameObject implements Directable, Animation, Movement, Hunger, Hygiene, Bladder, Energy {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int direction = EAST;  
    protected boolean isFocused = false;
	protected transient Thread movement = new Thread(); 
    protected int movX, movY;
    protected double dX, dY = 0.0;
    protected int state = IDLE;
    protected int aX = posX;
    protected int aY = posY;
    
    protected ArrayList<GameObject> objects;
    
    
    protected int needState = NOTHING;
    protected double energy = 100.0;
	protected double hunger = 100.0;
	protected double bladder = 100.0;
	protected double hygiene = 100.0;

	public Entity(int X, int Y, int width, int height) {
		super(X, Y, 1, 1);
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	      in.defaultReadObject();
	      
	      this.movement = new Thread();
	}

	public void move(int X, int Y, ArrayList<GameObject> objects) {
	    	this.objects = objects;
	    	if(X!= 0 || Y != 0){

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
	///////////////////////////
	
	@Override
	public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(this.sprite.getImage(), (int)((x+dX)*BLOC_SIZE),(int)((y-1+dY)*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*2, null);

    	
    }
	
	public boolean isFocused() {
		return isFocused;
	}

	public void setFocused(boolean isFocused) {
		this.isFocused = isFocused;
	}
	
	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getAX(){
		return aX;
	}
	public int getAY(){
		return aY;
	}

    public int getFrontX() {
        int delta = 0;
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return this.posX + delta;
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return this.posY + delta;
    }
    public void setPosX(int posX) {
		this.posX = posX;
		this.aX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
		this.aY = posY;
	}
    public int getState(){
    	return this.state;
    }
    
    public void tic(Game game) {
    	this.changeBladder(game);
		this.changeHygiene(game);
		this.changeHunger(game);
		this.changeEnergy(game);
    }
    public void sleep() {
		needState = SLEEPING;
	}
	public void pee() {
		needState = PEEING;
	}
	public void wash() {
		needState = WASHING;
	}
	public void eat() {
		needState = EATING;
	}

	
    public double getHygiene() {
		return hygiene/100;
	}

	public void setHygiene(double hygiene) {
		this.hygiene = hygiene;
	}
	
	public double getBladder() {
		return bladder/100;
	}
	
	public void setBladder(double bladder) {
		this.bladder = bladder;
	}

	public double getHunger() {
		return hunger/100;
	}

	public void setHunger(double hunger) {
		this.hunger = hunger;
	}

	public double getEnergy() {
    	return energy/100;
    }
    
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
    public void changeEnergy(Game g) {
    	if (needState == SLEEPING && energy < 100) {
			energy = clamp(energy, 0.5, 100, 0);
		}
		else{
			energy = clamp(energy, -0.025, 100, 0);
		}
    }
    public void changeHunger(Game g) {
    	if (needState == EATING && hunger < 100) {
    		hunger = clamp(hunger, 4, 100, 0);
			if (hunger >= 100) {
				stopEating();
			}
		}
		else{
			hunger = clamp(hunger, -0.1, 100, 0); 
		}
    }
    public void changeBladder(Game g) {
    	if (needState == PEEING && bladder <= 100) {
    		bladder = clamp(bladder, 5, 100, 0);
			if (bladder >= 100) {
				stopPeeing();
			}
		}
		else{
			bladder = clamp(bladder, -0.15, 100, 0); 
		}
    }
    public void changeHygiene(Game g) {
    	if (needState == WASHING && hygiene < 100) {
    		hygiene = clamp(hygiene, 4, 100, 0);
			if (hygiene >= 100) {
				stopWashing();
			}
		}
		else{
			hygiene = clamp(hygiene, -0.05, 100, 0);
		}
    }
    
    public void stopSleeping() {
		needState = NOTHING;
	}
    
    public void stopEating() {
		needState = NOTHING;
	}
    
    public void stopPeeing() {
		needState = NOTHING;
	}
    
    public void stopWashing() {
		needState = NOTHING;
	}
    
    public int getNeedState() {
		return needState;
	}






class EntityMovement implements Runnable{
		
		@Override
		public void run() {
			while(state == MOVING){
				int x = movX;
				int y = movY;
				int nextX = posX + x;
		        int nextY = posY + y;
		        CopyOnWriteArrayList<GameObject> copy = new CopyOnWriteArrayList<GameObject>();
		        copy.addAll(objects);
				boolean obstacle = false;
				boolean isEntrance = false;
				Entrance entrance = null;
		        for (GameObject object : copy) {
		            if (object.isAtPosition(nextX, nextY)) {
		                obstacle = object.isObstacle();
		                isEntrance = object instanceof Entrance;
		            }
		            if (obstacle || isEntrance) {
		            	if(isEntrance) {
		            		entrance = (Entrance) object;
		            	}
		                break;
		            }
		        }
				if(!obstacle){
					aX = nextX;
					aY = nextY;
					for(int i = 0; i<50; i++){
						dX += 0.02*x;
						dY += 0.02*y;
						if(isFocused())Camera.move(0.02*x, 0.02*y);
						
						
						//System.out.println("MOVING, "+stage);
						try {
							
							Thread.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					posX += x;
					posY += y;
					dX = dY = 0.0;
					if(isEntrance && entrance != null) {
						state = IDLE;
						entrance.notifyLevelSwitchObservers();
						
					}
					
				}else state = IDLE;
			}
		}
		
	}











}
