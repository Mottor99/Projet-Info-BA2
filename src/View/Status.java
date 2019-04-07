package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.Player;

public class Status extends JPanel {
	private Player p;
	private int BAR_LENGTH = 60;
	private int BAR_WIDTH = 20;
	private int AVATAR_SIZE = 100;

    public Status() {
        //this.setPreferredSize(new Dimension(200, 200));
        this.setOpaque(false);
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		// draw avatar
        //g.setColor(Color.BLUE);
        //g.fillRect(150, 25, AVATAR_SIZE, AVATAR_SIZE);

		// bars 
        // Energy 
        g.setColor(Color.BLACK);
        g.drawString("Energy", 10, 10);
        g.setColor(Color.RED);
        g.fillRect(10, 10, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        int length_ok = (int) Math.round(BAR_LENGTH*p.getEnergy());
        g.fillRect(10, 10, length_ok, BAR_WIDTH);
    }

    public void redraw() {
        this.repaint();
    }

	public void setPlayer(Player p2) {
		this.p = p2;
	}
}
