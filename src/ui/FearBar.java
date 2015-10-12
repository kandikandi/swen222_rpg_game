package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * The FearBar displays the players current fear level. If the current fear level reaches the maximum fear level then the player dies (wakes up from the dream)
 * @author newtondavi2 (David)
 *
 */
public class FearBar extends JLabel {

	private Image fearBarImage;
	private int currentFear;
	private int maxFear;
	String info = "Fear: " + currentFear + "/" + maxFear; // String used to display the fear stats
	private double displayedFear;

	public FearBar(int currentFear, int maxFear){
		setMaximumFear(maxFear);
		setCurrentFear(currentFear);
		this.currentFear = currentFear;
		this.maxFear = maxFear;
		this.setPreferredSize(new Dimension(180,50));
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
		this.setBackground(new Color(204, 255, 255));
		this.createBorder();

		try {
			// load the imageName
			fearBarImage = ImageIO.read(new File("Fear_Bar.png"));
		} catch (IOException e) {
			e.printStackTrace();
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

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		this.displayedFear = ((double)this.currentFear/(double)this.maxFear) * (double)getWidth(); // Keeps the ratio correct to display on (180,50)
		g.setFont(new Font("dialog",Font.BOLD,16));
		int stringLength = (int)g.getFontMetrics().getStringBounds(info, g).getWidth();
		int stringHeight = (int)g.getFontMetrics().getStringBounds(info, g).getHeight();
		int xPos = getWidth()/2 - stringLength/2;
		int yPos = (getHeight()/2) + stringHeight/3;

		g.drawImage(fearBarImage, 0, 0, (int) displayedFear, getHeight(), null);
		g.setColor(Color.WHITE);
		g.drawString(info, xPos, yPos);

	}

	/**
	 * Sets the players current fear level (david)
	 * @param currentFear
	 */
	public void setCurrentFear(int currentFear){
		this.currentFear = currentFear;
		info = "Fear: " + currentFear + "/" + maxFear;
		repaint();
	}

	/**
	 * Sets the plays maximum amount of fear (david)
	 * @param maxFear
	 */
	public void setMaximumFear(int maxFear){
		this.maxFear = maxFear;
		info = "Fear: " + currentFear + "/" + maxFear;
	}
}