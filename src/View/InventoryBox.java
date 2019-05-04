package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Mouse;
import Model.Camera;
import Model.Deletable;
import Model.DeletableObserver;
import Model.Dialog;
import Model.GameObject;
import Model.Player;

public class InventoryBox extends JPanel implements DeletableObserver{
	private static JPanel panel = new JPanel();
	private boolean hasFocus = false;
	private ArrayList<GameObject> inventory;	
	boolean isOpen = false;
	private boolean drawInventory = false;
	private GameObject selectedObject;
	private int X, Y;
	private ArrayList<InventoryItem>items = new ArrayList<InventoryItem>();
	
	public InventoryBox(){
		//int colonnes = 2;
		//int lignes = 10;
		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(200, 100));
		this.setVisible(false);
		this.setFocusable(true);
		this.setOpaque(true);
		this.setBackground(new Color(10, 20, 0));
		/*JButton buttons[] = new JButton[lignes*colonnes];
		for (int i = 0; i<lignes*colonnes; i++){
			buttons[i] = new JButton();
			buttons[i].setOpaque(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(true);
			buttons[i].setFocusable(false);
			//buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
			//buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
			buttons[i].setPreferredSize(new Dimension(60, 60)); 
			//buttons[i].setIcon(new ImageIcon("src/couch.png"));
			//buttons[i].setText("fauteuil");
			add(buttons[i]);
			buttons[i].addMouseListener(new MouseListener(){


				@Override
				public void mouseClicked(MouseEvent e) {
					
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					int X = e.getX();
					int Y = e.getY();
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

			
				
			});
			
			buttons[i].addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent e) {
					e.getComponent().setLocation((e.getX()+e.getComponent().getX())-25,(e.getY()+e.getComponent().getY())-25);
					
				}

				@Override
				public void mouseMoved(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			} 
		/*this.addMouseListener(new MouseListener(){


			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				int X = e.getX();
				int Y = e.getY();
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				e.getComponent().setLocation((e.getX()+e.getComponent().getX())-X,(e.getY()+e.getComponent().getY()-Y));
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
		
		panel.setForeground(Color.LIGHT_GRAY);
		
		this.add(panel, BorderLayout.LINE_START);
	}
	
	
	public void setInventory(ArrayList<GameObject> inventory, Screen screen) {
		this.items.clear();
		for(GameObject o : inventory){
		    InventoryItem item = new InventoryItem(o);
		    item.attachDeletable(this);
		    item.attachDraggable(screen);
		    this.items.add(item);
		    this.add(item);
		}
	}
	public void switchVisibility() {
		this.isOpen = !this.isOpen;
		this.setVisible(isOpen);
		drawInventory = true;
	}


	public void setPlayer(Player p, Screen screen) {
		this.setInventory(p.getInventory(), screen);
		
	}


	@Override
	public void delete(Deletable d, CopyOnWriteArrayList<GameObject> loot) {
		items.remove(d);
		this.remove((InventoryItem)d); 
		this.repaint();
		
	}
}
