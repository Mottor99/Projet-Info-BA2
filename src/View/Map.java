package View;

import Model.Directable;
import Model.GameObject;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Map extends Level {
    private ArrayList<GameObject> objects = null;
    
    private BufferedImage img = null;
    private Screen screen;
    

    public Map(Screen screen){
    	this.screen = screen;
    	this.viewPosX = 12-(int)screen.getWidth()/(BLOC_SIZE*2);
    	this.viewPosY = 12-(int)screen.getHeight()/(BLOC_SIZE*2);
    	System.out.println(viewPosX+ " "+ viewPosY);
    
		try {
			   img = ImageIO.read(new File("src/tile.png"));
			}catch(IOException e) {
			   e.printStackTrace();
		}
    }

    public void render(Graphics g) {
        for (int i = 0; i < MAP_SIZE; i++) { 
            for (int j = 0; j < MAP_SIZE; j++) {
                int x = i-viewPosX;
                int y = j-viewPosY;
                g.setColor(Color.LIGHT_GRAY); //color of the area
                g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
                g.setColor(Color.BLACK); //color in the space between squares
                g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
            }
        }

        for (GameObject object : this.objects) {
            int x = object.getPosX()-viewPosX;
            int y = object.getPosY()-viewPosY;
            int color = object.getColor();

            if (color == 0) {
                //g.setColor(Color.DARK_GRAY);
                g.drawImage(img, x*BLOC_SIZE,y*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, null);
            } else if (color == 1) {
                g.setColor(Color.GRAY);
            } else if (color == 2) {
                g.setColor(Color.BLUE);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            }
            if (color!= 0) {
	            g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
	            g.setColor(Color.BLACK);
	            g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE - 2, BLOC_SIZE - 2);
            }
            // Decouper en fontions
            if(object instanceof Directable) {
                int direction = ((Directable) object).getDirection();
                
                int deltaX = 0;
                int deltaY = 0;
                
                switch (direction) {
                case Directable.EAST:
                    deltaX = +(BLOC_SIZE-2)/2;
                    break;
                case Directable.NORTH:
                    deltaY = -(BLOC_SIZE-2)/2;
                    break;
                case Directable.WEST:
                    deltaX = -(BLOC_SIZE-2)/2;
                    break;
                case Directable.SOUTH:
                    deltaY = (BLOC_SIZE-2)/2;
                    break;
                }

                int xCenter = x * BLOC_SIZE + (BLOC_SIZE-2)/2;
                int yCenter = y * BLOC_SIZE + (BLOC_SIZE-2)/2;
                g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
                
            }
            
        }
        //g.setColor(new Color(200,20,200,127));
        g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        HUD.render(g);
        
        
    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    

	
	
}
