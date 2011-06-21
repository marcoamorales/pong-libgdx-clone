package com.pong;

public class Ball extends GameObject {
	public static final int BALL_MOVING = 0;
	public static final int BALL_BOUNCE = 1;
	public static final int BALL_SCORE = 2;
	public static final float BALL_WIDTH = 1;
	public static final float BALL_HEIGHT = 1;
	
	int state;
	
	public Ball(float x, float y){
		super(x, y, BALL_WIDTH, BALL_HEIGHT);
		velocity.set(15, 15);
		state = BALL_MOVING;
	}
	
	public void update(float deltaTime){
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;		
	}
	
	public void bounce(){
		velocity.x = velocity.x * -1;
		state = BALL_BOUNCE;
	}
	
	public void score(){
		position.set(10, 10);
		velocity.set(15, 15);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
	}
	

}
