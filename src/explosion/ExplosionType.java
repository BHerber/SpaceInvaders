package explosion;

import java.awt.Graphics2D;

public interface ExplosionType {

	public void draw(Graphics2D g);
	public void update(double delta);
	
	public boolean destroy();
}
