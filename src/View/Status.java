/*
 package View;
 


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.NPC;
import Model.Player;

public class Status extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private NPC p;
	private int BAR_LENGTH = 60;
	private int BAR_WIDTH = 20;

    public Status() {
        this.setPreferredSize(new Dimension(450, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
    }
    
    
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	if(p!=null){
			g.setColor(Color.RED);
	        g.fillRect(30, 30, BAR_LENGTH, BAR_WIDTH);
	        g.fillRect(30, 105, BAR_LENGTH, BAR_WIDTH);
	        g.fillRect(30, 180, BAR_LENGTH, BAR_WIDTH);
	        g.fillRect(30, 255, BAR_LENGTH, BAR_WIDTH);
	        g.setColor(Color.GREEN);
	        int length_ok = (int) Math.round(BAR_LENGTH*p.getEnergy());
	        int length_ok2 = (int) Math.round(BAR_LENGTH*p.getHunger());
	        int length_ok3 = (int) Math.round(BAR_LENGTH*p.getBladder());
	        int length_ok4 = (int) Math.round(BAR_LENGTH*p.getHygiene());
	        g.fillRect(30, 30, length_ok, BAR_WIDTH);
	        g.fillRect(30, 105, length_ok2, BAR_WIDTH);
	        g.fillRect(30, 180, length_ok3, BAR_WIDTH);
	        g.fillRect(30, 255, length_ok4, BAR_WIDTH);
	        g.setColor(Color.GRAY);
	        g.drawRect(30, 30, BAR_LENGTH, BAR_WIDTH);
	        g.drawRect(30, 105, BAR_LENGTH, BAR_WIDTH);
	        g.drawRect(30, 180, BAR_LENGTH, BAR_WIDTH);
	        g.drawRect(30, 255, BAR_LENGTH, BAR_WIDTH);
	        
	        g.setFont(new Font("Helvetica", Font.BOLD, 20));
	        g.setColor(Color.WHITE);
	        g.drawString("Energy", 30, 25); 
	        g.drawString("Hunger", 30, 100);
	        g.drawString("Bladder", 30, 175);
	        g.drawString("Hygiene", 30, 250);
	        
		}
    }
    

	public void open(NPC npc) {
		this.p = npc;
		System.out.println("[Status] "+ p);
		this.setVisible(true);
	}
}
*/
