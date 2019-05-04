package Model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class Entity extends GameObject implements Directable, Movement{

    int direction = EAST;  
    protected boolean isFocused = true;
	protected Thread movement = new Thread(new EntityMovement());
    protected int movX, movY;
    protected double dX, dY = 0.0;
    protected int state = IDLE;
    protected int aX = posX;
    protected int aY = posY;
    protected ArrayList<GameObject> objects;

	public Entity(int X, int Y, int width, int height) {
		super(X, Y, 1, 1);
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
    	//System.out.println("Sprite" + posX);
    	
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
    
    public int getState(){
    	return this.state;
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
					for(int i = 0; i<100; i++){
						dX += 0.01*x;
						dY += 0.01*y;
						if(isFocused)Camera.move(0.01*x, 0.01*y);
						
						//System.out.println("MOVING, "+stage);
						try {
							
							Thread.sleep(1);
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
