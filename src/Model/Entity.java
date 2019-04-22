package Model;

import java.awt.Graphics;


public abstract class Entity extends GameObject implements Directable, Movement{

    int direction = EAST;  
    protected boolean isFocused = true;
	protected Thread movement;
    protected int movX, movY;
    protected double dX, dY;
    protected int state = IDLE;

	public Entity(int X, int Y, int width, int height, int color) {
		super(X, Y, 1, 1, color);
	}

	public void move(int X, int Y) {
	    	
	    	dX = dY = 0.0;	
	    	
	    	if(state == IDLE){
	    		state=MOVING;
	    		movement = new Thread(new EntityMovement());
	    		movX = X;
	    		movY = Y;
	    		movement.start();
	    		
	    	}
	    	
		
    	
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
			state = MOVING;
			
			for(int i = 0; i<100; i++){
				dX += 0.01*movX;
				dY += 0.01*movY;
				if(isFocused)Camera.move(0.01*movX, 0.01*movY);
				
				//System.out.println("MOVING, "+stage);
				try {
					
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			posX += movX;
			posY += movY;
			movX = movY = 0;
			dX = dY = 0.0;
			state = IDLE;
			
		}
		
	}
}
