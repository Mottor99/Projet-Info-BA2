package Model;

public interface GUIModifier {
	void attachGUIObserver(GUIObserver go);
	void notifyGUIObserver();
	boolean isOpen();
}
