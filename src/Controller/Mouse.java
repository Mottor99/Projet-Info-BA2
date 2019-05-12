package Controller;

import java.io.Serializable;

import Model.Game;
import Model.GameObject;

public class Mouse implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Game game;

    public Mouse(Game game) {
        this.game = game;
    }

	public void mapEvent(int x, int y) {
		synchronized(game) {
			game.sendPlayer(x, y);
		}
	}
	public void inventory(int x, int y) {
		game.inventory(x, y);
		
	}

	public void placeObject(GameObject o) {
		game.placeObject(o);
		
	}
}
