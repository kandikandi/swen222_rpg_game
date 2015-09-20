package save;
//package save.test;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Point;
//
//import javax.swing.JPanel;
//import javax.swing.KeyStroke;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//
//public class Board extends JPanel {
//	private Square[][] board;
//	private Square square;
//	public static final int WIDTH = 40;
//	public static final int HEIGHT = 20;
//	public static final int SIZE = 20;
//
//	public Board() {
//		startOver(0,0);
//	}
//
//	public Dimension getPreferredSize() {
//		return new Dimension(WIDTH * SIZE, HEIGHT * SIZE);
//	}
//
//	public void moveUp() {
//		int newY = square.getLocation().y - 1;
//		if (newY > -1) {
//			movePlayer(new Point(square.getLocation().x, newY));
//		}
//	}
//
//	public void moveDown() {
//		int newY = square.getLocation().y + 1;
//		if (newY < HEIGHT) {
//			movePlayer(new Point(square.getLocation().x, newY));
//		}
//	}
//
//	public void moveLeft() {
//		int newX = square.getLocation().x - 1;
//		if (newX > -1) {
//			movePlayer(new Point(newX, square.getLocation().y));
//		}
//	}
//
//	public void moveRight() {
//		int newX = square.getLocation().x + 1;
//		if (newX < WIDTH) {
//			movePlayer(new Point(newX, square.getLocation().y));
//		}
//	}
//
//	private void movePlayer(Point point) {
//		board[square.getLocation().x][square.getLocation().y] = null;
//		board[point.x][point.y] = square;
//		square.setLocation(point);
//		repaint();
//	}
//
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//	}
//
//	public void paint(Graphics g) {
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, WIDTH * SIZE, HEIGHT * SIZE);
//		g.setColor(Color.BLACK);
//		g.fillRect(square.getLocation().x * SIZE,
//				square.getLocation().y * SIZE, SIZE, SIZE);
//	}
//
//	public Document save() {
//		// make a group element "ABCDK"
//		// our 'root node' in the tree
//		Element root = new Element("Board");
//		Document doc = new Document(root);
//
//		// make a group member and add attributes like name and hair colour
//		Element player = new Element("player");
//		player.addContent(new Element("position_x").setText(square.getLocation().x + ""));
//		player.addContent(new Element("position_y").setText(square.getLocation().y + ""));
//
//		// add to the root
//		doc.getRootElement().addContent(player);
//
//		return doc;
//	}
//
//	public void startOver(int x, int y) {
//		board = new Square[WIDTH][HEIGHT];
//		square = new Square();
//		board[x][y] = square;
//		square.setLocation(new Point(x,y));
//		repaint();
//	}
//}
