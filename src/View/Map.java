package View;

import Model.Directable;
import Model.GameObject;
import Model.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.Mouse;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    public final int MAP_SIZE = 25;
    private int BLOC_SIZE = 40;
    private int viewPosX;
    private int viewPosY;
    private BufferedImage img = null;
    

	private Mouse mouseController = null;

    public Map(Window window){
    	this.viewPosX = 12-(int)window.getWidth()/(BLOC_SIZE*2);
    	this.viewPosY = 12-(int)window.getHeight()/(BLOC_SIZE*2);
    	System.out.println(viewPosX+ " "+ viewPosY);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/BLOC_SIZE + viewPosX;
				int y = e.getY()/BLOC_SIZE + viewPosY;
				mouseController.mapEvent(x, y);
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
       try {
    	   img = ImageIO.read(new File("src/tile.png"));
       }catch(IOException e) {
    	   e.printStackTrace();
       }
    }

    public void paint(Graphics g) {
    	super.paintComponents(g);
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
        HUD.render(g);
        
        
    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint();  //appelle paint() du JPanel (Map extends JPanel)
    }

	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
	public int getViewPosX() {
		return viewPosX;
	}

	public int getViewPosY() {
		return viewPosY;
	}

	public void setViewPosX(int viewPosX) {
		this.viewPosX = viewPosX;
	}

	public void setViewPosY(int viewPosY) {
		this.viewPosY = viewPosY;
	}

	public int getBLOC_SIZE() {
		return BLOC_SIZE;
	}

	public void setBLOC_SIZE(int bLOC_SIZE) {
		this.BLOC_SIZE = bLOC_SIZE;
	}
	public void moveCamera(int x, int y){
		this.viewPosX += x;
		this.viewPosY += y;
		
	}
	public void centerCamera(Player p, int width, int height){
		this.viewPosX = p.getPosX()-(int)(width/(2*BLOC_SIZE));
		this.viewPosY = p.getPosY()-(int)(height/(2*BLOC_SIZE));
	}
	public void zoom(int zoomAmount){
		this.BLOC_SIZE += zoomAmount;
	}
	
}
