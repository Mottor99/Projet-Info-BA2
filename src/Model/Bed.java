package Model;

public class Bed extends BlockUnbreakable implements Activable, Deletable, MenuActivable {
	
	

	protected Menu Menu;
	protected boolean isInMenu = false;
	private Game g;
	private Entity p;

	public Bed(int x, int y, Game g) {
		super(x, y, 2, 2);
		sprite = Sprite.bed;
		this.g = g;
		this.Menu = new Menu(this);
		this.Menu.addItem(new MenuItem("continue sleeping"));
		this.Menu.addItem(new MenuItem("stop sleeping"));
		
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyDeletableObserver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(Entity p) {
		this.p = p;
		//active_player.setEnergy(100);
		p.setPosX(this.getPosX());
		p.setPosY(this.getPosY()+1);
		p.sleep();
		g.timeAccelerates();
		openMenu();

	}

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return true;
	}
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
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
		case "continue sleeping": 
			closeMenu();
			break;
		
		case "stop sleeping":
			closeMenu();
			stopSleeping();
			break;
		}	
			
		
	}

	private void stopSleeping() {
		p.setPosX(this.getPosX());
		p.setPosY(this.getPosY()-1);
		g.timeDecelerates();
		p.stopSleeping();
		
	}

	@Override
	public Menu getMenu() {
		return this.Menu;
	}

	@Override
	public boolean isInMenu() {
		return this.isInMenu;
	}

	


}
