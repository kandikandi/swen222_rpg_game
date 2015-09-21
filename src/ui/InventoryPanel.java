package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.GameObject;

/**
 * The InventoryPanel displays the players Inventory by creating and displaying
 * ItemLabels which display the equivalent pictures to the players currently held items.
 * It then fills the remaining inventory slots with EmptyLabels.
 * @author newtondavi2 (David)
 *
 */
public class InventoryPanel extends JPanel {

	private final int maxItemSlots = 9; // The maximum amount of slots avaliable to the player
	private ArrayList<JLabel> items = new ArrayList<JLabel>(); // The Inventory is made up of ItemLabels and EmptyLabels

	public InventoryPanel(){
		this.setPreferredSize(new Dimension(200,200));
		this.setLayout(new GridLayout(3,3));
		this.setBackground(new Color(10,10,10));
	}

	/**
	 * Updates the players inventory by clearing it and then refilling it by creating
	 * the equivalent ItemLabels
	 * @param inventory
	 */
	public void update(ArrayList<GameObject> inventory){

		// ONLY BEING USED UNTIL WE HAVE SORTED MORE PRECISE UPDATES OF THE GUI
//		Boolean changesMade = false;
//		if(this.items.size() != 0){
//			for(int i = 0; i < items.size(); i++){
//				if(items.get(i) instanceof ItemLabel){
//					if(((ItemLabel) items.get(i)).getItemLabelID() != inventory.get(i).getID()){
//						changesMade = true;;
//					}
//				}
//			}
//		} else {
//			changesMade = true;
//			System.out.println("Setting to true");
//		}
//
//		if(changesMade){
			// Clear the Inventory every time it is updated
			items.clear();
			this.removeAll();
			this.revalidate();

			int itemAmount = inventory.size();

			// Put the inventory items into the inventory
			for(int i = 0; i < inventory.size(); i++){
				ItemLabel newItem = new ItemLabel();
				newItem.setItemLabelID(inventory.get(i).getID()); // TESTING THE SETTING OF ITEM ID ON ITEM LABEL THIS WILL BE USED FOR IMAGE AND BEHAVIOUR
				items.add(newItem);
			}

			// Fill the remaining inventory slots with EmptyLabels
			for(int i = itemAmount; i < 9; i++){
				EmptyLabels empty = new EmptyLabels();
				items.add(empty);
			}

			// Add all the JLabels to the InventoryPanel
			for(int i = 0; i < items.size(); i++){
				this.add(items.get(i));
			}

			// Update the InventoryPanel
			this.revalidate();
//		}
	}

}