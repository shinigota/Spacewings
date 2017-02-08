package fr.shinigota.spacewings.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.controller.GameInput;
import fr.shinigota.spacewings.renderable.world.GameWorld;
import fr.shinigota.spacewings.renderer.world.DebugWorldRenderer;
import fr.shinigota.spacewings.renderer.world.GameWorldRenderer;

/**
 * Created by benjamin on 2/4/17.
 */
public class GameScreen implements Screen {
    private final GameWorld gameWorld;
    private final Spacewings spacewings;
    private final GameWorldRenderer gameWorldRenderer;
    private final DebugWorldRenderer debugWorldRenderer;

    public GameScreen(Spacewings spacewings) {
        this.spacewings = spacewings;
        this.gameWorld = new GameWorld(spacewings);
        this.gameWorldRenderer = new GameWorldRenderer(gameWorld);
        this.debugWorldRenderer = new DebugWorldRenderer(gameWorld);

        Gdx.input.setInputProcessor(new GameInput(this.spacewings, this.gameWorld, this.gameWorldRenderer));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.gameWorld.update(delta);
        this.gameWorldRenderer.render(delta);
        this.debugWorldRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.gameWorld.dispose();
        this.gameWorldRenderer.dispose();
        this.debugWorldRenderer.dispose();
    }
}
