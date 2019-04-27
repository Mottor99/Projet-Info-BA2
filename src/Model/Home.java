package Model;

public class Home extends Level {

	public Home(Game game) {
		super(game, "src/home.txt");
    	try {
			load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
