package fr.shinigota.spacewings.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import fr.shinigota.spacewings.renderable.Renderable;

/**
 * Created by benjamin on 2/4/17.
 */
public abstract class Renderer {
    protected Renderable renderable;

    public Renderer(Renderable renderable) {
        this.renderable = renderable;
    }

    protected void blackScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public abstract void render(float delta);
    public abstract void resize(int width, int height);
    public abstract void dispose();
}
