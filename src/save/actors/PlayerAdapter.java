package save.actors;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Player;

public class PlayerAdapter extends XmlAdapter<AdaptedPlayer, Player>{

	@Override
	public Player unmarshal(AdaptedPlayer ap) throws Exception {
		Player p = new Player(ap.getPosition(), ap.getAsciiCode(), ap.isCollidable(), ap.isDrawable(), ap.getClientNum());
		p.setInventory(ap.getInventory());
		p.setHasKey(ap.isHasKey());
		p.setAttack(ap.isPlayerIsAttacking());
		p.setAttackPoints(ap.getAttackPoints());
		p.setFear(ap.getFear());
		p.setAlive(ap.isAlive());
		p.setBravery(ap.getBravery());
		return p;
	}

	@Override
	public AdaptedPlayer marshal(Player p) throws Exception {
		AdaptedPlayer ap = new AdaptedPlayer();
		ap.setInventory(p.getInventory());
		ap.setHasKey(p.hasKey());
		ap.setPlayerIsAttacking(p.getAttacking());
		ap.setAttackPoints(p.getAttackPoints());
		ap.setFear(p.getFear());
		ap.setAlive(p.isAlive());
		ap.setBravery(p.getBravery());
		ap.setClientNum(p.getClientNum());
		ap.setPosition(p.getPosition());
		ap.setCollidable(p.isCollidable());
		ap.setDrawable(p.isDrawable());
		ap.setAsciiCode(p.getAsciiCode());
		ap.setActorDescription(p.getDescription());
		return ap;
	}

}
