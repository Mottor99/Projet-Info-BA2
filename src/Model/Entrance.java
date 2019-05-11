package Model;

import java.util.ArrayList;

public class Entrance extends Block implements LevelSwitch {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient ArrayList<LevelSwitchObserver> observers = new ArrayList<LevelSwitchObserver>();
	private String destination;
	public Entrance(int x, int y, String destination) {
		super(x, y, 1, 1);
		this.destination = destination;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
    public int compareTo(GameObject compareObj){
		return -1;
    	
    }
	@Override
	public void attachLevelSwitch(LevelSwitchObserver o) {
		if(observers == null){
			observers = new ArrayList<LevelSwitchObserver>();
		}
		observers.add(o);
	}
	@Override
	public void notifyLevelSwitchObservers() {
		for (LevelSwitchObserver o : observers) {
            o.switchLevel(this, this.destination);
        }
	}
	public String getDestination() {
		return destination;
	}

	

}
