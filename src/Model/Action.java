package Model;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Action implements Runnable{
	protected Game g;
	protected NPC p;
	protected volatile boolean running = false;
	protected Thread t;

	public Action(NPC p, Game g) {
		this.p = p;
		this.g = g;
		running = true;
		this.t = new Thread(this);
		this.t.start();
	}

	
	public void stop(){
		running = false;
		p.setCurrentAction(null);
	}
	public boolean isRunning(){
		return running;
	}
	

}
