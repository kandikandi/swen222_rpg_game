package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The GoldPanel will display the currently selected Coin Bags gold value.
 * @author newtondavi2 (David)
 *
 */
public class GoldPanel extends JPanel {

	private Image backgroundImage;

	public GoldPanel() {
		this.setPreferredSize(new Dimension(200,100));
		this.setLayout(new GridLayout(1,3));
		this.setBackground(new Color(204, 255, 255));
		this.createBorder();

		try {
			// load the image
			backgroundImage = ImageIO.read(new File("GoldPanel_BG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Currently displays the gold panel (not the actual gold count and label ------ incomplete)
	public void displayPlayersCoinBag(){
		//this.setBackground(new Color(240,230,210));
		//this.revalidate();
	}

	//Hides the gold panel (not the actual gold count and labe ----- incomplete)
	public void hidePlayersCoinBag(){
		//this.setBackground(new Color(10,10,10));
		//this.revalidate();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(backgroundImage!= null){
			g.drawImage(backgroundImage, 0, 0, null);
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

}