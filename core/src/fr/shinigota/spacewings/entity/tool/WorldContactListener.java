package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import fr.shinigota.spacewings.entity.behavior.Collidable;
import fr.shinigota.spacewings.entity.ship.Projectile;
import fr.shinigota.spacewings.renderable.world.GameWorld;
import fr.shinigota.spacewings.renderable.world.tool.EntityManager;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class WorldContactListener implements ContactListener {
    private final GameWorld gameWorld;

    public WorldContactListener(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void beginContact(Contact contact) {
//        if (contact.getFixtureA().getBody().getUserData() instanceof Projectile || contact.getFixtureB().getBody().getUserData() instanceof Projectile) {
//            if (contact.getFixtureA().getBody().getUserData() instanceof Projectile)
//                performCollisionAction(null, contact.getFixtureB());
//            else
//                performCollisionAction(null, contact.getFixtureA());
//        }
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
            entity.onCollision(fixture, impulse);
        }
    }
}
