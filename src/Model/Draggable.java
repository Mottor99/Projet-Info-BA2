package Model;

public interface Draggable {
	void attachDraggable(DraggableObserver d);
	void notifyDraggableObserver();
}
