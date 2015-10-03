package packet;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Actor;



public class Serialiser {

	    public byte[] serialize(Object obj) throws IOException {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        System.out.println("starting to serialise");
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        System.out.println("created a outputstream");
	        o.writeObject(obj);
	        o.flush();
	        System.out.println("serialising complete bitches");
	        return b.toByteArray();
	    }

	    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
	        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
	        ObjectInputStream o = new ObjectInputStream(b);
	        System.out.println("deserialising complete bitches");
	        Object object = o.readObject();
	        o.close();
	        return object;
	    }
	}




