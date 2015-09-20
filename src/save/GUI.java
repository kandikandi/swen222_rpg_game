package save;
//package save.test;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import javax.swing.BoxLayout;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JPanel;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//
//public class GUI extends JFrame implements KeyListener, ActionListener {
//
//	private Board board;
//
//	public GUI() {
//		super("Test Thing");
//
//		JPanel main = new JPanel();
//		main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
//
//		addKeyListener(this);
//
//		board = new Board();
//		main.add(board);
//
//		add(main);
//
//		JMenuBar menuBar = new JMenuBar();
//		JMenu menu = new JMenu("File");
//		menuBar.add(menu);
//
//		JMenuItem save = new JMenuItem("Save");
//		save.addActionListener(this);
//		menu.add(save);
//
//		JMenuItem load = new JMenuItem("Load");
//		load.addActionListener(this);
//		menu.add(load);
//
//		this.setJMenuBar(menuBar);
//
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		pack();
//		setVisible(true);
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// System.out.println(e.getKeyCode());
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//			board.moveUp();
//			break;
//		case KeyEvent.VK_DOWN:
//			board.moveDown();
//			break;
//		case KeyEvent.VK_LEFT:
//			board.moveLeft();
//			break;
//		case KeyEvent.VK_RIGHT:
//			board.moveRight();
//			break;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//	}
//
//	public static void main(String[] args) {
//		new GUI();
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		switch (e.getActionCommand()) {
//		case "Save":
//			save();
//			break;
//		case "Load":
//			load();
//			break;
//		}
//	}
//
//	private void load() {
//		// make a xml builder
//		SAXBuilder builder = new SAXBuilder();
//		// get the file
//		File xmlFile = new FileChooser(true).getFile();
//
//		try {
//			// build the xml document
//			Document document = (Document) builder.build(xmlFile);
//			// get the root node, in this case our group
//			Element rootNode = document.getRootElement();
//			// put all the 'group_member' children nodes into a list
//			List<Element> list = rootNode.getChildren("player");
//
//			// print information about each member
//			for (Element e : list) {
//
//				int x = Integer.parseInt(e.getChildText("position_x"));
//				int y = Integer.parseInt(e.getChildText("position_y"));
//
//				board.startOver(x, y);
//			}
//		} catch (IOException io) {
//			System.out.println(io.getMessage());
//		} catch (JDOMException jdomex) {
//			System.out.println(jdomex.getMessage());
//		}
//	}
//
//	private void save() {
//		// make xml output
//		XMLOutputter xmlOutput = new XMLOutputter();
//		xmlOutput.setFormat(Format.getPrettyFormat());
//
//		// output to users choice of destination and file name
//		try {
//			File file = new FileChooser(false).getFile();
//			xmlOutput.output(board.save(), new FileWriter(file));
//		} catch (IOException e) {
//			System.out.println("Error");
//		}
//
//		System.out.println("File saved!");
//	}
//
//}
