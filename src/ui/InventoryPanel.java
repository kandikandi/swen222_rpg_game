package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InventoryPanel extends JPanel {

	private final int maxItemSlots = 9;
	private ArrayList<JLabel> items = new ArrayList<JLabel>();

	public InventoryPanel(){
		this.update();
		this.setPreferredSize(new Dimension(200,200));
		this.setLayout(new GridLayout(3,3));
		this.setBackground(new Color(10,10,10));
	}

	public void update(){
		// Takes the inventory array and creates
		// a JLabel for each item type.

		// for int i < 0; i < items.length; i++){
		//	if(i<inventory.size()){
		//		items[i].setItem(inventory.get(i));
		//	} else {
		//		items[i].setItem(null);
		//	}
	 //	}

		for(int i = 0; i < maxItemSlots; i++){
			items.add(new ItemLabel());
		}

		for(int i = 0; i < maxItemSlots; i++){
			this.add(items.get(i));
			System.out.println("Item set");
		}

	}
}