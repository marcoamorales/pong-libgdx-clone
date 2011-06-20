package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen extends Screen{
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Rectangle newgameBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;
	
	public MainMenuScreen(Game game){
		super(game);
		guiCam = new OrthographicCamera(480,320);
		guiCam.position.set(480 / 2, 320 / 2, 0);
		batcher = new SpriteBatch();
		newgameBounds = new Rectangle(162, 86, 152, 29);
		highscoresBounds = new Rectangle(131, 41, 209, 37);
		helpBounds = new Rectangle(196, 10, 85, 30);
		touchPoint = new Vector3();
		
	}

	@Override
	public void update(float deltaTime) {
		if (Gdx.input.justTouched()){
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if(OverlapTester.pointInRectangle(newgameBounds, touchPoint.x, touchPoint.y)){
				Assets.playSound(Assets.bounceSound);
				game.setScreen(new GameScreen(game));
				return;
			}
//			if (OverlapTester.pointInRectangle(highscoresBounds, touchPoint.x, touchPoint.y)){
//				Assets.playSound(Assets.bounceSound);
//				game.setScreen(new HighSchoresScreen(game));
//				return;
//			}
//			if (OverlapTester.pointInRectangle(helpBounds, touchPoint.x, touchPoint.y)){
//				Assets.playSound(Assets.bounceSound);
//				game.setScreen(new HelpScreen(game));
//				return;
//			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		
		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.mainMenuScreenRegion, 0, 0, 480, 320);
		batcher.end();
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
