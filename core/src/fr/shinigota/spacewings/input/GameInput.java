package fr.shinigota.spacewings.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.Player;
import fr.shinigota.spacewings.renderable.world.GameWorld;
import fr.shinigota.spacewings.renderer.world.GameWorldRenderer;

/**
 * Created by benjamin on 2/4/17.
 */
public class GameInput implements InputProcessor {
    private final Spacewings spacewings;
    private final GameWorld gameWorld;
    private final GameWorldRenderer gameWorldRenderer;

    public GameInput(Spacewings spacewings, GameWorld gameWorld, GameWorldRenderer gameWorldRenderer) {
        this.gameWorld = gameWorld;
        this.spacewings = spacewings;
        this.gameWorldRenderer = gameWorldRenderer;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Player player = this.gameWorld.getPlayer();
        Vector2 target = this.gameWorldRenderer.unproject(new Vector2(screenX, screenY));
        Vector2 toTarget = target.cpy().sub(player.getPosition());
        float angle = MathUtils.atan2(-toTarget.x, toTarget.y);
        this.gameWorld.getPlayer().setDesiredAngle(angle);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        Body playerBody = this.gameWorld.getPlayer().getBody();
        this.gameWorld.getPlayer().changeDesiredAcceleration(-amount);
//        System.out.println(amount);
//        System.out.println(playerBody.getPosition().y);
//        if (amount < 0) {
//            playerBody.applyLinearImpulse(0, 10, playerBody.getPosition().x, playerBody.getPosition().y, true);
//        } else if (amount > 0) {
//            playerBody.applyLinearImpulse(0, -10, playerBody.getPosition().x, playerBody.getPosition().y, true);
//        } else  {
//            playerBody.applyLinearImpulse(0, 0, playerBody.getPosition().x, playerBody.getPosition().y, true);
//        }
        return false;
    }
}
