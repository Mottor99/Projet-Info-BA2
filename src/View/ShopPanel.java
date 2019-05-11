package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Dialog;
import Model.GameObject;
import Model.Sellable;
import Model.Shop;
import Model.ShopCounter;

public class ShopPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isOpen = false;
	private Shop shop;
	private ArrayList<ShopItem> items = new ArrayList<ShopItem>();
	private int lignes = 10;
	private int colonnes = 2;
	public ShopPanel(){
		
		this.setLayout(new GridLayout(10, 2));
		this.setPreferredSize(new Dimension(300, 100));
		this.setVisible(false);
		this.setFocusable(true);
		this.setOpaque(true);
		this.setBackground(new Color(10, 20, 0));
		
	}
	
	public void buy(GameObject item){
		this.shop.buyItem(item);
	}
	public void setShop(Shop shop){
		this.shop = shop;
		this.setInventory(shop.getItems());
	}
	public void setInventory(ArrayList<GameObject> inventory) {
		
		this.items.clear();
		for(GameObject o : inventory){
		    ShopItem item = new ShopItem(o, this);
		    this.items.add(item);
		}
		reorganize();
		
	}
	public void reorganize(){
		this.removeAll();
		for(ShopItem item : this.items){
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
	public void switchVisibility(Shop shop) {
		this.setShop(shop);
		this.isOpen = !this.isOpen;
		this.setVisible(isOpen);
	}
		
	


	


	
}


