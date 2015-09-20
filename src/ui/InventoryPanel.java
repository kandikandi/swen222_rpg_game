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

import model.Collectable;
import model.GameObject;

public class InventoryPanel extends JPanel {


	private final int maxItemSlots = 9;
	private ArrayList<JLabel> items = new ArrayList<JLabel>();

	public InventoryPanel(){
		this.fullInventoryWithEmptySlots();
		this.setPreferredSize(new Dimension(200,200));
		this.setLayout(new GridLayout(3,3));
		this.setBackground(new Color(10,10,10));
	}

	private void fullInventoryWithEmptySlots() {


	}

	public void update(ArrayList<GameObject> inventory){

		for(int i = 0; i < inventory.size(); i++){
			System.out.println("Creating the item labels");
			items.add(new ItemLabel());
		}

		for(int i = 0; i < items.size(); i++){
			this.add(items.get(i));

		}

	}
}