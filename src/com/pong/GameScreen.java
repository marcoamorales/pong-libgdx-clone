package com.pong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pong.World.WorldListener;

public class GameScreen extends Screen{
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_WON = 2;
	
	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	SpriteBatch batcher;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	int player1Score;
	int player2Score;
	String player1ScoreString;
	String player2ScoreString;
	FPSLogger fpslogger;
	
	public GameScreen(Game game){
		super(game);
		state = GAME_READY;
		guiCam = new OrthographicCamera(480,320);
		guiCam.position.set(480 / 2, 320 / 2, 0);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		worldListener = new WorldListener(){
			@Override
			public void bump() {
				Assets.playSound(Assets.bounceSound);
			}
		};
		world = new World(worldListener);
		renderer = new WorldRenderer(batcher, world);
		player1Score = 0;
		player2Score = 0;
		player1ScoreString = "0";
		player2ScoreString = "0";
		fpslogger = new FPSLogger();
	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;
		
		switch (state){
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_WON:
			updateWon();
			break;
		}
	}
	
	public void updateReady(){
		if(Gdx.input.justTouched()){
			state = GAME_RUNNING;
		}
	}
	
	private void updateRunning (float deltaTime){
		if (Gdx.app.getType() == Application.ApplicationType.Android){
			if (Gdx.input.getX() < 480 / 2){
				world.paddleP1.position.y = 32 - (Gdx.input.getY() / 10);
			}
			if (Gdx.input.getX() > 480 / 2){
				world.paddleP2.position.y = 32 - (Gdx.input.getY() / 10);
			}
			world.update(deltaTime, 0, 0);
		}
		else{
			float accel1 = 0;
			float accel2 = 0;
			if(Gdx.input.isKeyPressed(Keys.DPAD_UP))
				accel2 = 20;
			if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
				accel2 = -20;
			if(Gdx.input.isKeyPressed(Keys.W))
				accel1 = 20;
			if(Gdx.input.isKeyPressed(Keys.S))
				accel1 = -20;
			world.update(deltaTime, accel1, accel2);
		}
		if (world.scoreP1 != player1Score || world.scoreP2 != player2Score){
			player1Score = world.scoreP1;
			player2Score = world.scoreP2;
			player1ScoreString = "" + player1Score;
			player2ScoreString = "" + player2Score;
		}
		
		fpslogger.log();
		
	}
	
	private void updateWon(){
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		renderer.render();
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.enableBlending();
		batcher.begin();
		switch (state){
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_WON:
			presentWon();
			break;
		}
		batcher.end();
	}
	
	public void presentReady(){
		//waits for a click or touchscreen, doesn't render anything
	}
	
	public void presentRunning(){
		Assets.font.draw(batcher, player1ScoreString, 36, 300);
		Assets.font.draw(batcher, player2ScoreString, 420, 300);
	}
	
	public void presentWon(){
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	
	}

}
