package save;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import control.GameController;
import model.GameState;

/**
 * Created on 26/09/2015
 * @author Bonnie Liao
 *
 */
public class DataStorage {

//	private static final String GAME_FILE = "saved_game.xml";
	private static GameController gameController;

	public DataStorage(GameController gameController){
		this.gameController = gameController;
	}

	public static void save(){
		try {
			System.out.println("Saving Game...");

			// create JAXB context and marshaller
			JAXBContext context = JAXBContext.newInstance(GameState.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = FileChooser.getFile(false);

			// Write to file
			m.marshal(gameController.getGameState(), file);

			System.out.println("Game saved!");
		} catch (JAXBException e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
