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
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

import control.GameClient;
import control.PacketDropItem;
import model.Player;
import view.ID;



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
	private char asciiCode;
	private InfoPanel inspectPanel;
	private Player player;
	private GameClient gameClient;

	/*
	 * The constructor will take in an items ID so that it can create its inventory imageName for the ItemLabels ImageIcon.
	 */
	public ItemLabel(InfoPanel inspectItem, char c, Player player, GameClient gameClient){

		this.inspectPanel = inspectItem;
		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon()); // ------------- currently not set to items imageName
		this.asciiCode = c;
		this.player = player;
		this.gameClient = gameClient;

		/*
		 * Set up the JPopupMenu.
		 * This menu only displays when a ItemLabel is right clicked
		 */
		this.addMouseListener(new PopupTriggerListener());

		JMenuItem inspect = new JMenuItem("Inspect Item");
		inspect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(getAscii());
				System.out.println("Item Inspected");
				if(getLabel().getAscii() == 'K'){
					inspectPanel.getInspect();
				}


			}
		});
		itemMenu.add(inspect);

		JMenuItem use = new JMenuItem("Use Item");
		use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=============USE PACKET CODE HERE===========

				//
				//


			}
		});
		itemMenu.add(use);

		JMenuItem drop = new JMenuItem("Drop Item");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=============DROP PACKET CODE HERE===========
				String data = "";
				if(getLabel().getAscii() == 'K'){
					System.out.println("Here");
					data = "5"+player.getClientNum()+"K";
				}
				if(getLabel().getAscii() == 'B'){
					data = "5"+player.getClientNum()+"B";
				}
				if(getLabel().getAscii() == 'C'){
					data = "5"+player.getClientNum()+"C";
				}


				PacketDropItem p = new PacketDropItem(data.getBytes());
				p.writeData(gameClient);






				//	player.dropItemID(itemID);
				//	getLabel().setVisible(false);

			}
		});
		itemMenu.add(drop);


	}

	// rough set of this item id
//	public void setItemLabelID(ID id){
//		this.itemID = id;
//
//	}

	//rough get of this item label
	public char getAscii(){
		return this.asciiCode;
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