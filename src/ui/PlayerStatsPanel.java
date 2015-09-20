package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlayerStatsPanel extends JPanel {

	private JPanel bars = new JPanel();
	private JPanel playerInformation = new JPanel();
	private FearBar fear = new FearBar(0,150);
	private BraveryBar bravery = new BraveryBar(120,150);


	JLabel attack = new JLabel("Attack:                 ");
	JLabel gold = new JLabel("Gold:                     ");
	JLabel level = new JLabel("Level:                   ");



	public PlayerStatsPanel(){
		this.setPreferredSize(new Dimension(200,300));
		//this.setLayout(new FlowLayout());

		playerInformation.setLayout(new BoxLayout(playerInformation, BoxLayout.Y_AXIS));

		attack.setFont(new Font("dialog",Font.BOLD,16));
		attack.setForeground(Color.BLACK);
		playerInformation.add(attack);

		gold.setFont(new Font("dialog",Font.BOLD,16));
		gold.setForeground(Color.BLACK);
		playerInformation.add(gold);

		level.setFont(new Font("dialog",Font.BOLD,16));
		level.setForeground(Color.BLACK);
		playerInformation.add(level);

		bars.setLayout(new BorderLayout());
		bars.add(fear, BorderLayout.NORTH);
		bars.add(bravery, BorderLayout.SOUTH);

		this.add(playerInformation);
		this.add(bars);
		this.setBackground(new Color(10,10,10));


	}

	public void updateStats(){
		// Takes the player stats from
		// passed in variables and updates
		// them
	}

	public FearBar getFearBar(){
		return fear;
	}

	public void setAttack(){

	}

	public void setCourage(){

	}

	public void setName(){

	}

	public void setLevel(){

	}

	public void setGold(){

	}
}