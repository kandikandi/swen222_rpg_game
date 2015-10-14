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

import control.ClientControl;
import model.Player;


/**
 * The InventoryPanel displays the players Inventory by creating and displaying
 * ItemLabels which display the equivalent pictures to the players currently held items.
 * It then fills the remaining inventory slots with EmptyLabels.
 * @author newtondavi2 (David)
 *
 */
public class InventoryPanel extends JPanel {

	private final int maxItemSlots = 9; // The maximum amount of slots avaliable to the player
	private InfoPanel inspectItem;
	private char itemChar;
	private Image itemImage;
	private Image backgroundImage;
	private ArrayList<JLabel> items = new ArrayList<JLabel>(); // The Inventory is made up of ItemLabels and EmptyLabels

	public InventoryPanel(InfoPanel inspect){

		this.inspectItem = inspect;
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
	 * @param player
	 * @param clientControl
	 */
	public void update(Player player, ClientControl clientControl){

		// Clear the Inventory every time it is updated
			items.clear();
			this.removeAll();
			this.revalidate();
			int itemAmount = player.getInventory().returnContents().size();

			// Put the inventory items into the inventory
			for(int i = 0; i < player.getInventory().returnContents().size(); i++){

					ItemLabel newItem = new ItemLabel(inspectItem, player.getInventory().returnContents().get(i).getAsciiCode(), player, clientControl);
					//newItem.setItemLabelID(player.getInventory().returnContents().get(i).getID());

					itemChar = player.getInventory().returnContents().get(i).getAsciiCode();
					itemImage = findImage(itemChar);
					Image scaledImage = itemImage.getScaledInstance(60, 60,
							Image.SCALE_SMOOTH);

					newItem.setIcon(new ImageIcon(scaledImage));
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

	}

	public Image findImage(char c){

		Image image = null;

		switch(c){

		case 'C':
			try {
				image = ImageIO.read(new File("Inventory_Coin.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 'B':
			try {
				image = ImageIO.read(new File("Inventory_Bag.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 'K':
			try {
				image = ImageIO.read(new File("Inventory_Key.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 'X':
		try {
			image = ImageIO.read(new File("SKey_Inventory.png")); ///////
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;

		case 'Z':
		try {
			image = ImageIO.read(new File("Inventory_Candy.png")); ///////
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;

		case 'Q':
		try {
			image = ImageIO.read(new File("Inventory_Treasure.png")); ///////
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		}

		return image;

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