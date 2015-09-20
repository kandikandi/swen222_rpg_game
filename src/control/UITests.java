package control;

import java.awt.Image;
import java.awt.event.KeyListener;

import model.Collectable;
import model.Container;
import model.GameState;
import model.ID;
import model.Inventory;
import model.Player;
import model.Position;

import org.junit.Test;

import system.SDraw;
import ui.GameCanvas;
import ui.GameFrame;

public class UITests {

	@Test public void test_UI1() {
		//tests whether container will not accept more items if full
		GameController gc = new GameController();
		GameFrame g = new GameFrame("GUI Inventory Test", 1000, 600, gc);

		GameCanvas gameCanvas = new GameCanvas(800, 600);
		g.getContentPane().add(gameCanvas);
		g.pack();
		KeyListener gameKeyListner = new GameKeyListener(/*controller*/);
		g.addKeyListener(gameKeyListner);

		//this is awkward 1/2
		GameState gameState = new GameState((GameKeyListener)gameKeyListner);

		//this is awkward 2/2 (the actor list is so they can be ticked)
		gc.addActorList(gameState.getAllActors());


		// create and add systems in order they need to be executed
		SDraw drawSystem = new SDraw(gameState,gameCanvas);
		gc.addSystem(drawSystem);

		// create time to control systems loop
		GameTimer timer = new GameTimer(gc);
		timer.start();

		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		Collectable collectable1 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable2 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable3 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable4 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable5 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable6 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		//Collectable collectable7 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);

		Inventory container = new Inventory(ID.CONTAINER, new Position(5,5), null, true, true,45, player,
				collectable1,
				collectable2,
				collectable3,
				collectable4,
				collectable5,
				collectable6);

		player.setInventory(container);
		System.out.println(player.getInventory().returnContents());
		g.update(player);
		assert(container.numberOfObjectInContainer()==5);
		while(true){

		}
	}



}
