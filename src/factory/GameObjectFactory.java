/*
package factory;

import model.AbstractGameObject;
import model.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

*/
/**
 * Created by cuan on 9/12/15.
 *//*

public class GameObjectFactory implements AbstractFactory{
    private final Set<GameObject> objectsSet = new HashSet<>();


    @Override
    public Iterator<? extends AbstractGameObject> getIterator() {
        return objectsSet.iterator();
    }

    @Override
    public GameObject createTestProduct(){
        Image image = null;
        try {
            image = ImageIO.read(new File("tokenPlum.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameObject gameObject = new GameObject(image, 50, 50, 200, 200);
        objectsSet.add(gameObject);
        return gameObject;
    }

    @Override
    public GameObject createProduct(Image image, int xpos, int ypos, int width, int height) {
        GameObject gameObject = new GameObject(image,xpos,ypos,width,height);
        objectsSet.add(gameObject);
        return gameObject;
    }

    @Override
    public void destroyProduct(AbstractGameObject gameObject) {

    }



   */
/* public GameObject createProduct(Image image, int xpos, int ypos, int width, int height){
        GameObject gameObject = new GameObject(image, xpos, ypos, width, height);
        objectsSet.add(gameObject);
        return gameObject;
    }*//*



*//*


}
*/
