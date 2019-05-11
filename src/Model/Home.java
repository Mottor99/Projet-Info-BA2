package Model;

public class Home extends Level {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Home(Game game) {
		super(game, "src/home");
    	try {
			load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
