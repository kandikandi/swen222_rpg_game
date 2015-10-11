package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import control.GameClient;
import model.Actor;
import model.Player;

/**
 * The GameCanvas receives images via its receiveBuffImage(BufferedImage buffimg) method,
 * this imageName is then drawn to display on the canvas
 * @author newtondavi2 (David)
 *
 */
public class GameCanvas extends JPanel {

	private BufferedImage receivedImage = null;
	private int WIDTH;
	private int HEIGHT;
	private GameFrame frame;
	private GameClient socketClient;
	private boolean clientSet = false;
	//private final GameController gameController;

	//TODO does this class need a reference to GameFrame?
	/**
	 * Constructs the GameCanvas, setting the Canvas to the current GameFrame
	 * @param frame
	 * @param WIDTH
	 * @param HEIGHT
	 */
	public GameCanvas(GameFrame frame, int WIDTH, int HEIGHT) {

		//this.gameController = gameController;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.frame = frame;
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

	}

	/**
	 * Sets the imaged to be buffered, which is then painted via the paintCompoent(Graphics g) method
	 * @param buffImg
	 */
	public void receiveBuffImage(BufferedImage buffImg) {
		//System.out.println("GameCanvas receveBuffImage");
		receivedImage = buffImg;
		this.repaint();
		//this works to - might be to quick
		//if(gameController.getPlayer() != null){
		//	this.getFrame().updateGUI(gameController.getPlayer());
		//}

	}

	@Override
	public void paintComponent(Graphics g) {
		//System.out.println("GameCanvas paintComponent");
		Graphics2D g2 = (Graphics2D) g;
		if(receivedImage == null){
			g2.fillRect(0, 0, 800, 600);
		}else{
			g2.drawImage(receivedImage,0,0,this);
		}
		//this works
		if(clientSet){
			Player player = socketClient.getGameState().findPlayer(socketClient.getClientNum());
			if(player != null){
				this.getFrame().updateGUI(player);
			}

		}


	}

	public void setSocketClient(GameClient client){
		this.socketClient = client;
		this.clientSet = true;
	}

	/**
	 * Returns the GameFrame which this canvas is connected to.
	 * @return
	 */
	public GameFrame getFrame(){
		return frame;
	}
}