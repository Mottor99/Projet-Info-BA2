package Model;

import java.awt.Graphics;

import View.Animation;
import View.Screen;

public class Player extends Entity implements Animation {

    int energy = 100;
    private Thread animation;
    

    public Player(int x, int y, int maxBomb) {
        super(x, y, 1, 1);
        sprite = Sprite.player;
        animation = new Thread(this);
        animation.start();
    }

   

    

   // //////////////////////////////////////////////////////////////////////////////////////

   

    
    public double getEnergy() {
    	return energy/100.0;
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
