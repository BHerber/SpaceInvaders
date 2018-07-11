package enemy_bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game_screen.BasicBlocks;
import game_screen.Player;
import spaceInvaderDisplay.Display;

public class EnemyBasicBullet extends EnemyWeaponType{
	
	private Rectangle bullet;
	private double speed = 2.5;
	private int xPos, yPos;

	public EnemyBasicBullet(double xPos, double yPos) {
		bullet = new Rectangle((int) xPos, (int) yPos, 5, 5);
		setxPos((int) xPos);
		setyPos((int) yPos);
	}
	
	@Override
	public void draw(Graphics2D g) {
		if (bullet == null) {
			return;
		}
		
		g.setColor(Color.RED);
		g.fill(bullet);
	}

	@Override
	public void update(double delta, BasicBlocks blocks, Player player) {
		if (bullet == null) {
			return;
		}
		
		setyPos((int) (getyPos() + (delta * speed)));
		bullet.y = getyPos();
		
		isOutOfBounds();
		wallCollide(blocks);
	}

	@Override
	public boolean collision(Rectangle rect) {
		if (bullet != null && bullet.intersects(rect)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean destroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void wallCollide(BasicBlocks blocks) {
		if(bullet == null) {
			return;
		}
		
		for (int w = 0; w < blocks.wall.size(); w++) {
			if(bullet.intersects(blocks.wall.get(w))) {
				blocks.wall.remove(w);
				bullet = null;
				break;
			}
		}
	}

	@Override
	protected void isOutOfBounds() {
		if(bullet !=null || bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x <0 || bullet.x > Display.WIDTH) {
			bullet = null;
		}
	}
	
	public Rectangle getBullet() {
		return bullet;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
