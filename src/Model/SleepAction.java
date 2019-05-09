package Model;

import java.util.concurrent.CopyOnWriteArrayList;

public class SleepAction extends Action implements Runnable{
	private Game g;
	private NPC p;
	private Bed target;
	private volatile boolean running = false;
	private Thread t;

	public SleepAction(NPC p, Game g) {
		this.p = p;
		this.g = g;
		running = true;
		this.t = new Thread(this);
		this.t.start();
	}

	@Override
	public void run() {
		int direction = 0;
		CopyOnWriteArrayList<GameObject> copy = new CopyOnWriteArrayList<GameObject>(); 
		copy.addAll(g.getGameObjects());
		for(GameObject object : copy) {
			if (object instanceof Bed){
				this.target = (Bed) object;
				break;
			}
		}	
		System.out.println(target.getPosX());
		while(direction != -1 && running) {
			direction = (new AStar(p.getAX(), p.getAY(), target.getPosX(), target.getPosY()-1, copy)).getNextStep();
				
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
		target.activate(p);
		
	}

	


	public void stop(){
		running = false;
		p.setCurrentAction(null);
	}
	public boolean isRunning(){
		return running;
	}
		
	

}
