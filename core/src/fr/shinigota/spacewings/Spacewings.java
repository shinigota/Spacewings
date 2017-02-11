package fr.shinigota.spacewings;

import com.badlogic.gdx.Game;
import fr.shinigota.spacewings.screen.GameScreen;

public class Spacewings extends Game {
	public static final float PIXELS_TO_METERS = 1/100f;
	public static final int DEFAULT_SCREEN_WIDTH = 1280;
	public static final int DEFAULT_SCREEN_HEIGHT = 720;

	@Override
	public void create() {
		this.setScreen(new GameScreen(this));
	}
}
