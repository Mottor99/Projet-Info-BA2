package Model;

import java.awt.Graphics;

public class Computer extends BlockUnbreakable implements Activable, Deletable, MenuActivable, GUIModifier {

	private Player p;
	private GUIObserver go;
	protected Menu startWorkingMenu;
	protected Menu stopWorkingMenu;
	protected Menu currentMenu;
	protected boolean isInMenu = false;


	public Computer(int x, int y, Game g) {
		super(x, y, 1, 1);
		sprite = Sprite.computer;

		attachGUIObserver(g);
		System.out.println(go);
		this.currentMenu = new Menu(this);
		this.startWorkingMenu = new Menu(this);
		this.stopWorkingMenu = new Menu(this);
		this.startWorkingMenu.addItem(new MenuItem("work"));
		this.startWorkingMenu.addItem(new MenuItem("cancel"));
		this.stopWorkingMenu.addItem(new MenuItem("stop working"));
		
		
	}
	@Override
	public void render(double x, double y, Graphics g, int BLOC_SIZE){
    	g.drawImage(sprite.getImage(), (int)Math.ceil(x*BLOC_SIZE),(int)Math.ceil((y-1)*BLOC_SIZE), BLOC_SIZE*width, BLOC_SIZE*2, null);
    }

	@Override
	public void attachDeletable(DeletableObserver po) {
		
		
	}

	@Override
	public void notifyDeletableObserver() {
	
		
	}

	@Override
	public void activate(Entity p) {
		
		this.p = (Player) p;
		currentMenu = startWorkingMenu;
		openMenu();
		
	}
	
	public void work() {
		this.p.startWorking();
		
	}
	public void stopWorking() {
		this.p.stopWorking();
		
	}
	@Override
	public void openMenu() {
		this.isInMenu = true;
		this.notifyGUIObserver();
		
	}

	@Override
	public void closeMenu() {
		this.isInMenu = false;
		this.notifyGUIObserver();
		
		
	}

	@Override
	public void menuAction(String action) {
		switch(action){
		case "work": 
			currentMenu = stopWorkingMenu;
			notifyGUIObserver();
			work();
			break;
		
		case "cancel":
			closeMenu();
			break;
		case "stop working":
			closeMenu();
			stopWorking();
			
		}
		
			
			
		
	}

	@Override
	public Menu getMenu() {
		return this.currentMenu;
		
	}

	@Override
	public boolean isInMenu() {
		
		return this.isInMenu;
	}
	@Override
	public void attachGUIObserver(GUIObserver go){
		this.go = go;
	}
	@Override
	public void notifyGUIObserver(){
		go.notifyGUI(this);
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return isInMenu;
	}

}
