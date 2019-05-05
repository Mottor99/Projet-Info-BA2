package View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Deletable;
import Model.DeletableObserver;
import Model.GameObject;
import Model.Player;

public class InventoryBox extends JPanel implements DeletableObserver{
	boolean isOpen = false;
	private ArrayList<InventoryItem>items = new ArrayList<InventoryItem>();
	private int colonnes = 2;
	private int lignes = 10;
	private GridLayout layout = new GridLayout(lignes, colonnes);
	public InventoryBox(){
		layout.setHgap(5);
		layout.setVgap(5);
		this.setBorder(new EmptyBorder(5, 5 , 5, 5));
		this.setLayout(layout);

		this.setPreferredSize(new Dimension(300, 100));
		this.setVisible(false);
		this.setFocusable(true);
		this.setOpaque(true);
		this.setBackground(Color.DARK_GRAY);
		
	}
	
	
	public void setInventory(ArrayList<GameObject> inventory, Screen screen) {
		
		this.items.clear();
		for(GameObject o : inventory){
		    InventoryItem item = new InventoryItem(o);
		    item.attachDeletable(this);
		    item.attachDraggable(screen);
		    this.items.add(item);
		}
		reorganize();
		
	}
	public void reorganize(){
		this.removeAll();
		for(InventoryItem item : this.items){
			this.add(item);
		}
		int i = this.items.size();
		while(i<this.lignes*this.colonnes){
			JPanel pan = new JPanel();
			pan.setBackground(Color.LIGHT_GRAY);
			this.add(pan);
			i++;
		}
	}
	public void switchVisibility() {
		this.isOpen = !this.isOpen;
		this.setVisible(isOpen);
	}


	public void setPlayer(Player p, Screen screen) {
		this.setInventory(p.getInventory(), screen);
		
	}


	@Override
	public void delete(Deletable d, CopyOnWriteArrayList<GameObject> loot) {
		items.remove(d);
		this.remove((InventoryItem)d); 
		this.reorganize();
		this.validate();
		this.repaint();
		
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
