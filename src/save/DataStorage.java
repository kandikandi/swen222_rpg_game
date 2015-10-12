package save;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.GameState;

/**
 * Created on 05/10/2015
 *
 * @author Bonnie Liao
 *
 *         Class used for the purpose of saving and loading the game state
 *
 */
public class DataStorage {

	private DataStorage() {

	}

	/**
	 * The save method. Takes a GameState object and serializes it to a xml file
	 *
	 * @param gamestate
	 *            state of the game to save
	 */
	public static void save(GameState gamestate) {
		try {
			System.out.println("Saving Game...");

			// create JAXB context and marshaller
			JAXBContext context = JAXBContext.newInstance(GameState.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = FileChooser.getFile(false);

			// Write to file
			m.marshal(gamestate, file);

			System.out.println("Game saved!");
		} catch (JAXBException e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * The load method. Uses file chooser to load the xml and create a gamestate
	 * out of it
	 *
	 * @return the loaded game state
	 */
	public static GameState load() {
		System.out.println("Loading Game...");
		try {
			JAXBContext context = JAXBContext.newInstance(GameState.class);
			Unmarshaller um = context.createUnmarshaller();

			File file = FileChooser.getFile(true);

			GameState gamestate = (GameState) um.unmarshal(file);
//			GameState gs = new GameState(false);
//			gs.setActors(gamestate.getActors());

			System.out.println("Game loaded!");
			return gamestate;
		} catch (JAXBException e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
