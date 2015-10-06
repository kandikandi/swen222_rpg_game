package ui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.ID;


/**
 * Empty Labels fill up the remaining spots in the players inventory
 * @author newtondavi2 (David)
 *
 */
public class EmptyLabels extends JLabel {

	BufferedImage emptySlot;
	private int index;
	ID id;
	//private Item item;

	public EmptyLabels(){
		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon());
//		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
//		this.setBorder(border);
	}

	//	public void setItem(Item item){
	//
	//	}
	//
	//	public Item getItem(){
	//
	//	}

	public ID getItemLabelID(){
		return this.id;
	}


}