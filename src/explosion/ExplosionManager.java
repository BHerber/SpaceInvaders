package explosion;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ExplosionManager {
	
	private static ArrayList<ExplosionType> explosions = new ArrayList<ExplosionType>();
	
	public ExplosionManager() {
		
	}
	
	public void draw (Graphics2D g) {
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}
	}
	
	public void update (double delta) {
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update(delta);
			if(explosions.get(i).destroy()) {
				explosions.remove(i);
			}
		}	
	}
	
	public static void createPixelExplosion(double xPos, double yPos) {
		ExplosionType et = new PixelExplosion(xPos, yPos);
		explosions.add(et);
	}
}
