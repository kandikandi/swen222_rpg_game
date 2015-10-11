package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import control.GameServer;
import control.Main;
import control.MainClient;
import control.MainServer;

public class LoginScreen extends JFrame {

	private BufferedImage backgroundScreen;
	private BufferedImage title;
	private BufferedImage hostGameImage;
	private BufferedImage joinGameImage;
	private BufferedImage exitGameImage;
	private BufferedImage login;
	private BufferedImage serverStarted;
	private int HEIGHT;
	private int WIDTH;
	private boolean isServer;
	private boolean clicked;
	private JLabel bg = new JLabel();
	private JLabel host = new JLabel();
	private JLabel join = new JLabel();
	private boolean userNameEntered = false;
	private boolean hosted = false;
	String username = "00";

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

		//this.setBackground(new Color(100,100,100));

		this.HEIGHT = 600;
		this.WIDTH = 1000;

		//this.getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));
		//this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(1000,600));
		this.pack();
		this.revalidate();
		this.setVisible(true);



//		confirm.addActionListener(
//				new ActionListener(){
//
//					public void actionPerformed(ActionEvent e){
//						if(nameField.getText().length() <= 0 || nameField.getText().length() >= 13){
//							JOptionPane.showMessageDialog(LoginScreen.this, "You have entered either to little or to many characters");
//						}
//						else {
//							username = "OO";
//							username = username + nameField.getText();
//							nameField.setText(""); //resets text to be blank
//							userNameEntered = true;
//							repaint();
//							//nameTitle.setText("Player " + (count + 1) + "'s Name:");
//						}
//					}
//				}
//				);
//		getContentPane().add(confirm);

//		addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosing(WindowEvent we) {
//
//				String ObjButtons[] = {"Yes", "No"};
//				int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Bed Time Story",
//						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
//				if (PromptResult == JOptionPane.YES_OPTION) {
//
//					System.exit(0);
//				}
//			}
//		});



		addMouseListener(
				new MouseAdapter(){
					private boolean isServer;
					private boolean isJoin;

					public void mouseClicked(MouseEvent arg0){
						int xCord = arg0.getX();
						int yCord = arg0.getY();
						System.out.println("x: " + xCord + "y " + yCord);
						if(xCord > 459 && xCord < 540 && yCord > 358 && yCord < 406){

							System.out.println("Launching server");
							hosted = true;
							MainServer.main(null);

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

			/*
			 * (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
			 */
			public void mouseMoved(MouseEvent arg0) {
				int xCord = arg0.getX();
				int yCord = arg0.getY();
				if(xCord > 459 && xCord < 540 && yCord > 358 && yCord < 406){
					try {
						hostGameImage = ImageIO.read(new File("HostHover.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
				else {
					try {
						hostGameImage = ImageIO.read(new File("Host.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
				if(userNameEntered){
					if(xCord > 450 && xCord < 542 && yCord > 435 && yCord < 476){
						try {
							joinGameImage = ImageIO.read(new File("JoinHover.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						repaint();
					} else {
						try {
							joinGameImage = ImageIO.read(new File("Join.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						repaint();
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
						repaint();
					} else {
						try {
							joinGameImage = ImageIO.read(new File("JoinGray.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						repaint();
					}
				}

				if(xCord > 953 && xCord < 980 && yCord > 18 && yCord < 46){
					try {
						exitGameImage = ImageIO.read(new File("exitHover.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();

				} else {
					try {
						exitGameImage = ImageIO.read(new File("Exit.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}

				if(xCord > 10 && xCord < 201 && yCord > 10 && yCord < 54 && !userNameEntered){

					try {
						login = ImageIO.read(new File("loginHover.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



				} else if(!userNameEntered){

					try {
						login = ImageIO.read(new File("loginButton.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub


			}
		});





		// Center window in screen
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Dimension scrnsize = toolkit.getScreenSize();
				setBounds((scrnsize.width - WIDTH) / 2,
						(scrnsize.height - HEIGHT) / 2, WIDTH, HEIGHT);

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

//	private void checkIfServer(Boolean server, Boolean join){
//
//		if(server && !join){
//			this.isServer = true;
//		}
//		if(!server && join){
//			this.isServer = false;
//		}
//		this.picked = true;
//		this.clicked = true;
//		this.setVisible(false);
//
//	}

	public static void main(String args[]){
		  LoginScreen screen = new LoginScreen();
	}

	public boolean getResult() {
		return this.isServer;
	}

	public boolean getClick() {
		return this.clicked;
	}



}