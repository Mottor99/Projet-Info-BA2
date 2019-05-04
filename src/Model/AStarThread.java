package Model;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class AStarThread implements Runnable{
	private Game g;
	private Player p;
	private int x;
	private int y;
	private volatile boolean running = false;
	private Thread t;

	public AStarThread(Game g, Player p, int x, int y) {
		this.g = g;
		this.p = p;
		this.x = x;
		this.y = y;
		running = true;
		this.t = new Thread(this);
		this.t.start();
		
	}
	
	@Override
	public void run() {
		
		int direction = 0;
		
		while(direction != -1 && running) {
			CopyOnWriteArrayList<GameObject> copy = new CopyOnWriteArrayList<GameObject>(); 
			copy.addAll(g.getGameObjects());
			direction = (new AStar(p.getAX(), p.getAY(), x, y, copy)).getNextStep();
			
			switch (direction) {
				case 0 : g.movePlayer(1,0); break;
				case 1 : g.movePlayer(0,-1); break;
				case 2 : g.movePlayer(-1,0); break;
				case 3 : g.movePlayer(0,1); break;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g.movePlayer(0, 0);
		
	}
	public void stop(){
		running = false;
	}
	public boolean isRunning(){
		return running;
	}
		

}
