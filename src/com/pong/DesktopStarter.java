package com.pong;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {
	public static void main(String[] args){
		new JoglApplication(new Pong(), "Pong", 480, 320, false);
	}
}
