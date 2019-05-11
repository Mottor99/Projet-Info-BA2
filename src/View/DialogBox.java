package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Dialog;
import Model.NPC;

public class DialogBox extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel();
	private Dialog object = null;
	private boolean hasFocus = false;
	public DialogBox(){
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
		        case KeyEvent.VK_SPACE: 
					if(object != null){
						object.nextSentence();
					}
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
		
		label.setForeground(Color.LIGHT_GRAY);
		
		label.setText("Test");
		this.add(label, BorderLayout.LINE_START);
	}
	public void render(){
		if(object!=null){

			this.label.setText(object.getCurrentSentence());
			this.repaint();
		}
		
		
		
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	public boolean hasFocus(){
		return this.hasFocus;
	}
	public void open(Dialog gm) {
		this.object = gm;
		this.setVisible(true);
		
	}

}
