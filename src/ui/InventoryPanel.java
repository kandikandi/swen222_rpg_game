package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Model.Actor;




/**
 * The InventoryPanel displays the players Inventory by creating and displaying
 * ItemLabels which display the equivalent pictures to the players currently held items.
 * It then fills the remaining inventory slots with EmptyLabels.
 * @author newtondavi2 (David)
 *
 */
public class InventoryPanel extends JPanel {

	private Image itemImage;
	private Image backgroundImage;
	private final int maxItemSlots = 9; // The maximum amount of slots avaliable to the player
	private ArrayList<JLabel> items = new ArrayList<JLabel>(); // The Inventory is made up of ItemLabels and EmptyLabels

	public InventoryPanel(){

		this.setPreferredSize(new Dimension(200,200));
		this.setLayout(new GridLayout(3,3));
		this.setBackground(new Color(204, 255, 255));
		this.createBorder();


		try {
			// load the imageName
			backgroundImage = ImageIO.read(new File("Inventory_BG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the players inventory by clearing it and then refilling it by creating
	 * the equivalent ItemLabels
	 * @param inventory
	 */
	public void update(ArrayList<Actor> inventory){

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
				if(newItem.getItemLabelID().getID() == 6){
					try {
						// load the imageName
						itemImage = ImageIO.read(new File("Inventory_Key.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					newItem.setIcon(new ImageIcon(itemImage));
				}
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

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(backgroundImage!= null){
			g.drawImage(backgroundImage, 0, 0, null);
		}

	}

	private void createBorder() {
		//Compound borders
		Border compound;

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		//This creates a nice frame.
		compound = BorderFactory.createCompoundBorder(
		                          raisedbevel, loweredbevel);

		this.setBorder(compound);

	}

}