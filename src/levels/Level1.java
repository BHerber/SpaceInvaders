package levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import enemy_types.EnemyType;
import enemy_types.EnemyTypeBasic;
import game_screen.BasicBlocks;
import game_screen.Player;
import handler.EnemyBulletHandler;

public class Level1 implements SuperLevel{

	private Player player;
	private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
	private EnemyBulletHandler bulletHandler;
	
	public Level1(Player player, EnemyBulletHandler bulletHandler) {
		this.player = player;
		this.bulletHandler = bulletHandler;
		addEnemies();
	}
	
	
	
	@Override
	public void draw(Graphics2D g) {
		if(enemies == null) {
			return;
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		bulletHandler.draw(g);
	}

	@Override
	public void update(double delta, BasicBlocks blocks) {
		if(enemies == null)
			return;
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update(delta, player, blocks);
		}	
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).collide(i, player, blocks, enemies);
		}
		hasDirectionChange(delta);
		bulletHandler.update(delta, blocks, player);
	}

	@Override
	public void hasDirectionChange(double delta) {
		if(enemies == null)
			return;
		
		for (int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).isOutOfBounds()) {
				changeDirectionAllEnemies(delta);
			}
		}
	}

	@Override
	public void changeDirectionAllEnemies(double delta) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).changeDirection(delta);
		}

	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public void addEnemies() {
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 10; x++){
				EnemyType e = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1 , 3, bulletHandler);
				enemies.add(e);
			}
		}
	}

	
}
