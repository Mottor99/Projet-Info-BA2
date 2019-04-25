package Model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;



public class Map extends Level {
    


    public Map(Game game){
    	super(game);
    	fileName = "src/file.txt";
    	Camera.setViewPosX(3);
    	Camera.setViewPosY(3);
    	System.out.println(Camera.getViewPosX()+ " "+ Camera.getViewPosY());
    	try {
			load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
    }

    
    @Override
	public void render(Graphics g) {
        
    	
    	//SHADERS
        //g.setColor(new Color(200,20,200,127));
        //g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        
        
    }

    @Override
	public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    

	
	
}
