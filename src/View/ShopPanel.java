package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;


import Model.Shop;

public class ShopPanel extends JPanel{
	boolean isOpen = false;
	private Shop shop;
	
	public ShopPanel(){
		
		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(200, 100));
		this.setVisible(false);
		this.setFocusable(true);
		this.setOpaque(true);
		this.setBackground(new Color(10, 20, 0));
		
	}
	
	
	public void setShop(Shop shop){
		this.shop = shop;
	}
	public void switchVisibility() {
		this.isOpen = !this.isOpen;
		this.setVisible(isOpen);
	}


	


	
}


