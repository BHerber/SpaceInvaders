package game_screen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import explosion.ExplosionManager;
import player_bullets.MachineGun;
import player_bullets.PlayerWeaponType;
import timer.Timer;

public class PlayerWeapons {
	
		private Timer timer;
		private ExplosionManager explosionManager;
		public ArrayList<PlayerWeaponType> weapons = new ArrayList<PlayerWeaponType>();
		
		public PlayerWeapons() {
			explosionManager = new ExplosionManager();
			timer = new Timer();
		}
		
		//draw weapon
		public void draw(Graphics2D g) {
			
			explosionManager.draw(g);
			for(int i = 0; i < weapons.size(); i++) {
				weapons.get(i).draw(g);
				} 
		}
		
		//updates weapon sise and removes weapons when bullets are destroyed
		public void update (double delta, BasicBlocks blocks) {
			
			explosionManager.update(delta);
			for(int i = 0; i < weapons.size(); i++) {
				weapons.get(i).update(delta, blocks);
				if(weapons.get(i).destroy()) {
					ExplosionManager.createPixelExplosion(weapons.get(i).getxPos(), weapons.get(i).getyPos());
					weapons.remove(i);
				}
			}
		}
		
		//initalises machine gun at position alligned to the playersprite
		public void shootBullet(double xPos, double yPos, int width, int height) {
			if(timer.timerEvent(250) && weapons.size() < 3)
				weapons.add(new MachineGun(xPos + 22, yPos, width, height));
		}
}
