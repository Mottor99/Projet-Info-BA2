package Model;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private String label;
	public MenuItem(String label){
		this.label = label;
	}
	public void onClick(MenuActivable a){
		a.menuAction(this.label);
	}
	public String getLabel(){
		return this.label;
	}
}
