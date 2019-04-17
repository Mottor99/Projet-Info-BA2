package View;



public interface Animation extends Runnable{
	static final int IDLE = 0;
	static final int MOVING = 1;
	public void animate();
	
}
