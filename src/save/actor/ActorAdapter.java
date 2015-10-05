package save.actor;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import Model.Actor;
import Model.Coin;
import Model.CoinBag;
import Model.Enemy;
import Model.Key;
import Model.Player;

/**
 * Created on 05/101/2015
 *
 * @author Bonnie Liao
 *
 *         Class used to save and load the different kinds of actors.
 *
 */
public class ActorAdapter extends XmlAdapter<AdaptedActor, Actor> {

	/**
	 * Using the adapted actor class, it takes all the information from it and
	 * makes a new actor with those saved states
	 */
	@Override
	public Actor unmarshal(AdaptedActor adaptedactor) throws Exception {
		switch (adaptedactor.getType()) {
		case "coin":
			return new Coin(adaptedactor.getId(), adaptedactor.getPosition(),
					adaptedactor.getAsciiCode(), adaptedactor.isCollidable(),
					adaptedactor.isDrawable(),
					adaptedactor.getBoundingBoxSize());
		case "coinbag":
			return new CoinBag(adaptedactor.getId(),
					adaptedactor.getPosition(), adaptedactor.getAsciiCode(),
					adaptedactor.isCollidable(), adaptedactor.isDrawable(),
					adaptedactor.getBoundingBoxSize());
		case "key":
			return new Key(adaptedactor.getId(), adaptedactor.getPosition(),
					adaptedactor.getAsciiCode(), adaptedactor.isCollidable(),
					adaptedactor.isDrawable(),
					adaptedactor.getBoundingBoxSize());
		case "enemy":
			Enemy enemy = new Enemy(adaptedactor.getId(),
					adaptedactor.getPosition(), adaptedactor.getAsciiCode(),
					adaptedactor.isCollidable(), adaptedactor.isDrawable(),
					adaptedactor.getBoundingBoxSize());
			enemy.setAlive(adaptedactor.isAlive());
			enemy.setAttackPoints(adaptedactor.getAttackPoints());
			enemy.setHealth(adaptedactor.getHealth());
			enemy.setCount(adaptedactor.getCount());
			return enemy;
		default:
			Player player = new Player(adaptedactor.getId(),
					adaptedactor.getPosition(), adaptedactor.getAsciiCode(),
					adaptedactor.isCollidable(), adaptedactor.isDrawable(),
					adaptedactor.getBoundingBoxSize(),
					adaptedactor.getClientNum());
			player.setHasKey(adaptedactor.hasKey());
			player.setFear(adaptedactor.getFear());
			player.setBravery(adaptedactor.getBravery());
			player.setClientNum(adaptedactor.getClientNum());
			player.setAttackPoints(adaptedactor.getAttackPoints());
			player.setAlive(adaptedactor.isAlive());
			return player;
		}
	}

	/**
	 * Makes an adapted actor with the current state of the actor.
	 */
	@Override
	public AdaptedActor marshal(Actor actor) throws Exception {
		AdaptedActor adaptedactor = new AdaptedActor();
		if (actor instanceof Coin) {
			adaptedactor.setType("coin");
		} else if (actor instanceof CoinBag) {
			adaptedactor.setType("coinbag");
		} else if (actor instanceof Key) {
			adaptedactor.setType("key");
		} else if (actor instanceof Enemy) {
			Enemy enemyactor = (Enemy) actor;
			adaptedactor.setType("enemy");
			adaptedactor.setAlive(enemyactor.getIsAlive());
			adaptedactor.setAttackPoints(enemyactor.getAttackPoints());
			adaptedactor.setHealth(enemyactor.getHealth());
			adaptedactor.setCount(enemyactor.getCount());
		} else {
			Player player = (Player) actor;
			adaptedactor.setType("player");
			adaptedactor.setHasKey(player.hasKey());
			adaptedactor.setFear(player.getFear());
			adaptedactor.setBravery(player.getBravery());
			adaptedactor.setClientNum(player.getClientNum());
			adaptedactor.setAttackPoints(player.getAttackPoints());
			adaptedactor.setAlive(player.isAlive());
		}
		adaptedactor.setAsciiCode(actor.getAsciiCode());
		adaptedactor.setBoundingBoxSize(actor.getBoundingBox().height);
		adaptedactor.setCollidable(actor.isCollidable());
		adaptedactor.setDrawable(actor.isDrawable());
		adaptedactor.setId(actor.getID());
		adaptedactor.setPosition(actor.getPosition());
		return adaptedactor;
	}

}
