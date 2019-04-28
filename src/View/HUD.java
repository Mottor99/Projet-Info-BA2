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
	protected static Screen screen;
	protected static Window window;
	private static JPanel shopPanel = new JPanel();
	private boolean buttonPressed = false;

    protected static JButton button = new JButton("Shop");

    public HUD(Screen m, Window w){
    	screen = m;
    	window = w;
    	
        
        shopPanel.setFocusable(false);;
		shopPanel.setOpaque(true);
		shopPanel.setVisible(false);
		shopPanel.setBackground(Color.RED);
		shopPanel.setPreferredSize(new Dimension(window.getWidth()/5,window.getHeight()));
		
		
        button.addActionListener(new ActionListener() {

        	
			@Override
			public void actionPerformed(ActionEvent e) {
				shopPanel.setVisible(!buttonPressed);
				
				buttonPressed = !buttonPressed;
				System.out.println("Test");
			}
        	
        });
        
        //screen.add(button, BorderLayout.NORTH);
        screen.add(shopPanel, BorderLayout.EAST);
        
    }
    
    
	public static void render(Graphics g) {
		
		
		// bars 
        // Energy 
		g.setColor(Color.RED);
        g.fillRect(30, 30, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        int length_ok = (int) Math.round(BAR_LENGTH*p.getEnergy());
        g.fillRect(30, 30, length_ok, BAR_WIDTH);
        g.setColor(Color.GRAY);
        g.drawRect(30, 30, BAR_LENGTH, BAR_WIDTH);
        
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.setColor(Color.GRAY);
        g.drawString("Energy", 30, 25); 
        g.drawString("Day : " + String.valueOf(Time.getDay()) + " " + String.valueOf(Time.getHour()) + ":" + String.valueOf(Time.getMin()), 300, 25);
        button.repaint();
        shopPanel.repaint();
    }


	public static void setPlayer(Player p2) {
		p = p2;
	}
	public static void setNPC(NPC npc2) {
		npc = npc2;
	}
	
}
