package save;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FileChooser {

	private FileChooser(){

	}

	public static File getFile(boolean open) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));

		if(open){
			chooser.setDialogTitle("Open File");
			if (chooser.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			}
		}
		else{
			chooser.setDialogTitle("Save File");
			if (chooser.showSaveDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			}
		}
		return null;
	}


}
