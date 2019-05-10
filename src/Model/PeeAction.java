package Model;

import java.util.concurrent.CopyOnWriteArrayList;

public class PeeAction extends Action implements Runnable{
	
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
		System.out.println(target.getPosX());
		while(direction != -1 && running) {
			direction = (new AStar(p.getAX(), p.getAY(), target.getPosX(), target.getPosY()-1, copy)).getNextStep();
				
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
		if(p.getPosX()==target.getPosX()&&p.getPosY()==target.getPosY()-1) {
			p.pee();
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
