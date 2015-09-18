package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class FearBar extends JLabel {

	private int fear;
	private int totalFear;
	String info = "Fear: " + fear + "/" + totalFear;
	private float displayedFear;

	public FearBar(int fear, int totalFear){
		setTotalFear(totalFear);
		setFear(fear);
		this.fear = fear;
		this.totalFear = totalFear;
		this.setForeground(Color.BLACK); // set font to white
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setPreferredSize(new Dimension(180,40));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(150,0,255));
		this.displayedFear = ((float)this.fear/(float)this.totalFear) * (float)getWidth();

		if(displayedFear != 0){
			g.fillRect(0, 0, (int)displayedFear, getHeight());
			g.setColor(Color.BLACK);
			g.setFont(new Font("dialog",Font.BOLD,14));
			g.drawString(info, 20, 20);
		} else {
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.setFont(new Font("dialog",Font.BOLD,14));
			g.drawString(info, 20, 20);
		}

	}

	public void setFear(int fear){
		this.fear = fear;
		info = "Fear: " + fear + "/" + totalFear;
		//setText("Fear: " + fear + "/" + totalFear);
	}

	public void setTotalFear(int totalFear){
		this.totalFear = totalFear;
		info = "Fear: " + fear + "/" + totalFear;
		//setText("Fear: " + fear + "/" + totalFear);
	}


}