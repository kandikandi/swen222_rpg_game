package ui;

import javax.swing.*;

import Control.GameServer;
import Model.Actor;
import Model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The GameFrame holds the pieces which make up the GUI for Bedtime Story.
 * This includes:
 * InventoryPanel, GoldPanel, PlayerStatsPanel, JMenuBar and the GameCanvas.
 * It holds the method calls to update all of the above.
 * @author newtondavi2 (David)
 *
 */
public class GameFrame extends JFrame {

	private final int WIDTH;
	private final int HEIGHT;
	private JMenuBar menu =  new JMenuBar();
	private JMenu file = new JMenu("File");
	private JPanel sidePanel = new JPanel();
	private PlayerStatsPanel playerStats;
	private GoldPanel goldPanel;
	private InventoryPanel inventory;
	private GameServer socketServer; //TODO: Bonnie added this!

	public static boolean displayPlayersCoinBag; // ------------ROUGH (REMOVE THIS IN FUTURE DONT USE STATIC)

	// TODO: Bonnie added extra argument here!
	public GameFrame(String title, int WIDTH, int HEIGHT, GameServer socketServer) {
		super(title);

		this.socketServer = socketServer;

		this.setLayout(new BorderLayout());
		this.playerStats = new PlayerStatsPanel();
		this.goldPanel = new GoldPanel();
		this.inventory = new InventoryPanel();
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.setVisible(true);
		this.getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CHANGE TO DO_NOTHING_ON_CLOSE
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

		// Set up the sidePanel to hold the Players Stats (PlayerStatsPanel), Coin Bag (GoldPanel) and Inventory (InventoryPanel)
		this.sidePanel.setLayout(new BorderLayout());
		this.sidePanel.add(goldPanel, BorderLayout.CENTER);
		this.sidePanel.add(playerStats, BorderLayout.NORTH);
		this.sidePanel.add(inventory, BorderLayout.SOUTH);

		// Set the JMenuBar and SidePanel
		super.setJMenuBar(menu);
		super.add(sidePanel, BorderLayout.EAST); // add the sidePanel panel to this frame in the EAST position
		super.pack(); // pack components tightly together

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

				//=================================================//
				//TODO: Bonnie here adding add some lines for save!
				socketServer.save();
				//TODO: Bonnie ends here!
				//=================================================//
			}
		});

		//===================================================//
		// TODO: Bonnie added this!
		JMenuItem loadG = new JMenuItem("Load Game");
		loadG.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				socketServer.load();
			}

		});
		//=================================================//
		file.add(newG);
		file.add(saveG);
		file.add(loadG);
	}

	/**
	 * Updates the GUI
	 * This means that the 3 following things are updated
	 * Players PlayerStatsPanel
	 * Players GoldPanel
	 * Players InventoryPanel
	 * @param player
	 */
	public void updateGUI(Player player){
//		this.updatePlayerInventory(player.getInventory().returnContents());
//		this.updatePlayerFear(player.getFear());
		this.updatePlayerGoldPanel();
	}


	private void updatePlayerGoldPanel() {

		if(displayPlayersCoinBag){
			this.goldPanel.displayPlayersCoinBag();
		} else {
			this.goldPanel.hidePlayersCoinBag();
		}

	}

	/**
	 * Updates the players Inventory display via the InventoryPanel
	 * @param inventory
	 */
	public void updatePlayerInventory(ArrayList<Actor> inventory){
		//this.inventory.update(inventory);
	}

	/**
	 * Updates the players FearBar display via the PlayerStatsPanel
	 * @param fear
	 */
	public void updatePlayerFear(int fear){
		this.playerStats.getFearBar().setCurrentFear(fear);
	}

	/**
	 * Returns the GameFrame currently being used for the game
	 * @return
	 */
	public GameFrame getGameFrame(){
		return this;
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

}
