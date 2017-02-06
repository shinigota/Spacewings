package fr.shinigota.spacewings.renderer.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.Player;
import fr.shinigota.spacewings.renderable.Renderable;
import fr.shinigota.spacewings.renderable.world.GameWorld;
import fr.shinigota.spacewings.renderer.Renderer;

/**
 * Created by benjamin on 2/4/17.
 */
public class GameWorldRenderer extends Renderer {
    private final OrthographicCamera camera;
    private final SpriteBatch spriteBatch;
    private final Player player;

    public GameWorldRenderer(GameWorld gameWorld) {
        super(gameWorld);

        this.player = gameWorld.getPlayer();
        this.camera = new OrthographicCamera(Spacewings.DEFAULT_SCREEN_WIDTH, Spacewings.DEFAULT_SCREEN_HEIGHT);
        this.updateCamPosition();

        this.spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        this.blackScreen();
        this.updateCamPosition();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }

    private void updateCamPosition() {
        this.camera.position.set(this.player.getPosition(), 0);
        this.camera.update();
    }

    public Vector2 unproject(Vector2 point) {
        Vector3 result = this.camera.unproject(new Vector3(point.x, point.y, 0));
        return new Vector2(result.x, result.y);
    }
}
