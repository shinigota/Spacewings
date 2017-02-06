package fr.shinigota.spacewings.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.shinigota.spacewings.Spacewings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Spacewings.DEFAULT_SCREEN_WIDTH;
		config.height = Spacewings.DEFAULT_SCREEN_HEIGHT;
		new LwjglApplication(new Spacewings(), config);
	}
}
