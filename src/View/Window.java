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
    private Map map = new Map(this);
    private Status status = new Status();

    public Window(String title) {
    	super(title);
        // JFrame window = new JFrame("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, width, height);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(map, BorderLayout.LINE_START);
        //groupPanel.add(status, BorderLayout.LINE_END);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }

    public void update() {
        this.map.redraw();
        this.status.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.map.addMouse(m);
    }

	public int getMapSize() {
		return map.MAP_SIZE;
	}
	
	public void setPlayer(Player p) {
		status.setPlayer(p);
	}
	public Map getMap(){
		return map;
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
		map.moveCamera(x,y);
	}
	public void centerCamera(Player p){
		map.centerCamera(p, this.width, this.height);
	}
	public void zoomCamera(int zoom){
		map.zoom(zoom);
	}
}
