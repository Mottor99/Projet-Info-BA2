package Model;

public class Loop implements Runnable{
	private Thread t;
	private boolean running = false;
	private Game game;
	
	public Loop(Game game){
		this.game = game;
		t = new Thread(this); //loop tourne en continu, indépendamment du reste
		running = true;
		t.start();
		
	}
	
	
	@Override
	public void run() {
		System.out.println("booted" + running);
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60;
		int frames = 0, updates = 0;
		double delta = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1){ //60fois par sec
				game.updateTime();
				
				updates++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000){ 
				timer+=1000;
				System.out.println(updates+" ups, "+ frames+" fps");
				frames = 0;
				updates = 0;
				
			}

			game.render();//aussi vite que possible
			frames++;
			
			
		}
		
	}
}
