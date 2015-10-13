package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import control.MainClient;
import control.MainServer;

/**
 * Main Method starts the game. The LoginScreen displays the options to set username and to either host or join a game.
 * @author newtondavi2
 *
 */
public class LoginScreen extends JFrame {

	String username = "00";
	private BufferedImage backgroundScreen;
	private BufferedImage title;
	private BufferedImage hostGameImage;
	private BufferedImage joinGameImage;
	private BufferedImage exitGameImage;
	private BufferedImage login;
	private BufferedImage serverStarted;
	private int HEIGHT = 600;
	private int WIDTH = 1000;
	private boolean isServer;
	private boolean clicked;
	private JLabel bg = new JLabel();
	private JLabel host = new JLabel();
	private JLabel join = new JLabel();
	private boolean userNameEntered = false;
	private boolean hosted = false;
	private boolean picked = false;


	public LoginScreen() {

		this.setUndecorated(true);
		try {
			backgroundScreen = ImageIO.read(new File("LoginScreen(1).png"));
			title = ImageIO.read(new File("Title.png"));
			hostGameImage = ImageIO.read(new File("Host.png"));
			joinGameImage = ImageIO.read(new File("JoinGray.png"));
			exitGameImage = ImageIO.read(new File("Exit.png"));
			login = ImageIO.read(new File("loginButton.png"));
			serverStarted = ImageIO.read(new File("serverStarted.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(new Dimension(1000,600));
		this.pack();
		this.setVisible(true);

		addMouseListener(
				new MouseAdapter(){
					private boolean isServer;
					private boolean isJoin;

					public void mouseClicked(MouseEvent arg0){
						int xCord = arg0.getX();
						int yCord = arg0.getY();

						if(xCord > 459 && xCord < 540 && yCord > 358 && yCord < 406){
							hosted = true;
							repaint();
							//MainServer.main(null);
							MainServer server = new MainServer();
						}
						else if(xCord > 450 && xCord < 542 && yCord > 435 && yCord < 476 && userNameEntered){
							MainClient client = new MainClient();
							client.launchClient(username);
							dispose();
						} else if(xCord > 953 && xCord < 980 && yCord > 18 && yCord < 46){
							dispose();
						} else if( xCord > 10 && xCord < 201 && yCord > 10 && yCord < 54){
							String username = "00" + JOptionPane.showInputDialog(null, "enter username");
							userNameEntered = true;
						}
					}
				});

		addMouseMotionListener(new MouseMotionListener(){

			public void mouseMoved(MouseEvent arg0) {
				int xCord = arg0.getX();
				int yCord = arg0.getY();

				if(xCord > 459 && xCord < 540 && yCord > 358 && yCord < 406){
					try {
						hostGameImage = ImageIO.read(new File("HostHover.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				else {
					try {
						hostGameImage = ImageIO.read(new File("Host.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				if(userNameEntered){
					if(xCord > 450 && xCord < 542 && yCord > 435 && yCord < 476){
						try {
							joinGameImage = ImageIO.read(new File("JoinHover.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {
						try {
							joinGameImage = ImageIO.read(new File("Join.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
				;
					}
				}

				if(!userNameEntered){
					if(xCord > 450 && xCord < 542 && yCord > 435 && yCord < 476){
						try {
							joinGameImage = ImageIO.read(new File("JoinGrayHover.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						try {
							joinGameImage = ImageIO.read(new File("JoinGray.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

				if(xCord > 953 && xCord < 980 && yCord > 18 && yCord < 46){
					try {
						exitGameImage = ImageIO.read(new File("exitHover.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				} else {
					try {
						exitGameImage = ImageIO.read(new File("Exit.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				if(xCord > 10 && xCord < 201 && yCord > 10 && yCord < 54 && !userNameEntered){

					try {
						login = ImageIO.read(new File("loginHover.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else if(!userNameEntered){

					try {
						login = ImageIO.read(new File("loginButton.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}


				repaint();


			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		// Center window in screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		setBounds((scrnsize.width - WIDTH) / 2,
				(scrnsize.height - HEIGHT) / 2, WIDTH, HEIGHT);

	}

	public static void main(String args[]){
		LoginScreen screen = new LoginScreen();
//		String dave = "Dave";
//		MainServer.main(null);
//		MainClient client = new MainClient();
//		client.launchClient(dave);
	}

	public void paint(Graphics g){
		BufferedImage offScreen = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphics = offScreen.getGraphics();
		graphics.drawImage(backgroundScreen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		graphics.drawImage(hostGameImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		graphics.drawImage(joinGameImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		graphics.drawImage(title.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		graphics.drawImage(exitGameImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);

		if(!userNameEntered){
			graphics.drawImage(login.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		}

		if(hosted){
			graphics.drawImage(serverStarted.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
		}
		g.drawImage(offScreen, 0, 0, null);

	}

}