package Model;

public class ShopCounter extends BlockUnbreakable implements Activable, MenuActivable, GUIModifier{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu = new Menu(this);
	private boolean isInMenu = false;
	private Shop shop;
	private Player player;
	private GUIObserver go;
	public ShopCounter(int X, int Y, Game game) {
		super(X, Y, 1, 1);
		attachGUIObserver(game);
		this.sprite = Sprite.shop_counter;
		this.menu.addItem(new MenuItem("buy"));
		this.menu.addItem(new MenuItem("cancel"));
		this.shop = new Shop(game);
		shop.addItem(new Table(0, 0));
		shop.addItem(new Couch(0, 0));
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
		case "buy":
			closeMenu();
			
			this.shop.open(this.player);
			System.out.println(shop.getItems());
			this.notifyGUIObserver();
			System.out.println("Screen notified");
			break;
		case "cancel":
			closeMenu();
			break;
		}
		
	}

	@Override
	public Menu getMenu() {
		// TODO Auto-generated method stub
		return this.menu;
	}

	@Override
	public boolean isInMenu() {
		// TODO Auto-generated method stub
		return this.isInMenu;
	}

	@Override
	public void activate(Entity p) {
		this.player = (Player) p;
		openMenu();
		
	}
	public Shop getShop(){
		return this.shop;
	}

	@Override
	public void attachGUIObserver(GUIObserver go) {
		this.go = go;
		
	}

	@Override
	public void notifyGUIObserver() {
		go.notifyGUI(this);
		
	}

	@Override
	public boolean isOpen() {
		boolean open = false;
		if(shop!=null){
			open = shop.isOpen();
		}
		return isInMenu||open;
	}

}
