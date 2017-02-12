package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class FixtureType {
    public final static FixtureType LIGHT = new FixtureType(0.1f, 0.2f, 0.5f);
    public final static FixtureType MODERATE = new FixtureType(1f, 0.5f, 0.1f);
    public final static FixtureType HEAVY = new FixtureType(2.5f, 0.5f, 0.1f);
    public final static FixtureType ZERO = new FixtureType(0f, 0f, 0f);

    public final FixtureDef fixtureDef;

    public FixtureType(float density, float friction, float restitution) {
        this.fixtureDef = new FixtureDef();
        this.fixtureDef.density = density;
        this.fixtureDef.friction = friction;
        this.fixtureDef.restitution = restitution;
    }

}
