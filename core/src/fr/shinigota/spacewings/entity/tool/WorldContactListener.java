package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class WorldContactListener implements ContactListener {
    private Array<Fixture> fixturesToDestroy;
    private Array<Body> bodyToDestroy;

    public WorldContactListener() {
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
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();


    }
}
