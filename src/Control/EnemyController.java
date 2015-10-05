package Control;

import java.util.List;

import Model.Actor;
import Model.Enemy;
import Model.GameState;

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
				enemy.tick(game);
			}
		}
	}

}
