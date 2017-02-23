package fr.shinigota.spacewings.renderer.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.ship.Player;
import fr.shinigota.spacewings.renderable.world.GameWorld;
import fr.shinigota.spacewings.renderer.Renderer;

/**
 * Created by benjamin on 2/4/17.
 */
public class DebugWorldRenderer extends Renderer{
    private static final float GRID_MIN_POS = -10000 * Spacewings.PIXELS_TO_METERS;
    private static final float GRID_MAX_POS = 10000 * Spacewings.PIXELS_TO_METERS;
    private static final float SPACE_BETWEEN_WHITE_LINE = 100 * Spacewings.PIXELS_TO_METERS;
    private static final float SPACE_BETWEEN_YELLOW_LINE = 500 * Spacewings.PIXELS_TO_METERS;
    private final Player player;

    private final OrthographicCamera camera;
    private final ShapeRenderer debugRenderer;
    private final Box2DDebugRenderer box2DDebugRenderer;

    public DebugWorldRenderer(GameWorld gameWorld) {
        super(gameWorld);

        this.player = gameWorld.getPlayer();

        this.camera = new OrthographicCamera(Spacewings.DEFAULT_SCREEN_WIDTH, Spacewings.DEFAULT_SCREEN_HEIGHT);
        this.updateCamPosition();

        this.debugRenderer = new ShapeRenderer();
        this.box2DDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void render(float delta) {
        this.blackScreen();
        this.updateCamPosition();
        this.renderDebug();
        this.renderBox2DWorld();
    }

    private void renderBox2DWorld() {
        this.box2DDebugRenderer.render(((GameWorld)this.renderable).getWorld(), camera.combined.cpy().scl(1/Spacewings.PIXELS_TO_METERS));
    }

    private void renderDebug() {
        this.debugRenderer.setProjectionMatrix(camera.combined.cpy().scl(1/Spacewings.PIXELS_TO_METERS));
        this.debugRenderer.begin(ShapeType.Line);

        this.drawAxis();
        this.drawGrid();
        this.renderPlayerAxis();

        this.debugRenderer.end();
    }

    private void renderPlayerAxis() {
        float angle = this.player.getAngle() * MathUtils.radiansToDegrees;
        float axisLength = 50 * Spacewings.PIXELS_TO_METERS;
        Vector2 horizontal = new Vector2(axisLength, 0);
        Vector2 vertical = new Vector2(0, axisLength);
        horizontal.rotate(angle).add(this.player.getPosition());
        vertical.rotate(angle).add(this.player.getPosition());

        this.debugRenderer.setColor(Color.RED);
        this.debugRenderer.line(this.player.getPosition().x, this.player.getPosition().y, vertical.x, vertical.y);
        this.debugRenderer.setColor(Color.GREEN);
        this.debugRenderer.line(this.player.getPosition().x, this.player.getPosition().y, horizontal.x, horizontal.y);
    }

    private void drawGrid() {
        drawGridLines(false);
        drawGridLines(true);
    }

    private void drawGridLines(boolean horizontal) {
        float start = horizontal ? (this.camera.position.y * Spacewings.PIXELS_TO_METERS - Spacewings.DEFAULT_SCREEN_HEIGHT * Spacewings.PIXELS_TO_METERS - SPACE_BETWEEN_YELLOW_LINE)
                : (this.camera.position.x * Spacewings.PIXELS_TO_METERS - Spacewings.DEFAULT_SCREEN_WIDTH * Spacewings.PIXELS_TO_METERS - 2*SPACE_BETWEEN_YELLOW_LINE);
        float end = horizontal ? (this.camera.position.y + Spacewings.DEFAULT_SCREEN_HEIGHT + SPACE_BETWEEN_YELLOW_LINE)
                : (this.camera.position.x * Spacewings.PIXELS_TO_METERS + Spacewings.DEFAULT_SCREEN_WIDTH * Spacewings.PIXELS_TO_METERS + 2*SPACE_BETWEEN_YELLOW_LINE);

        start = start - ( start % SPACE_BETWEEN_YELLOW_LINE ) ;
        end = end + ( end % SPACE_BETWEEN_YELLOW_LINE ) ;
        for(float position = start; position < end ; position += SPACE_BETWEEN_WHITE_LINE) {
            if (position % SPACE_BETWEEN_WHITE_LINE == 0 && position != 0) {
                Color color = position % SPACE_BETWEEN_YELLOW_LINE == 0 ? Color.YELLOW : Color.WHITE;
                drawGridLine(position, color, horizontal);
            }
        }
    }

    private void drawGridLine(float position, Color color, boolean horizontal) {
        this.debugRenderer.set(ShapeType.Line);
        this.debugRenderer.setColor(color);
        if (horizontal) {
            this.debugRenderer.line(-GRID_MIN_POS, position, GRID_MIN_POS, position);
        } else {
            this.debugRenderer.line(position, -GRID_MIN_POS, position, GRID_MIN_POS);
        }
    }

    private void drawAxis() {
        this.debugRenderer.setColor(Color.RED);
        this.debugRenderer.line(0, GRID_MIN_POS, 0, GRID_MAX_POS);
        this.debugRenderer.setColor(Color.GREEN);
        this.debugRenderer.line(GRID_MIN_POS, 0, GRID_MAX_POS, 0);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        this.debugRenderer.dispose();
    }

    private void updateCamPosition() {
        this.camera.position.set(this.player.getPosition().cpy().scl(1/Spacewings.PIXELS_TO_METERS), 0);
        this.camera.update();
    }
}
