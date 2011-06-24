package com.pong;

public class World {
	public interface WorldListener {
		public void bump();
	}
	
	public static final float WORLD_WIDTH = 48;
	public static final float WORLD_HEIGHT = 32;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_GAME_END = 1;
	
	public final Paddle paddleP1;
	public final Paddle paddleP2;
	public final Ball ball;
	public final WorldListener listener;
	
	public int scoreP1;
	public int scoreP2;
	public int state;
	
	public World(WorldListener listener){
		this.ball = new Ball(10,10);
		this.paddleP1 = new Paddle(1,16);
		this.paddleP2 = new Paddle(47, 16);
		this.scoreP1 = 0;
		this.scoreP2 = 0;
		this.listener = listener;
		this.state = WORLD_STATE_RUNNING;
		
	}
	
	public void update(float deltaTime, float accelP1, float accelP2){
		updateBall(deltaTime);
		updatePaddles(deltaTime, accelP1, accelP2);
		checkCollisions();
		checkGameOver();
	}
	
	public void updateBall(float deltaTime){
		ball.update(deltaTime);
		if (ball.position.y >= WORLD_HEIGHT && ball.velocity.y > 0)
			ball.velocity.y = ball.velocity.y * -1;
		if (ball.position.y <= 0 && ball.velocity.y < 0)
			ball.velocity.y = ball.velocity.y * -1;
		if (ball.position.x >= WORLD_WIDTH){
			scoreP1++;
			ball.score();
		}
		if (ball.position.x <= 0){
			scoreP2++;
			ball.score();
		}
		
	}
	
	public void updatePaddles(float deltaTime, float accelP1, float accelP2){
		paddleP1.velocity.y = accelP1;
		paddleP2.velocity.y = accelP2;
		paddleP1.update(deltaTime);
		paddleP2.update(deltaTime);
	}
	
	public void checkCollisions(){
		if (OverlapTester.overlapRectangles(paddleP1.bounds, ball.bounds) && ball.velocity.x < 0){
			ball.velocity.x = ball.velocity.x * -1;
			listener.bump();
		}
		if (OverlapTester.overlapRectangles(paddleP2.bounds, ball.bounds) && ball.velocity.x > 0){
			ball.velocity.x = ball.velocity.x * -1;
			listener.bump();
		}
	}
	
	public void checkGameOver(){
		if (scoreP1 > 15 || scoreP2 > 15){
			state = WORLD_STATE_GAME_END;
		}
	}
	
	

}
