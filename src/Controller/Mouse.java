package Controller;

import Model.Game;

public class Mouse {
    private Game game;

    public Mouse(Game game) {
        this.game = game;
    }

	public void mapEvent(int x, int y) {
		synchronized(game) {
			game.sendPlayer(x, y);
		}
	}
	public void inventory(int x, int y) {
		synchronized(game) {
			game.inventory(x, y);
		}
	}

	/*public void placeObject(GameObject o) {
		game.placeObject(GameObject o);
		
	}*/
}
