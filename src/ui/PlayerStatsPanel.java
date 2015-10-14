package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The PlayerStatsPanel holds the FearBar, BraveryBar and multiple JLabels which display character statistics
 * such as Attack Damage and Level.
 * @author newtondavi2 (David)
 *
 */
public class PlayerStatsPanel extends JPanel {

	private Image backgroundImage;
	private JPanel labelPanel = new JPanel();
	private JPanel barPanel = new JPanel(); // Holds both the FearBar and BraveryBar
	private JPanel playerInformation = new JPanel(); // Holds JLabels showing players stats
	private FearBar fear = new FearBar(0,150);

	//private BraveryBar bravery = new BraveryBar(0,150);

	// JLabels to display players stats (Held in JPanel playerInformation)
	private JLabel attack = new JLabel("Attack:                 ");
	private JLabel gold = new JLabel("Gold:                     ");
	private JLabel userName = new JLabel("Name:                ");

	public PlayerStatsPanel(){
		this.setPreferredSize(new Dimension(200,250));
		this.setBackground(new Color(204, 255, 255));
		this.createBorder();
		playerInformation.setLayout(new BoxLayout(playerInformation, BoxLayout.Y_AXIS));

		// Set up Attack JLabel
		attack.setFont(new Font("dialog",Font.BOLD,18));
		attack.setForeground(Color.BLACK);

		// Set up Gold JLabel
		gold.setFont(new Font("dialog",Font.BOLD,18));
		gold.setForeground(Color.BLACK);

		// Set up Level JLabel
		userName.setFont(new Font("dialog",Font.BOLD,18));
		userName.setForeground(Color.BLACK);

		// Set up Rotation Button



		try {
			// load the imageName
			backgroundImage = ImageIO.read(new File("PlayerStats_BG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set up playerInformation JPanel
//		playerInformation.setOpaque(false);
//		playerInformation.add(attack);
//		playerInformation.add(gold);
//		playerInformation.add(userName);


		labelPanel.setLayout(new BorderLayout());
		labelPanel.setOpaque(false);
		labelPanel.add(attack, BorderLayout.NORTH);
		labelPanel.add(gold, BorderLayout.CENTER);
		labelPanel.add(userName, BorderLayout.SOUTH);


		// Set up barPanel JPanel
		barPanel.setLayout(new BorderLayout());
		barPanel.setOpaque(false);
		barPanel.add(fear, BorderLayout.NORTH);

		//barPanel.add(bravery, BorderLayout.SOUTH);

		// Set up this PlayerStatsPanel JPanel

		this.add(labelPanel);
		this.add(barPanel);

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

	/**
	 * Updates the players three information Jlabels (Attack, Level, Gold)
	 */
//	public void updateStats(){
//		setAttack();
//		setLevel();
//		setGold();
//	}

	public FearBar getFearBar(){
		return fear;
	}

	public void setAttack(){

	}

	public void setLevel(){

	}

	public void setGold(){

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(backgroundImage!= null){
			g.drawImage(backgroundImage, 0, 0, null);
		}

	}
	public void setAttack(int count) {
		this.attack.setText("Attack: " + count            );
		this.revalidate();

	}

	public void setCoins(int count) {
		this.gold.setText("Gold: " + count            );
		this.revalidate();

	}
	public void setUserName(String name) {
		name = name.substring(2,name.length());
		this.userName.setText("Name: " + name    );
		this.revalidate();

	}

}