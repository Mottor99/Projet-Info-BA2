package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	private MenuItem selectedItem;
	private int selection = 0;
	private MenuActivable activable;
	
	public Menu(MenuActivable activable){
		this.activable = activable;
	}
	
	public void nextItem(){
		if(selection<items.size()-1){
			selection++;
			selectItem(selection);	
		}
	}
	public void previousItem(){
		if(selection>0){
			selection--;
			selectItem(selection);	
		}
	}
	
	private void selectItem(int index){
		this.selectedItem = this.items.get(index);
	}
	
	public void addItem(MenuItem item){
		this.items.add(item);
		selectItem(selection);
	}
	
	public void click(){
		this.selectedItem.onClick(this.activable);
	}
	
	
	public MenuItem getSelectedItem(){
		return selectedItem;
	}
	public ArrayList<String> getItemsByLabel(){
		return format();
	}
	public ArrayList<MenuItem> getItems(){
		return this.items;
	}
	
	public ArrayList<String> format(){
		ArrayList<String> itemNames = new ArrayList<String>();
		for(MenuItem item : this.items){
			itemNames.add(item.getLabel());
			if(item == this.selectedItem){
				itemNames.set(selection, "> "+this.selectedItem.getLabel());
			}
		}
		return itemNames;
		
	}
}
