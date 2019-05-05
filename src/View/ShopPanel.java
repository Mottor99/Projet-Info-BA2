package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Model.Dialog;
import Model.GameObject;
import Model.Sellable;
import Model.Shop;
import Model.ShopCounter;

public class ShopPanel extends JPanel{
	boolean hasFocus = false;
	private Shop shop;
	
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
		for(GameObject item : this.shop.getItems()){
			this.add(new ShopItem(item, this));
		}
	}
	public void render(ShopCounter object){
		if(object!=null){
			
			this.setShop(object.getShop());
			this.setVisible(true);
			this.hasFocus = true;
			this.repaint();
		}
		else{
			this.setVisible(false);
			this.hasFocus = false;
		}
		
		
	}
	public boolean hasFocus(){
		return this.hasFocus;
	}


	


	
}


