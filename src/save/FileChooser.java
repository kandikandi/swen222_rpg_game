package save;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * Created on 05/10/2015
 *
 * @author Bonnie Liao
 *
 *         Class used just for selecting files
 *
 */
public class FileChooser {

	private FileChooser() {

	}

	public static File getFile(boolean open) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));

		if (open) {
			chooser.setDialogTitle("Open File");
			if (chooser.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			}
		} else {
			chooser.setDialogTitle("Save File");
			if (chooser.showSaveDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			}
		}
		return null;
	}

}
