package game_screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BasicBlocks {
	
	//The ArrayList that will be used to store the 4 blocks.
	public ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
	
	//Constructs the blocks at 4 different x values equidistant from eachother 
	public BasicBlocks() {
		basicBlocks(75, 450);
		basicBlocks(275, 450);
		basicBlocks(475, 450);
		basicBlocks(675, 450);
	}
	
	//draws the blocks sets them to blue and allows us to slowing destroy them when player shoots
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		for (int i = 0; i < wall.size(); i++) {
			g.fill(wall.get(i));
		}
	}
	
	
	public void basicBlocks(int xPos, int yPos) {
		int wallWidth = 3;
		int x = 0;
		int y = 0;
	// sets the shaping of the blocks to create unique shape - Nabil	
		for(int i = 0; i < 13; i++) {
			if(14 + (i * 2) + wallWidth < 22 + wallWidth) {
				row(14 + (i * 2) + wallWidth, xPos - (i * 3), yPos + (i * 3));
				x = (i * 3) + 3;
			}else {
				row(22 + wallWidth, xPos - x , yPos + (i * 3));
				y = (i * 3);
			}
		}
		
		//LeftSide of the blocks
		for(int i = 0; i < 5; i++) {
			row(8 + wallWidth - i, xPos - x, (yPos + y) + (i * 3));
		}
		
		//RightSide of the blocks
				for(int i = 0; i < 5; i++) {
					row(8 + wallWidth - i, (xPos - x + (14 * 3)) + (i * 3), (yPos + y) + (i * 3));
				}
	}
	
	//creates rows of pixels for the blocks allowing them to be broken
	public void row(int rows, int xPos, int yPos) {
		for(int i = 0; i < rows; i++) {
			Rectangle brick = new Rectangle(xPos + (i * 3), yPos, 3, 3);
			this.wall.add(brick);
		}
	}
	
	//will be used to reset the blocks when a new game is initialized 
	public void reset() {
		wall.clear();
		
		basicBlocks(75, 450);
		basicBlocks(275, 450);
		basicBlocks(475, 450);
		basicBlocks(675, 450);
	}
}
