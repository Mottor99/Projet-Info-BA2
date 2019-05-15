package Model;

public class Bed extends BlockUnbreakable implements Activable, Deletable, MenuActivable, GUIModifier {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu Menu;
	private boolean isInMenu = false;
	private transient Game g;
	private Entity e;
	private transient GUIObserver go;

	public Bed(int x, int y) {
		super(x, y, 2, 2);
		sprite = Sprite.bed;
		this.Menu = new Menu(this);
		this.Menu.addItem(new MenuItem("stop sleeping"));
		
	}

	public void attachGame(Game game){
		this.g = game;
	}

	@Override
	public void activate(Entity e) {
		if(e instanceof Player) {
			this.e = e;
			//active_player.setEnergy(100);
			e.setPosX(this.getPosX());
			e.setPosY(this.getPosY()+1);
			e.sleep(); 
			g.timeAccelerates();
			openMenu();
		}else if (e instanceof NPC){
			e.setPosX(this.getPosX()+1);
			e.setPosY(this.getPosY()+1);
			e.sleep(); 
		}

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
		
		
		case "stop sleeping":
			closeMenu();
			stopSleeping();
			break;
		}	
	}

	private void stopSleeping() {
		e.setPosX(this.getPosX());
		e.setPosY(this.getPosY()-1);
		g.timeDecelerates();
		e.stopSleeping();
		
	}

	@Override
	public Menu getMenu() {
		return this.Menu;
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



	@Override
	public void attachDeletable(DeletableObserver po) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void notifyDeletableObserver() {
		// TODO Auto-generated method stub
		
	}

	


}
