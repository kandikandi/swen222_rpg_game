package control;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**The serialiser class is used by the Server and Client control classes to package and unpackage the arraylist of actors
 * This is cleaner than using seperate methods as can use either anywhere if needed, and ensures that the same input/output methods are used everywhere
 * which helps prevent errors
 * @author mcleankand
 *
 */
public class Serialiser {

	    public synchronized byte[] serialize(Object obj) throws IOException {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        o.writeObject(obj);
	        o.flush();
	        return b.toByteArray();
	    }

	    public synchronized Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
	        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
	        ObjectInputStream o = new ObjectInputStream(b);
	        Object object = o.readObject();
	        o.close();
	        return object;
	    }
	}




