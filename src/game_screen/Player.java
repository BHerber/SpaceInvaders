package game_screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import spaceInvaderDisplay.Display;

public class Player implements KeyListener{
	
	private final double speed = 5.0d;
	
	private BufferedImage pSprite;
	private Rectangle rect;
	private double xPos, yPos;
	private int width, height;
	private BasicBlocks blocks;
	
	private boolean left = false, right = false, shoot = false;
	
	public PlayerWeapons playerWeapons;
	
	public Player (double xPos, double yPos, int width, int height, BasicBlocks blocks) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
		rect = new Rectangle((int) xPos,(int) yPos, width, height);
		
		//this is used to get the player image and set it equal to playersprite
		try {
			URL url = this.getClass().getResource("/images/Player.png");
			pSprite = ImageIO.read(url);
		//input catch
		}catch(IOException e) {};
		
		this.blocks = blocks;
		playerWeapons = new PlayerWeapons();
	}
	
	//draws the player image to the screen along with the weapon
	public void draw(Graphics2D g) {
		g.drawImage(pSprite, (int) xPos, (int) yPos, width, height, null);
		playerWeapons.draw(g);
	}
	
	//update the position of the player and shoot 
	public void update(double delta) {
		//if right == true then move speed times delta to the right
		if(right && !left && xPos < Display.WIDTH-width) {
			xPos+= speed * delta;
			rect.x = (int) xPos;
		//if left == true then move speed times delta to the left

		}else if(!right && left && xPos > 10) {
			xPos-= speed * delta;
			rect.x = (int) xPos;
		}
		playerWeapons.update(delta, blocks);
		
		if(shoot) {
			playerWeapons.shootBullet(xPos, yPos, 5, 5);
		}
	}
	
	//if key d or a and left or right key move, if space shoot
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = true;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			left = true;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			shoot = true;
		}
	}

	//if key d or a and left or right key are released stop moving, if space released stop shooting
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = false;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			left = false;
		}	
		
		if(key == KeyEvent.VK_SPACE) {
			shoot = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return rect;
	}
	
	

}
