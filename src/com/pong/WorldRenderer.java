package com.pong;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 48;
	static final float FRUSTUM_HEIGHT = 32;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	TextureRegion background;
	
	public WorldRenderer (SpriteBatch batch, World world){
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
	}
	
	public void render() {
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}
	
	public void renderBackground(){
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.gameScreenBackgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		batch.end();
	}
	
	public void renderObjects(){
		batch.enableBlending();
		batch.begin();
		Ball ball = world.ball;
		Paddle paddleP1 = world.paddleP1;
		Paddle paddleP2 = world.paddleP2;
		batch.draw(Assets.ball, ball.position.x - 0.9f, ball.position.y - 0.9f, 1, 1);
		batch.draw(Assets.paddle, paddleP1.position.x - 0.5f, paddleP1.position.y - 3.2f, 1f, 6.4f);
		batch.draw(Assets.paddle, paddleP2.position.x - 0.5f, paddleP2.position.y - 3.2f, 1f, 6.4f);
		batch.end();
	}

}
