package fr.shinigota.spacewings;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
