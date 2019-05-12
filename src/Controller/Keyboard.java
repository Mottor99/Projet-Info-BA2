package Controller;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;

//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import Main.Main;
import Model.Game;

public class Keyboard implements KeyListener, Runnable {
    private Game game;
    private int xa, ya = 0;
    private boolean[] pressed = {false, false, false, false}; // {right, left, down, up}

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        switch (key) {
        case KeyEvent.VK_RIGHT:
        	if(!pressed[0]){
        		pressed[0] = true;
        		xa = 1;
        		ya = 0;
        		game.movePlayer(xa, ya);
        	}
            
            
            
            break;
        case KeyEvent.VK_LEFT:
        	if(!pressed[1]){
        		pressed[1] = true;
        		xa = -1;
        		ya = 0;
        		game.movePlayer(xa, ya);
        	}     
            break;
        case KeyEvent.VK_DOWN:
        	if(!pressed[2]){
        		pressed[2] = true;
        		xa = 0;
        		ya = 1;
        		game.movePlayer(xa, ya);
        	}     
            break;
        case KeyEvent.VK_UP:
        	if(!pressed[3]){
        		pressed[3] = true;
        		xa = 0;
        		ya = -1;
        		game.movePlayer(xa, ya);
        	}
            break;
        case KeyEvent.VK_SPACE:
             game.action();
             break;
             /*try {
         		FileInputStream fileInputStream = new FileInputStream("bip.mp3");
         		Player player = new Player(fileInputStream);
         		player.play();
         		System.out.println("Song is playing");  
         		} catch(FileNotFoundException e) {
         			e.printStackTrace();
         			
         		} catch(JavaLayerException e) {
         			e.printStackTrace();
         		}
         	
             
            	  new Thread(new Runnable() {
            	  // The wrapper thread is unnecessary, unless it blocks on the
            	  // Clip finishing; see comments.
            	    public void run() {
            	    	try {
                     		FileInputStream fileInputStream = new FileInputStream("bop.mp3");
                     		Player player = new Player(fileInputStream);
                     		player.play();
                     		System.out.println("Song is playing");  
                     		} catch(FileNotFoundException e) {
                     			e.printStackTrace();
                     			
                     		} catch(JavaLayerException e) {
                     			e.printStackTrace();
                     		}
            	    }
            	  }).start();*/
             
             
        case KeyEvent.VK_Q:
             game.stop();
             break;
        case KeyEvent.VK_A:
             //game.tirePlayer();
             break;
        case KeyEvent.VK_T:
             game.timeAccelerates();
             break;     
        case KeyEvent.VK_P:
             game.playerPos();
             break;
        
        case KeyEvent.VK_Z:
        	 try {
				game.zoomCamera(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 game.centerCamera();
        	 break;
        
        case KeyEvent.VK_X:
        	try {
				game.zoomCamera(-1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	game.centerCamera();
        	break;
        case KeyEvent.VK_Y:
        	game.lockCamera();
        	game.centerCamera();
        	break;
        case KeyEvent.VK_S:
        	Main.save();
        	break;
        }
        
        //System.out.println(pressed[0] + " " + pressed[1]+ " " + pressed[2]+ " " + pressed[3]);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
    	

        switch (key) {
        case KeyEvent.VK_RIGHT:
        	if(!pressed[1]){
        		xa = 0;
        	}else xa = -1;
        	
        	pressed[0] = false;
        	game.movePlayer(xa, ya);
            
            break;
        case KeyEvent.VK_LEFT:
        	if(!pressed[0]){
        		xa = 0;
        	}else xa = 1;
            pressed[1] = false;
            game.movePlayer(xa, ya);
            break;
        case KeyEvent.VK_DOWN:
        	if(!pressed[3]){
        		ya = 0;
        	}else ya = -1;
        	pressed[2] = false;
        	game.movePlayer(xa, ya);
            
            break;
        case KeyEvent.VK_UP:
        	if(!pressed[2]){
        		ya = 0;
        	}else ya = 1;
        	pressed[3] = false;
        	game.movePlayer(xa, ya);
            
             break;
        case KeyEvent.VK_T:
        	game.timeDecelerates();
        }
        //System.out.println(pressed[0] + " " + pressed[1]+ " " + pressed[2]+ " " + pressed[3]);
    }

	@Override
	public void run() {
		
		
	}
}
