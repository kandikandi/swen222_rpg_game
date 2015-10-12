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

	/**
	 * This method updates enemy state, including movement and attacking.
	 *
	 */
	public void update() {
		List<Enemy> enemies = game.getEnemies();
		for (Enemy enemy : enemies) {
			if (!enemy.getIsAlive()) {
				enemy = null;
			} else {
				enemy.tick(game);
			}
		}
	}
}
