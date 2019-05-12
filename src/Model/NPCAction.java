package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class NPCAction implements Runnable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Game g;
	protected NPC p;
	protected transient volatile boolean running = false;
	protected transient Thread t;

	public NPCAction(NPC p, Game g) {
		this.p = p;
		this.g = g;
		running = true;
		this.t = new Thread(this);
		this.t.start();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	      in.defaultReadObject();
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
