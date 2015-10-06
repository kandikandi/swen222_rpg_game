package control;

import java.util.List;

import model.Actor;
import model.Enemy;
import model.GameState;

public class EnemyController {

	private GameState game;

	public EnemyController(GameState game) {
		this.game = game;
	}

	public void update() {
		List<Actor> actors = game.getActors();
		for (Actor actor : actors) {
			if (actor instanceof Enemy) {
				Enemy enemy = (Enemy) actor;
				if(!enemy.getIsAlive()){
					enemy=null;
				}else{
				enemy.tick(game);
				}
			}
		}
	}

}
