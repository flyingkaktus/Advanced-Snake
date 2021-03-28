package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Snake;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Advanced Snake 1.0";
		config.height = (2700/3);
		config.width = (1440/3);
		config.resizable = false;
		new LwjglApplication(new Snake(), config);
	}
}
