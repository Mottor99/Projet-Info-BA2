package View;

import Model.Camera;
import Model.GUIModifier;
import Model.GUIObserver;
import Model.GameObject;
import Model.NPC;
import Model.Player;
import Model.Shop;
import Model.Time;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;
import Main.Main;

public class Window extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel groupPanel = new JPanel(new BorderLayout());
	private int height = 720;
	private int width = 1280;
    private Screen screen = new Screen(this, new BorderLayout());

    public Window(String title) {
    	super(title);
        // JFrame window = new JFrame("Game");
    	this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, width, height);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(screen, BorderLayout.LINE_START);
        //groupPanel.add(status, BorderLayout.LINE_END);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
       
        
    }

    public void setGameObjects(ArrayList<GameObject> arrayList) {
        this.screen.setGameObjects(arrayList);
        //this.screen.redraw();
    }
    
    
    public void update() {
        this.screen.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.screen.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.screen.addMouse(m);
    }

	public int getMapSize() {
		return screen.MAP_SIZE;
	}
	
	public void setPlayer(Player p) {
		this.screen.setPlayer(p);
	}
	public void setNPC(NPC npc) {
		HUD.setNPC(npc);
	}
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}
	

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void centerCamera(Player p){
		Camera.center(p, this.width, this.height);
	}
	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public void zoomCamera(int zoom){
		this.screen.zoom(zoom);
	}
	
	public void setTime(Time time) {
		screen.setTime(time);
	}


	public void notifyGUI(GUIModifier gm) {
		
		screen.notifyGUI(gm);
		
		
	}

	
}
