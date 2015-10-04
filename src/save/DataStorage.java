package save;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Model.GameState;

public class DataStorage {

	private DataStorage(){

	}

	public static void save(GameState gamestate){
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
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
