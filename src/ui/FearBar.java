package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * The FearBar displays the players current fear level. If the current fear level reaches the maximum fear level then the player dies (wakes up from the dream)
 * @author newtondavi2 (David)
 *
 */
public class FearBar extends JLabel {

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
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(150,0,255));
		this.displayedFear = ((double)this.currentFear/(double)this.maxFear) * (double)getWidth(); // Keeps the ratio correct to display on (180,50)

		g.fillRect(0, 0, (int)displayedFear, getHeight());
		g.setColor(Color.BLACK);
		g.setFont(new Font("dialog",Font.BOLD,14));
		int stringLength = (int)g.getFontMetrics().getStringBounds(info, g).getWidth();
		int stringHeight = (int)g.getFontMetrics().getStringBounds(info, g).getHeight();
		int xPos = getWidth()/2 - stringLength/2;
		int yPos = (getHeight()/2) + stringHeight/3;
		g.drawString(info, xPos, yPos);

		/*if(displayedFear == 0){
			WAKE UP YOU LOSE
		}*/

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