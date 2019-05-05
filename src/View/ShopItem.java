package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Model.GameObject;

public class ShopItem extends JButton{
	private GameObject item;
	public ShopItem(GameObject o, ShopPanel s) {
		this.item = o;
		this.setIcon(new ImageIcon(o.getSprite().getPath()));
		this.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				s.buy(item);
				
			}
			
		});
	}
	public GameObject getObject() {
		return this.item;
	}
}
