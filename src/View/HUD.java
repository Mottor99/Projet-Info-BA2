package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Model.NPC;
import Model.Player;
import Model.Time;

public class HUD {
	private static Player p;
	private static NPC npc;
	private static int BAR_LENGTH = 120;
	private static int BAR_WIDTH = 40;
	private Screen screen;
	private Window window;
	private JPanel shopPanel = new JPanel();
	private boolean buttonPressed = false;

    private JButton button; 

    public HUD(Screen m, Window w){
    	screen = m;
    	window = w;
    	
        /*
        shopPanel.setFocusable(false);;
		shopPanel.setOpaque(true);
		shopPanel.setVisible(false);
		shopPanel.setBackground(new Color(255, 0 , 0, 170));
		shopPanel.setPreferredSize(new Dimension(window.getWidth()/5,window.getHeight()));
		
        //button.setForeground(Color.DARK_GRAY);
		button = new JButton("Shop");
        button.setFocusable(false);
        
        button.setBackground(Color.GRAY);
        button.setVisible(true);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200,200));
        button.addActionListener(new ActionListener() {

        	
			@Override
			public void actionPerformed(ActionEvent e) {
				shopPanel.setVisible(!buttonPressed);
				
				buttonPressed = !buttonPressed;
				System.out.println("Test");
			}
        	
        });
        
        //screen.add(button, BorderLayout.NORTH);
        //System.out.println("Button added");
        //screen.add(shopPanel, BorderLayout.EAST);
        //window.setVisible(true);
        */
    }
    
    
	public void render(Graphics g) {
		
		
		// bars 
        // Energy 
		
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
        g.drawString("Day " + Time.getDay() + " - " + Time.getHour() + ":" + Time.getMin(), 300, 25);
        
        //this.button.repaint();
        //this.shopPanel.repaint();
        //shopPanel.repaint();
        //window.setVisible(true);
    }


	public static void setPlayer(Player p2) {
		p = p2;
	}
	public static void setNPC(NPC npc2) {
		npc = npc2;
	}
	
}
