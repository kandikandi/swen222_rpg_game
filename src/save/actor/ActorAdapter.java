package save.actor;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Actor;
import model.Coin;
import model.CoinBag;
import model.Enemy;
import model.Key;
import model.Player;
import model.Wall;

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
	public Actor unmarshal(AdaptedActor aa) throws Exception {
		switch (aa.getType()) {
		case "coin":
			return new Coin(aa.getId(), aa.getPosition(), aa.getAsciiCode(), aa.isCollidable(), aa.isDrawable());
		case "coinbag":
			return new CoinBag(aa.getId(), aa.getPosition(), aa.getAsciiCode(), aa.isCollidable(), aa.isDrawable());
		case "key":
			return new Key(aa.getId(), aa.getPosition(), aa.getAsciiCode(), aa.isCollidable(), aa.isDrawable());
		case "enemy":
			Enemy enemy = new Enemy(aa.getId(), aa.getPosition(), aa.getAsciiCode(), aa.isCollidable(),
					aa.isDrawable());
			enemy.setAlive(aa.isAlive());
			enemy.setAttackPoints(aa.getAttackPoints());
			enemy.setHealth(aa.getHealth());
			enemy.setCount(aa.getCount());
			return enemy;
		case "player":
			Player player = new Player(aa.getId(), aa.getPosition(), aa.getAsciiCode(), aa.isCollidable(),
					aa.isDrawable(), aa.getClientNum());
			player.setHasKey(aa.hasKey());
			player.setFear(aa.getFear());
			player.setBravery(aa.getBravery());
			player.setClientNum(aa.getClientNum());
			player.setAttackPoints(aa.getAttackPoints());
			player.setAlive(aa.isAlive());
			return player;
		case "wall":
			return new Wall(aa.getId(), aa.getPosition(), aa.getAsciiCode(), true, aa.isDrawable());
		}
		return null;
	}

	/**
	 * Makes an adapted actor with the current state of the actor.
	 */
	@Override
	public AdaptedActor marshal(Actor a) throws Exception {
		AdaptedActor aa = new AdaptedActor();
		if (a instanceof Coin) {
			aa.setType("coin");
		} else if (a instanceof CoinBag) {
			aa.setType("coinbag");
		} else if (a instanceof Key) {
			aa.setType("key");
		} else if (a instanceof Enemy) {
			Enemy enemyactor = (Enemy) a;
			aa.setType("enemy");
			aa.setAlive(enemyactor.getIsAlive());
			aa.setAttackPoints(enemyactor.getAttackPoints());
			aa.setHealth(enemyactor.getHealth());
			aa.setCount(enemyactor.getCount());
		} else if (a instanceof Player) {
			Player player = (Player) a;
			aa.setType("player");
			aa.setHasKey(player.hasKey());
			aa.setFear(player.getFear());
			aa.setBravery(player.getBravery());
			aa.setClientNum(player.getClientNum());
			aa.setAttackPoints(player.getAttackPoints());
			aa.setAlive(player.isAlive());
		} else if (a instanceof Wall) {
			aa.setType("wall");
		}
		aa.setAsciiCode(a.getAsciiCode());
		aa.setBoundingBox(a.getBoundingBox());
		aa.setCollidable(a.isCollidable());
		aa.setDrawable(a.isDrawable());
		aa.setId(a.getID());
		aa.setPosition(a.getPosition());
		return aa;
	}

}