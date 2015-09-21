package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

import model.ID;

/**
 * The ItemLabel extends JLabel, it is used for each item type received from
 * the players inventory. It sets the items picture and the behavior for each item.
 * @author newtondavi2 (David)
 *
 */
public class ItemLabel extends JLabel {

	private Image itemImage;
	private BufferedImage emptySlot; // The Image for an Empty Slot in the players Inventory (no item)
	private JPopupMenu itemMenu = new JPopupMenu();
	private ID itemID;

	/*
	 * The constructor will take in an items ID so that it can create its inventory image for the ItemLabels ImageIcon.
	 */
	public ItemLabel(){

		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon()); // ------------- currently not set to items image
		this.itemID = itemID;





		/*
		 * Set up the JPopupMenu.
		 * This menu only displays when a ItemLabel is right clicked
		 */
		this.addMouseListener(new PopupTriggerListener());

		JMenuItem inspect = new JMenuItem("Inspect Item");
		inspect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Item Inspected");
				if(getLabel().getItemLabelID().getID() == 4){
					GameFrame.displayPlayersCoinBag = true;

				}


			}
		});
		itemMenu.add(inspect);

		JMenuItem use = new JMenuItem("Use Item");
		use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Item used");


			}
		});
		itemMenu.add(use);

		JMenuItem destroy = new JMenuItem("Destroy Item");
		destroy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Item Destroyed");

			}
		});
		itemMenu.add(destroy);

		JMenuItem hide = new JMenuItem("Hide Coins");
		hide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hide coins");
				if(getLabel().getItemLabelID().getID() == 4){
					GameFrame.displayPlayersCoinBag = false;

				}

			}
		});
		itemMenu.add(hide);


	}

	// rough set of this item id
	public void setItemLabelID(ID id){
		this.itemID = id;

	}

	//rough get of this item label
	public ID getItemLabelID(){
		return this.itemID;
	}

	//rough return of this label
	public ItemLabel getLabel(){
		return this;
	}

	/**
	 * Inner Class which sets up the right click functionality of each ItemLabel
	 * @author newtondavi2
	 *
	 */
	class PopupTriggerListener extends MouseAdapter {

		Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN, 1);

		public void mousePressed(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				itemMenu.show(ev.getComponent(), ev.getX(), ev.getY());
				((ItemLabel) ev.getComponent()).setBorder(selectedBorder); //Highlights item that has been clicked on
			}
		}

		public void mouseReleased(MouseEvent ev) {
			if (ev.isPopupTrigger()) {
				itemMenu.show(ev.getComponent(), ev.getX(), ev.getY());
				System.out.println("Hey");
			}
		}

		public void mouseClicked(MouseEvent ev) {
		}
	}


	//	public void setItem(Item item){
	//
	//	}
	//
	//	public Item getItem(){
	//
	//	}

}