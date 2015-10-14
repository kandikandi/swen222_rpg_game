package ui;

import javax.swing.*;

import control.ClientControl;

import control.PacketDisconnectServer;

import control.MainServer;

import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * The GameFrame holds the pieces which make up the GUI for Bedtime Story.
 * This includes:
 * InventoryPanel, GoldPanel, PlayerStatsPanel, JMenuBar and the GameCanvas.
 * It holds the method calls to update all of the above.
 * @author newtondavi2 (David)
 *
 */
public class GameFrame extends JFrame {

	public static boolean displayPlayersCoinBag; // ------------ROUGH (REMOVE THIS IN FUTURE DONT USE STATIC)
	private final int WIDTH;
	private final int HEIGHT;
	private JMenuBar menu =  new JMenuBar();
	private JMenu file = new JMenu("File"); 
	private JPanel sidePanel = new JPanel();
	private PlayerStatsPanel playerStats;
	private InfoPanel infoPanel;
	private InventoryPanel inventory;
	private ClientControl socketClient; //TODO: Bonnie added this!
	private MainServer server;
	private String name;


	// TODO: Bonnie added extra argument here!
	public GameFrame(String title, int WIDTH, int HEIGHT, MainServer server, String userName) {
		super(title);

		this.name = userName;
		this.server = server;
		this.setLayout(new BorderLayout());
		this.playerStats = new PlayerStatsPanel();
		this.infoPanel = new InfoPanel();
		this.inventory = new InventoryPanel(infoPanel);
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.setVisible(true);
		this.getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));


		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we)
            {
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure!?","",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                	System.out.println("Sending a disconnection packet");
		        	PacketDisconnectServer disconnectPlayer = new PacketDisconnectServer(("4"+socketClient.getClientNum()+socketClient.getName()).getBytes());
		        	disconnectPlayer.writeData(socketClient);
		        	System.exit(0);

                }
            }
        });



		this.setupMenuBar();

		// Set up the sidePanel to hold the Players Stats (PlayerStatsPanel), Coin Bag (GoldPanel) and Inventory (InventoryPanel)
		this.sidePanel.setLayout(new BorderLayout());
		this.sidePanel.add(infoPanel, BorderLayout.CENTER);
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

		menu.add(file);
		file.getPopupMenu().setLightWeightPopupEnabled(false);
		JMenuItem about = new JMenuItem("Info/Instructions");
		about.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	getAbout();
		    }
		});
		file.add(about);
		JMenuItem saveG = new JMenuItem("Save Game");
		saveG.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//		    	saveGame();

				//=================================================//
				//TODO: Bonnie here adding add some lines for save!
				server.save();
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
				server.load();
			}

		});
		//=================================================//
		file.add(about);
		file.add(saveG);
		file.add(loadG);
	}

	public void getAbout(){
		int count = JOptionPane.OK_OPTION;
		JOptionPane.showMessageDialog(null, "Bed Time Story created by: "
				+ "\n Andrew, Cuan, Kandice, Bonnie and David."
				+ "\nControls:"
				+ "\n r: rotate"
				+ "\n spacebar: attack"
				+ "\n arrow keys: move"
				+ "\nRight click on items in inventory for options. ", "",
		        JOptionPane.OK_OPTION);
	}

	/**
	 * Updates the GUI
	 * This means that the 3 following things are updated
	 * Players PlayerStatsPanel
	 * Players GoldPanel
	 * Players InventoryPanel
	 * @param player
	 * @param clientControl
	 */
	public void updateGUI(Player player, ClientControl clientControl){

		this.updatePlayerInventory(player, clientControl);

		this.updatePlayerFear(player.getFear());
		this.updatePlayerCoins(player.getInventory().getCoinCount());
		this.updatePlayerAttack(player.getAttackPoints());
		this.updatePlayerName(this.name);

	}



	/**
	 * Updates the players Inventory display via the InventoryPanel
	 * @param player
	 */
	public void updatePlayerInventory(Player player, ClientControl clientControl){

			this.inventory.update(player, clientControl);

	}

	/**
	 * Updates the players FearBar display via the PlayerStatsPanel
	 * @param fear
	 */
	public void updatePlayerFear(int fear){
		this.playerStats.getFearBar().setCurrentFear(fear);
	}
	public void updatePlayerCoins(int count){
		this.playerStats.setCoins(count);
	}
	public void updatePlayerName(String name){
		this.playerStats.setUserName(name);
	}
	public void updatePlayerAttack(int count){
		this.playerStats.setAttack(count);
}


	/**
	 * Returns the GameFrame currently being used for the game
	 * @return
	 */
	public GameFrame getGameFrame(){
		return this;
	}

	public void add(ClientControl socketClient) {
		this.socketClient = socketClient;
	}

}