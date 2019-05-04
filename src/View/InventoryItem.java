package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Model.Deletable;
import Model.DeletableObserver;
import Model.Draggable;
import Model.DraggableObserver;
import Model.GameObject;

public class InventoryItem extends JButton implements Deletable, Draggable {
	GameObject selectedObject;
	DraggableObserver draO;
	DeletableObserver delO;
	public InventoryItem(GameObject o) {
		this.selectedObject = o;
		this.setIcon(new ImageIcon(o.getSprite().getPath()));
		this.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				//screen.setDraggedObject(selectedObject);
				notifyDraggableObserver();
				
			}
			
		});
	}
	public GameObject getObject() {
		return this.selectedObject;
	}
	@Override
	public void attachDeletable(DeletableObserver po) {
		this.delO = po;
		
	}
	@Override
	public void notifyDeletableObserver() {
		delO.delete(this, null);
	}
	@Override
	public void attachDraggable(DraggableObserver d) {
		this.draO = d;
		
	}
	@Override
	public void notifyDraggableObserver() {
		draO.setDraggedItem(this);
		
	}
	
	
}
