package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private boolean isOpen = false;
	private ArrayList<GameObject> items = new ArrayList<GameObject>();
	public Shop(){
		this.isOpen = false;
	}
	public void open(Player p){
		this.player = p;
		this.isOpen = true;
		System.out.println("[Shop] Shop is open " + isOpen);
	}
	public void close(){
		this.isOpen = false;
		System.out.println("[Shop] Shop is closed " + isOpen);
	}
	public void buyItem(GameObject item){
		
		try{
			player.pay(((Sellable)item).getPrice()); //throws exception if not enough money
			player.addToInventory(item);
			//items.remove(item);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		
		
	}
	public void addItem(GameObject item){
		if(item instanceof Sellable){
			this.items.add(item);
		}else System.out.println("You can't add this item to the shop, it doesn't have a price !");
	}
	public boolean isOpen(){
		return this.isOpen;
	}
	public ArrayList<GameObject> getItems(){
		return this.items;
	}
}
