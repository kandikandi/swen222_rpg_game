package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BraveryBar extends JLabel {

	private int bravery = 0;
	private int totalBravery = 0;
	String info = "Bravery: " + bravery + "/" + totalBravery;
	float displayedBravery;

	public BraveryBar(int bravery, int totalBravery){
		setTotalBravery(totalBravery);
		setBravery(bravery);
		this.bravery = bravery;
		this.totalBravery = totalBravery;
		this.setForeground(Color.BLACK); // set font to white
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setPreferredSize(new Dimension(180,40));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		this.displayedBravery = ((float)this.bravery/(float)this.totalBravery) * (float)getWidth();

		if(displayedBravery != 0){
			g.setColor(new Color(50,255,50));
			g.fillRect(0, 0, (int) displayedBravery, getHeight());
			g.setColor(Color.BLACK);
			g.setFont(new Font("dialog",Font.BOLD,14));
			g.drawString(info, 20, 20);
		} else {
			displayedBravery = getWidth();
			g.setColor(new Color(50,255,50));
			g.fillRect(0, 0, (int) displayedBravery, getHeight());
			g.setColor(Color.BLACK);
			g.setFont(new Font("dialog",Font.BOLD,14));
			g.drawString(info, 20, 20);
		}

	}

	public void setBravery(int bravery){
		this.bravery = bravery;
		info = "Bravery: " + bravery + "/" + totalBravery;
		//setText("Fear: " + fear + "/" + totalFear);
	}

	public void setTotalBravery(int totalBravery){
		this.totalBravery = totalBravery;
		info = "Bravery: " + bravery + "/" + totalBravery;
		//setText("Fear: " + fear + "/" + totalFear);
	}


}