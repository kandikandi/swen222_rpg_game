package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The PlayerStatsPanel holds the FearBar, BraveryBar and multiple JLabels which display character statistics
 * such as Attack Damage and Level.
 * @author newtondavi2 (David)
 *
 */
public class PlayerStatsPanel extends JPanel {

	private JPanel barPanel = new JPanel(); // Holds both the FearBar and BraveryBar
	private JPanel playerInformation = new JPanel(); // Holds JLabels showing players stats
	private FearBar fear = new FearBar(0,150);
	private BraveryBar bravery = new BraveryBar(0,150);

	// JLabels to display players stats (Held in JPanel playerInformation)
	private JLabel attack = new JLabel("Attack:                 ");
	private JLabel gold = new JLabel("Gold:                     ");
	private JLabel level = new JLabel("Level:                   ");


	public PlayerStatsPanel(){
		this.setPreferredSize(new Dimension(200,300));

		playerInformation.setLayout(new BoxLayout(playerInformation, BoxLayout.Y_AXIS));

		// Set up Attack JLabel
		attack.setFont(new Font("dialog",Font.BOLD,16));
		attack.setForeground(Color.BLACK);

		// Set up Gold JLabel
		gold.setFont(new Font("dialog",Font.BOLD,16));
		gold.setForeground(Color.BLACK);

		// Set up Level JLabel
		level.setFont(new Font("dialog",Font.BOLD,16));
		level.setForeground(Color.BLACK);

		// Set up playerInformation JPanel
		playerInformation.add(attack);
		playerInformation.add(gold);
		playerInformation.add(level);

		// Set up barPanel JPanel
		barPanel.setLayout(new BorderLayout());
		barPanel.add(fear, BorderLayout.NORTH);
		barPanel.add(bravery, BorderLayout.SOUTH);

		// Set up this PlayerStatsPanel JPanel
		this.add(playerInformation);
		this.add(barPanel);
		this.setBackground(new Color(10,10,10));


	}

	/**
	 * Updates the players three information Jlabels (Attack, Level, Gold)
	 */
	public void updateStats(){
		setAttack();
		setLevel();
		setGold();
	}

	public FearBar getFearBar(){
		return fear;
	}

	public void setAttack(){

	}

	public void setLevel(){

	}

	public void setGold(){

	}
}