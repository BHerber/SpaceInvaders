package state;

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class SuperStateMachine {
	
	private StateMachine stateMachine;
	
	public SuperStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	public abstract void update(double delta);
	public abstract void draw(Graphics2D g);
	public abstract void init(Canvas canvas);
	
	public StateMachine getStateMachine() {
		return stateMachine;
	}
}
