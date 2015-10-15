package ui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Empty Labels fill up the remaining spots in the players inventory
 * @author newtondavi2 (David)
 *
 */
public class EmptyLabels extends JLabel {

	BufferedImage emptySlot;
	private int index;

	public EmptyLabels(){
		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon());
	}

}