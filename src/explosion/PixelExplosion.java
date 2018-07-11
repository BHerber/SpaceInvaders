package explosion;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class PixelExplosion extends ExplosionType{

	private double[] xPos, yPos, xPosVol, yPosVol, angle, energy;
	
	public PixelExplosion (double xPos, double yPos) {
		int index = 12;
		this.xPos = new double [index];
		this.yPos = new double [index];
		this.xPosVol = new double [index];
		this.yPosVol = new double [index];
		this.angle = new double [index];
		this.energy = new double [index];
		
		for (int i = 0; i < index; i++) {
			this.xPos[i] = xPos;
			this.yPos[i] = yPos;
			
			this.xPosVol[i] = Math.random() * 1;
			this.yPosVol[i] = Math.random() * 1;
			this.energy[i] = Math.random();
			
			Random r = new Random();
			this.angle[i] = r.nextInt(6) + 1;


		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		for (int i = 0; i < xPos.length; i++) {
			if(energy[i] >= 0.00d) {
				g.setColor(new Color(1.0f, 1.0f, 0f,(float) energy[i]));
			}else {
				g.setColor(new Color(1.0f, 1.0f, 0f, 0));
			}
			g.fillRect((int) xPos[i], (int) yPos[i], 3, 3);
		}
		
	}

	@Override
	public void update(double delta) {
		for (int i = 0; i < xPos.length; i++) {
			energy[i] -= 0.01d;
			xPos[i] += xPosVol[i] * Math.cos(angle[i]);
			yPos[i] += yPosVol[i] * Math.cos(angle[i]);

		}
		
	}

	@Override
	public boolean destroy() {
		int destroy = 0;
		
		for (int i = 0; i < xPos.length; i++) {
			if(energy[i] < 0.00d) {
				destroy++;
			}else {
				break;
			}
		}
		
		if(destroy == energy.length) {
			return true;
		}
		
		return false;
	}
	

}
