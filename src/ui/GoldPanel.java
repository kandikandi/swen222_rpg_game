package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GoldPanel extends JPanel {
	Border border = BorderFactory.createLineBorder(Color.RED, 1);

	public GoldPanel() {
		this.setPreferredSize(new Dimension(200,100));
		this.setBackground(new Color(10,10,10));
		this.setLayout(new GridLayout(1,3));
		this.setBorder(border);
	}





}