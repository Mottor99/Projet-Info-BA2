package View;

import Model.GameObject;
import Model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;

public class Window extends JFrame {
	private JPanel groupPanel = new JPanel(new BorderLayout());
	private int height = 720;
	private int width = 1280;
    private Screen screen = new Screen(this);

    public Window(String title) {
    	super(title);
    	HUD hud = new HUD(screen, this);
        // JFrame window = new JFrame("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, width, height);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(screen, BorderLayout.LINE_START);
        //groupPanel.add(status, BorderLayout.LINE_END);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
        
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.screen.setGameObjects(objects);
        this.screen.redraw();
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
		HUD.setPlayer(p);
	}
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public void moveCamera(int x, int y){
		this.screen.moveCamera(x,y);
	}
	public void centerCamera(Player p){
		this.screen.centerCamera(p, this.width, this.height);
	}
	public void zoomCamera(int zoom){
		this.screen.zoom(zoom);
	}
	
}
