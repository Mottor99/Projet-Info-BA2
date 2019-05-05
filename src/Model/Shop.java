package Model;

import java.util.ArrayList;

public class Shop {
	private Player player;
	private ArrayList<Sellable> items = new ArrayList<Sellable>();
	public Shop(){
		
	}
	public void open(Player p){
		this.player = p;
	}
	public void buyItem(Sellable item){
		items.remove(item);
		//player.pay(item.getPrice());
		//player.addToInventory(item);
	}
	public void addItem(Sellable item){
		this.items.add(item);
	}
	
}
