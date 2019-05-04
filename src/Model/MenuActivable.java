package Model;

public interface MenuActivable {
	void openMenu();
	void closeMenu();
	void menuAction(String action);
	Menu getMenu();
	boolean isInMenu();
}
