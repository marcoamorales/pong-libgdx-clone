package com.pong;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class PongDesktop {
	public static void main(String[] argv){
		new JoglApplication(new Pong(), "Pong - by Marco", 480, 320, false);
	}
}
