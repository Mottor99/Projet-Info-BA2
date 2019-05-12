package Main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.Keyboard;
import Controller.Mouse;
import Model.Game;
import View.Window;

public class Main {
    private static Game game;
	public static void main(String[] args) throws Exception {
        Window window = new Window("Game");
        game = load(window);
        Keyboard keyboard = new Keyboard(game);
        Mouse mouse = new Mouse(game);
        window.setKeyListener(keyboard);
        window.setMouseListener(mouse);
        
    }
    public static Game load(Window window){
    	
    	
    	
    	try {
    		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game.ser"));
			game = (Game) ois.readObject();
			ois.close();
    		System.out.println("[Main] Loaded game from save file");
    		
    	} catch (ClassNotFoundException e1) {
			System.out.println("[Main] ClassNotFoundException - Game could not load from save file");
		} catch (FileNotFoundException e1) {
			System.out.println("[Main] FileNotFoundException - Game could not load from save file");
		} catch (IOException e) {
			System.out.println("[Main] IOException - Game could not load from save file");
		}
    	if(game==null) {
    		game = new Game(window);
    		System.out.println("[Main] Loaded game for first time");
    	}else game.start(window);
		
    	
    	
    	return game;
    }
    public static void save() {
    	
    	try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.ser"));
			oos.writeObject(game);
			oos.flush();
			oos.close();
			System.out.println("[Main] Game Saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
