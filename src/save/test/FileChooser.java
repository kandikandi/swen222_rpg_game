package save.test;
import java.io.File;

import javax.swing.*;

public class FileChooser extends JPanel{

	private JFileChooser chooser;
	private File fileDir;

	public FileChooser(boolean open) {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));

		if(open){
			chooser.setDialogTitle("Open File");
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				fileDir = chooser.getSelectedFile();
			}
		}
		else{
			chooser.setDialogTitle("Save File");
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				fileDir = chooser.getSelectedFile();
			}
		}
	}

	public File getFile() {
		return fileDir;
	}

}
