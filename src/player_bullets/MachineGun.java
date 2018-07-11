package player_bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import game_screen.BasicBlocks;
import spaceInvaderDisplay.Display;

public class MachineGun extends PlayerWeaponType{

	private Rectangle bullet;
	private final double speed = 2.5d;
	
	//machine gun constructor takes four arguements
	public MachineGun(double xPos, double yPos, int width, int height) {
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setWidth(width);
		this.setHeight(height);
		
		this.bullet = new Rectangle((int) getxPos(), (int) getyPos(), getWidth(), getHeight());
	}
	
	//draws the bullet as a white rectangle
	@Override
	public void draw(Graphics2D g) {
		if(bullet == null)
			return;
		
		g.setColor(Color.WHITE);
		g.fill(bullet);
	}

	//updates bullet position and then makes sure the the bullet is in bounds and check if it has hit the blocks
	@Override
	public void update(double delta, BasicBlocks blocks) {
		if(bullet == null)
			return;	
		
		this.setyPos(getyPos() - (delta * speed));
		bullet.y = (int) this.getyPos();
		wallCollide(blocks);
		isOutOfBounds();
	}

	//for collisions if the bullet hits it becomes null returning true
	@Override
	public boolean collisionRect(Rectangle rect) {
		if(this.bullet == null)
			return false;
		
		if(bullet.intersects(rect)) {
			this.bullet = null;
			return  true;
		}
		return false;
	}

	@Override
	public boolean collinsionPolly(Polygon poly) {
		// TODO Auto-generated method stub
		return false;
	}

	//bullet destroy method that returns true if the bullet is equal to null
	@Override
	public boolean destroy() {
		if(this.bullet == null)
			return true;
		
		return false;
	}

	//checks if the bullet has hit a wall and the removes the section of the wall that the bullet collided with
	@Override
	protected void wallCollide(BasicBlocks blocks) {
		for(int i = 0; i < blocks.wall.size(); i++) {
			if(bullet.intersects(blocks.wall.get(i))) {
				blocks.wall.remove(i);
				bullet = null;
				return;
			}
		}
	}

	//Checks to see if the bullet is still in bounds on the jscreen if not then it sents the bullet equal to null
	@Override
	protected void isOutOfBounds() {
		if(this.bullet == null)
			return;
		
		if(bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x < 0 || bullet.x > Display.WIDTH) {
			bullet = null;
		}
		
	}

}
