package Model;


public class BlockUnbreakable extends Block {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlockUnbreakable(int X, int Y, int width, int height) {
        super(X, Y, width, height);
        
    }
    
    @Override
    public boolean isObstacle() {
        return true;
    }
	
}
