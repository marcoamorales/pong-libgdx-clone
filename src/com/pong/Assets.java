package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture textureatlas;
	public static Texture paddles_n_ball;
	public static TextureRegion gameScreenBackgroundRegion;
	public static TextureRegion mainMenuScreenRegion;
	public static TextureRegion paddle;
	public static TextureRegion ball;
	public static BitmapFont font;
	
	public static Music music;
	public static Sound bounceSound;
	
	public static Texture loadTexture(String file){
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load(){
		textureatlas = loadTexture("data/screen_atlas.png");
		paddles_n_ball = loadTexture("data/paddles.png");
		gameScreenBackgroundRegion = new TextureRegion(textureatlas, 0,0,480,320); //0,0,480,320
		mainMenuScreenRegion = new TextureRegion(textureatlas, 0,320,480,320); //0,320,480,320
		paddle = new TextureRegion(paddles_n_ball, 0,0,10,64);
		ball = new TextureRegion(paddles_n_ball, 53,53,10,10);
		
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("data/Legend_of_zelda_Nullification_OC_ReMix.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		bounceSound = Gdx.audio.newSound(Gdx.files.internal("data/ball_bump.wav"));
	}
	
	public static void playSound(Sound sound){
		sound.play(1);
	}

}
