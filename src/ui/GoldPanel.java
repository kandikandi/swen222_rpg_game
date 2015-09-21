package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The GoldPanel will display the currently selected Coin Bags gold value.
 * @author newtondavi2 (David)
 *
 */
public class GoldPanel extends JPanel {
	Border border = BorderFactory.createLineBorder(Color.YELLOW, 1);

	public GoldPanel() {
		this.setPreferredSize(new Dimension(200,100));
		this.setBackground(new Color(10,10,10));
		this.setLayout(new GridLayout(1,3));
		this.setBorder(border);
	}

	//Currently displays the gold panel (not the actual gold count and label ------ incomplete)
	public void displayPlayersCoinBag(){
		this.setBackground(new Color(240,230,210));
		//this.revalidate();
	}

	//Hides the gold panel (not the actual gold count and labe ----- incomplete)
	public void hidePlayersCoinBag(){
		this.setBackground(new Color(10,10,10));
		//this.revalidate();
	}

}