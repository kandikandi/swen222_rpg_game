package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameCanvas extends JPanel {

	private BufferedImage receivedImage = null;
	private int WIDTH;
	private int HEIGHT;

	public GameCanvas(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}

	public void receiveBuffImage(BufferedImage buffImg) {
        //System.out.println("GameFrame recieve BuffImage");
        receivedImage = buffImg;
        this.repaint();
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
	}
}