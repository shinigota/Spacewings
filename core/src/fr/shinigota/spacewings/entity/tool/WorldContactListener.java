package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import fr.shinigota.spacewings.entity.behavior.Collidable;
import fr.shinigota.spacewings.renderable.world.GameWorld;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class WorldContactListener implements ContactListener {
    private final GameWorld gameWorld;
    private Array<Fixture> fixturesToDestroy;
    private Array<Body> bodyToDestroy;

    public WorldContactListener(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.fixturesToDestroy = new Array<Fixture>();
        this.bodyToDestroy = new Array<Body>();
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        performCollisionAction(impulse, contact.getFixtureA());
        performCollisionAction(impulse, contact.getFixtureB());
    }

    private void performCollisionAction(ContactImpulse impulse, Fixture fixture) {
        if (fixture.getBody().getUserData() instanceof Collidable) {
            Collidable entity = (Collidable) fixture.getBody().getUserData();
            entity.onCollision(fixture, impulse, gameWorld);
        }
    }
}
