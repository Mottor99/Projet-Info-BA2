package Model;

import java.util.concurrent.CopyOnWriteArrayList;

public class PeeAction extends NPCAction implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toilet target;
	

	public PeeAction(NPC p, Game g) {
		super(p, g);
		
	}

	@Override
	public void run() {
		int direction = 0;
		CopyOnWriteArrayList<GameObject> copy = new CopyOnWriteArrayList<GameObject>(); 
		copy.addAll(g.getGameObjects());
		for(GameObject object : copy) {
			if (object instanceof Toilet){
				this.target = (Toilet) object;
				break;
			}
		}	
		while(direction != -1 && running) {
			direction = (new AStar(p.getAX(), p.getAY(), target.getPosX(), target.getPosY()+1, copy)).getNextStep();
				
			switch (direction) {
					case 0 : g.moveEntity(1,0, p); break;
					case 1 : g.moveEntity(0,-1, p); break;
					case 2 : g.moveEntity(-1,0, p); break;
					case 3 : g.moveEntity(0,1, p); break;
				}
			try {
					Thread.sleep(5);
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}

		g.moveEntity(0, 0, p);
		if(p.getAX()==target.getPosX()&&p.getAY()==target.getPosY()+1) {
			p.pee();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.moveEntity(1, 0, p);
			try {
				Thread.sleep(110);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.moveEntity(0, 0, p);
		}else stop();
		
		
		
	}
	public void stop(){
		running = false;
		p.setCurrentAction(null);
	}
	public boolean isRunning(){
		return running;
	}
		
	

}
