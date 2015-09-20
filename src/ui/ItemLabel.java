package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

public class ItemLabel extends JLabel {

	BufferedImage emptySlot;
	private int index;
	JPopupMenu menu = new JPopupMenu("Popup");
	//private Item item;

	public ItemLabel(){
		this.setPreferredSize(new Dimension(50,50));
		this.setIcon(new ImageIcon());
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		this.setBorder(border);
		this.addMouseListener(new PopupTriggerListener());

		  JMenuItem inspect = new JMenuItem("Inspect Item");
		    inspect.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("Item Inspected");
		      }
		    });
		    menu.add(inspect);

		  JMenuItem use = new JMenuItem("Use Item");
		    use.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("Item used");
		      }
		    });
		    menu.add(use);

		  JMenuItem destroy = new JMenuItem("Destroy Item");
		    destroy.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.out.println("Item Destroyed");
		      }
		    });
		    menu.add(destroy);
		 
	}

	class PopupTriggerListener extends MouseAdapter {
	      public void mousePressed(MouseEvent ev) {
	        if (ev.isPopupTrigger()) {
	          menu.show(ev.getComponent(), ev.getX(), ev.getY());
	        }
	      }

	      public void mouseReleased(MouseEvent ev) {
	        if (ev.isPopupTrigger()) {
	          menu.show(ev.getComponent(), ev.getX(), ev.getY());
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