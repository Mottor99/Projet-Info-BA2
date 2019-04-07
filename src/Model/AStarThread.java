package Model; 
//1 ligne
//2 changement
//troisieme ligne ++
public class AStarThread implements Runnable{
	private Game g;
	private Player p;
	private int x;
	private int y;
	private volatile boolean running = false;
	private Thread t;
//quatrieme ligneeeeeeeeeeeeeeeeeeeeeeeee luderic est un gros kik
	public AStarThread(Game g, Player p, int x, int y) {
		this.g= g;
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
		synchronized(p) {
		while(direction != -1 && running) {
			direction = (new AStar(p.getPosX(), p.getPosY(), x, y, g.getGameObjects())).getNextStep();
			switch (direction) {
				case 0 : g.movePlayer(1,0); break;
				case 1 : g.movePlayer(0,-1); break;
				case 2 : g.movePlayer(-1,0); break;
				case 3 : g.movePlayer(0,1); break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
	public void stop(){
		running = false;
	}
	public boolean isRunning(){
		return running;
	}
		

}
