package com.pong;

public class Paddle extends GameObject{
	public static final float PADDLE_WIDTH = 1;
	public static final float PADDLE_HEIGHT = 6.4f;
	public static final float PADDLE_MOVE_VELOCITY = 5;
	
	public Paddle(float x, float y){
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
	}

}
