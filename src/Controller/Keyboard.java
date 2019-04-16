package Controller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Game;

public class Keyboard implements KeyListener {
    private Game game;

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        switch (key) {
        case KeyEvent.VK_RIGHT:
            game.movePlayer(1, 0);
            
            
            break;
        case KeyEvent.VK_LEFT:
            game.movePlayer(-1, 0);
            
            break;
        case KeyEvent.VK_DOWN:
            game.movePlayer(0, 1);
            
            break;
        case KeyEvent.VK_UP:
            game.movePlayer(0, -1);
            
             break;
        case KeyEvent.VK_SPACE:
             game.action();
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
         	
             */
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
            	  }).start();
             
             	break;
        case KeyEvent.VK_Q:
             game.stop();
             break;
        case KeyEvent.VK_T:
             game.tirePlayer();
             break;
        case KeyEvent.VK_P:
             game.playerPos();
             break;
        
        case KeyEvent.VK_Z:
        	 game.zoomCamera(1);
        	 game.centerCamera();
        	 break;
        
        case KeyEvent.VK_X:
        	game.zoomCamera(-1);
        	game.centerCamera();
        	break;
        case KeyEvent.VK_Y:
        	game.lockCamera();
        	game.centerCamera();
        	break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
