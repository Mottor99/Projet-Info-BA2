package View;

import Model.Camera;
import Model.GameObject;
import Model.Sprite;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;


public class Map extends Level {
    private CopyOnWriteArrayList<GameObject> objects = null;
    

    public Map(Screen screen){
    	this.screen = screen;
    	Camera.setViewPosX(12-screen.getWidth()/(BLOC_SIZE*2));
    	Camera.setViewPosY(12-screen.getHeight()/(BLOC_SIZE*2));
    	System.out.println(Camera.getViewPosX()+ " "+ Camera.getViewPosY());
    
		
    }

    @Override
	public void render(Graphics g) {
        
    	double viewPosX = Camera.getViewPosX();
    	double viewPosY = Camera.getViewPosY();
    	for(double i = -20; i<45; i++){
    		for(int j = -20; j<45;j++){
    			
    			double x = i-viewPosX;
    			double y = j-viewPosY;
    			g.drawImage(Sprite.grass.getImage(), (int)Math.round((x*BLOC_SIZE)), (int)Math.round((y*BLOC_SIZE)), BLOC_SIZE, BLOC_SIZE, null);
    		}
    	}
    	Collections.sort(objects);
    	for (GameObject object : this.objects) {
            double x = object.getPosX()-viewPosX;
            double y = object.getPosY()-viewPosY;
            object.render(x, y, g, BLOC_SIZE);
    	}
    	
    	//SHADERS
        //g.setColor(new Color(200,20,200,127));
        //g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        
        
    }

    @Override
	public void setObjects(CopyOnWriteArrayList<GameObject> objects) {
        this.objects = objects;
    }

    

	
	
}
