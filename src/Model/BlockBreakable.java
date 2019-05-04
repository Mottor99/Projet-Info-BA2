package Model;

import java.util.ArrayList;

public class BlockBreakable extends Block implements Deletable, Activable {

    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private int lifepoints = 0;
    public BlockBreakable(int X, int Y, int width, int height, int lifepoints) {
        super(X, Y, width, height);
        this.lifepoints = lifepoints;
        sprite = Sprite.brick;
    }
    
    
    /*
	public void activate(){
        if (lifepoints == 1){
            crush();
        }
        else {
            lifepoints--;
           
        }
    }*/


    public void crush(){
        notifyDeletableObserver();
    }
    // //////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachDeletable(DeletableObserver po) {
        observers.add(po);
    }

    @Override
    public void notifyDeletableObserver() {
        //int i = 0;
        for (DeletableObserver o : observers) {
            //i++;
            o.delete(this, null);
        }
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

	@Override
	public void activate(Player p) {
		// TODO Auto-generated method stub
		
	}

	

}
