package explosion;

import java.awt.Graphics2D;

public abstract class ExplosionType {

	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
	
	public abstract boolean destroy();
}
