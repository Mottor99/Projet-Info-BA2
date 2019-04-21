package Model;

import java.awt.Graphics;

import View.Animation;
import View.Map;

public class Player extends GameObject implements Directable, Animation, Movement {

    int energy = 100;
    int direction = EAST;  
    private boolean isFocused = true;
    private Thread animation;
    private Thread movement;
    private int movX, movY;
    private double dX, dY;
    private int speed;
    private int state = IDLE;
    private int stage = 0;

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1, 2);
        sprite = Sprite.player;
        animation = new Thread(this);
        animation.start();
    }

    public void move(int X, int Y) {
    	
    	dX = dY = 0.0;	
    	
    	if(state == IDLE){
    		movement = new Thread(new PlayerMovement());
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

   // //////////////////////////////////////////////////////////////////////////////////////

    @Override
	public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(this.sprite.getImage(), (int)((x+dX)*BLOC_SIZE),(int)((y-1+dY)*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*2, null);
    	//System.out.println("Sprite" + posX);
    	
    }
    
    @Override
    public boolean isObstacle() {
        return false;
    }

    public boolean isFocused() {
		return isFocused;
	}

	public void setFocused(boolean isFocused) {
		this.isFocused = isFocused;
	}

	@Override
    public int getDirection() {
    return direction;
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
    
    public double getEnergy() {
    	return energy/100.0;
    }
    public int getState(){
    	return this.state;
    }

	public void tire() {
		if (energy > 10)
			energy -= 10;
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
						Thread.sleep(1);
						//System.out.println("Player animated");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
	}

	@Override
	public void animate() {
		
		for(int i = 0; i<8; i++){
			if(state == MOVING){
			this.sprite = Sprite.walking[i/2];
			
			//System.out.println("MOVING, "+stage);
			try {
				
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		
	//}
		
	}
	class PlayerMovement implements Runnable{
		
		@Override
		public void run() {
			state = MOVING;
			for(int i = 0; i<10; i++){
				dX += 0.1*movX;
				dY += 0.1*movY;
				
				//System.out.println("MOVING, "+stage);
				try {
					
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			state = IDLE;
			posX += movX;
			posY += movY;
			movX = movY = 0;
			dX = dY = 0.0;
			
		}
		
	}
}
