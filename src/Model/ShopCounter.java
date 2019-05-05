package Model;

public class ShopCounter extends BlockUnbreakable implements Activable, MenuActivable{
	private Menu menu = new Menu(this);
	private boolean isInMenu = false;
	private Shop shop;
	private Player player;
	public ShopCounter(int X, int Y) {
		super(X, Y, 1, 1);
		this.sprite = Sprite.shop_counter;
		this.menu.addItem(new MenuItem("buy"));
		this.menu.addItem(new MenuItem("cancel"));
		this.shop = new Shop();
		shop.addItem(new Bed(0, 0));
		shop.addItem(new Couch(0, 0));
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
		case "buy":
			closeMenu();
			this.shop.open(this.player);
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
	public void activate(Player p) {
		this.player = p;
		openMenu();
		
	}
	public Shop getShop(){
		return this.shop;
	}

}
