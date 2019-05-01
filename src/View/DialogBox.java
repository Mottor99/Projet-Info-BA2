package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Dialog;
import Model.NPC;

public class DialogBox extends JPanel{
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
	public void render(Dialog object){
		if(object!=null){
			this.object = object;
			this.label.setText(object.getCurrentSentence());
			this.setVisible(true);
			this.hasFocus = true;
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
