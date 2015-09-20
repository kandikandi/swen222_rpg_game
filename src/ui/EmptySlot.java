package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class EmptySlot extends JLabel {

	BufferedImage emptySlot;
	private int index;
	//private Item item;

	public EmptySlot(){
		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon());
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
		this.setBorder(border);
	}

//	public void setItem(Item item){
//
//	}
//
//	public Item getItem(){
//
//	}

}