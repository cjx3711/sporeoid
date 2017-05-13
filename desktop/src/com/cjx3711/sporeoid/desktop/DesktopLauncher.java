package com.cjx3711.sporeoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cjx3711.sporeoid.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sporeoid";
		config.height = 400 * 2;
		config.width = 800 * 2;
		new LwjglApplication(new GameMain(), config);
	}
}
