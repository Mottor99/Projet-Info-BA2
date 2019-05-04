package Model;

public class MenuItem {
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
