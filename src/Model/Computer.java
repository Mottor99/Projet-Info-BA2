package Model;

public class Computer extends BlockUnbreakable implements Activable, Deletable, MenuActivable {

	Player player;
	
	protected Menu startWorkingMenu;
	protected Menu stopWorkingMenu;
	protected Menu currentMenu;
	protected boolean isInMenu = false;


	public Computer(int x, int y) {
		super(x, y, 1, 1);
		sprite = Sprite.computer;
		this.currentMenu = new Menu(this);
		this.startWorkingMenu = new Menu(this);
		this.stopWorkingMenu = new Menu(this);
		this.startWorkingMenu.addItem(new MenuItem("work"));
		this.startWorkingMenu.addItem(new MenuItem("cancel"));
		this.stopWorkingMenu.addItem(new MenuItem("continue working"));
		this.stopWorkingMenu.addItem(new MenuItem("stop working"));
		
		
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		
		
	}

	@Override
	public void notifyDeletableObserver() {
	
		
	}

	@Override
	public void activate(Player p) {
		
		this.player = p;
		if (p.isWorking) {
			this.currentMenu = stopWorkingMenu;
		}
		else {
			this.currentMenu = startWorkingMenu;
		}
		openMenu();
		
	}
	
	public void work() {
		player.isWorking();
		
	}
	public void stopWorking() {
		player.stopWorking();
		
	}
	@Override
	public void openMenu() {
		this.isInMenu = true;
		
	}

	@Override
	public void closeMenu() {
		this.isInMenu = false;
		
		
	}

	@Override
	public void menuAction(String action) {
		switch(action){
		case "work": 
			closeMenu();
			work();
			break;
		
		case "cancel":
			closeMenu();
			break;
		case "stop working":
			closeMenu();
			stopWorking();
		
		case "continue working":
			closeMenu();
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

}
