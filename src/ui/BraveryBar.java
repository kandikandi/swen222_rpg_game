/*
 * Bravery Bar for future implementation of Experiance points.
 */




//package ui;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Graphics;
//
//import javax.swing.BorderFactory;
//import javax.swing.JLabel;
//import javax.swing.border.Border;
//
///**
// * The BraveryBar displays the experience bar for the player. When the BraveryBar is full the players level will increase
// * @author newtondavi2 (David)
// *
// */
//public class BraveryBar extends JLabel {
//
//	private int currentBravery;
//	private int requiredBravery;
//	private String braveryStats = "Bravery: " + currentBravery + "/" + requiredBravery; //String used to display the bravery stats
//	private double displayedBravery;
//
//	public BraveryBar(int currentBravery, int requiredBravery){
//		this.setRequiredBravery(requiredBravery);
//		this.setCurrentBravery(currentBravery);
//		this.currentBravery = currentBravery;
//		this.requiredBravery = requiredBravery;
//		this.setPreferredSize(new Dimension(180,50));
//		this.setBackground(new Color(204, 255, 255));
//		this.createBorder();
//	}
//
//	private void createBorder() {
//		//Compound borders
//		Border compound;
//
//		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
//		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
//		//This creates a nice frame.
//		compound = BorderFactory.createCompoundBorder(
//		                          raisedbevel, loweredbevel);
//
//		this.setBorder(compound);
//
//	}
//
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		this.displayedBravery = ((double)this.currentBravery/(double)this.requiredBravery) * (double)getWidth(); // displays the ratio of current to required bravery
//
//		// Used for when we begin leveling up ------------------- incomplete ------------------------------
//		g.setColor(new Color(50,255,50));
//		g.fillRect(0, 0, (int) displayedBravery, getHeight());
//		g.setColor(Color.BLACK);
//		g.setFont(new Font("dialog",Font.BOLD,14));
//		int stringLength = (int)g.getFontMetrics().getStringBounds(braveryStats, g).getWidth();
//		int stringHeight = (int)g.getFontMetrics().getStringBounds(braveryStats, g).getHeight();
//		int xPos = getWidth()/2 - stringLength/2;
//		int yPos = getHeight()/2 + stringHeight/3;
//		g.drawString(braveryStats, xPos, yPos);
//
//	}
//
//	/**
//	 * Sets the players current amount of bravery
//	 * @param bravery
//	 */
//	public void setCurrentBravery(int currentBravery){
//		this.currentBravery = currentBravery;
//		braveryStats = "Bravery: " + currentBravery + "/" + requiredBravery;
//
//	}
//
//	/**
//	 * Sets the required amount of bravery needed for the player to go up a level (david)
//	 * @param requiredBravery
//	 */
//	public void setRequiredBravery(int requiredBravery){
//		this.requiredBravery = requiredBravery;
//		braveryStats = "Bravery: " + currentBravery + "/" + requiredBravery;
//
//	}
//
//
//}