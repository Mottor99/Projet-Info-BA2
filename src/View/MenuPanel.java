package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Dialog;
import Model.Menu;
import Model.MenuActivable;

public class MenuPanel extends JPanel{
	private JLabel label = new JLabel();
	private boolean hasFocus = false;
	private Menu menu;
	public MenuPanel(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000, 100));
		this.setVisible(false);
		this.setFocusable(true);
		this.setOpaque(true);
		this.setBackground(new Color(0, 10, 50));
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent event) {
				int key = event.getKeyCode();

		        switch (key) {
		        	
		        case KeyEvent.VK_DOWN:
		        	menu.nextItem();
		        	break;
		        case KeyEvent.VK_UP:
		        	menu.previousItem();
		        	break;
		        case KeyEvent.VK_SPACE:
		        	menu.click();
		        	break;
		        }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		String text = "<HTML>";
		if(menu != null){
			for(String item : menu.getItemsByLabel()){
				text += item + "<BR>";
			}
		}
		text += "</HTML>";
		label.setText(text);
		label.setForeground(Color.LIGHT_GRAY);
		
		
		this.add(label, BorderLayout.LINE_START);
	}
	public void render(MenuActivable object){
		if(object!= null){
			this.menu = object.getMenu();
			String text = "<HTML>";
			
			for(String item : menu.getItemsByLabel()){
				text += item + "<BR>";
			}
			
			text += "</HTML>";
			label.setText(text);
			this.setVisible(true);
			this.hasFocus = true;
		}else{
			this.setVisible(false);
			this.hasFocus = false;
		}
	}
	
	public boolean hasFocus(){
		return this.hasFocus;
	}

}
