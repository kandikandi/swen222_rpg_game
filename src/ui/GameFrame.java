package ui;
import control.GameController;

import javax.swing.*;

import model.Collectable;
import model.GameObject;
import model.GameState;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameFrame extends JFrame {

	private final int WIDTH;
	private final int HEIGHT;
	private final GameController controller;
	private InventoryPanel inventory;
	private JMenuBar menu =  new JMenuBar();
	private JMenu file = new JMenu("File");
	private JPanel sidePanel = new JPanel();
	private PlayerStatsPanel playerStats;
	private GoldPanel container;

	public GameFrame(String title, int WIDTH, int HEIGHT, GameController gc) {
		super(title);

		this.setLayout(new BorderLayout());
		this.inventory = new InventoryPanel();
		this.playerStats = new PlayerStatsPanel();
		this.container = new GoldPanel();
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.controller = gc;
		this.setVisible(true);
		this.getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

//		addWindowListener(new WindowAdapter() {
//		    @Override
//		    public void windowClosing(WindowEvent we)
//		    {
//		        String ObjButtons[] = {"Yes","No"};
//		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure!?","",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
//		        if(PromptResult==JOptionPane.YES_OPTION)
//		        {
//		            System.exit(0);
//		        }
//		    }
//		});

		this.setupMenuBar();

		//Set up the sidePanel to hold the Players Stats and Inventory
			this.sidePanel.setLayout(new BorderLayout());
			this.sidePanel.add(container, BorderLayout.CENTER);
			this.sidePanel.add(playerStats, BorderLayout.NORTH);
			this.sidePanel.add(inventory, BorderLayout.SOUTH);

			this.setJMenuBar(menu);
			//super.add(canvas, BorderLayout.CENTER); // add the topPanel panel to this frame in the CENTER position
			super.add(sidePanel, BorderLayout.EAST); // add the sidePanel panel to this frame in the EAST position
			super.pack(); // pack components tightly together

		this.pack();

		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - WIDTH) / 2,
				(scrnsize.height - HEIGHT) / 2, WIDTH, HEIGHT);

	}

	/**
	 * Sets up the about button in the JMenuBar
	 */
	public void setupMenuBar(){
		menu.add(file);
		file.getPopupMenu().setLightWeightPopupEnabled(false);
		JMenuItem newG = new JMenuItem("New Game");
		newG.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
//		    	newGame();
		    }
		});
		JMenuItem saveG = new JMenuItem("Save Game");
		saveG.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
//		    	saveGame();
		    }
		});
		file.add(newG);
		file.add(saveG);
	}

	public void updatePlayerInventory(ArrayList<GameObject> inventory){
		this.inventory.update(inventory);
	}

//	/**
//	 * Creates a New Game of BedTime Story and disposes of the current game
//	 */
//	public void newGame(){
//		BoardFrame f = new BoardFrame();
//		this.dispose();
//	}
//
//	/**
//	 * Saves the current game of BedTime Story
//	 */
//	public void saveGame(){
//		BoardFrame f = new BoardFrame();
//		this.dispose();
//	}

	public void update(Player player){

		this.updatePlayerInventory(player.getInventory().returnContents());
	}

}
